package com.gson.demo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.Gson;

public abstract class HttpCallBack<T> implements ParameterizedType{
	
	public abstract void success(T t);
	
	
	 public T deal(String json) {
       Type gsonType = this;
       ResponseEntity<T> response = new Gson().fromJson(json, gsonType);
       return response.getData();
   }

	 
	@Override
    public Type[] getActualTypeArguments() {
        Class clz = this.getClass();
        //这里必须注意在外面使用new GsonResponsePasare<GsonResponsePasare.DataInfo>(){};实例化时必须带上{},否则获取到的superclass为Object
        Type superclass = clz.getGenericSuperclass(); //getGenericSuperclass()获得带有泛型的父类
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return parameterized.getActualTypeArguments();
    }

    @Override
    public Type getOwnerType() {
        return null;
    }

    @Override
    public Type getRawType() {
        return ResponseEntity.class;
    }
}
