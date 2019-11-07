package com.gson.demo;

import com.google.gson.Gson;

public class GsonUtils {
	
	static String json = "{\"code\":0, \"message\":\"ok\", \"data\":{\"id\":\"1001\", \"name\":\"唐朝健\"}}";
	
	public static <T> void post(HttpCallBack<T> callback) {
		Gson gson = new Gson();
		ResponseEntity<T> entity = gson.fromJson(json, callback);
		callback.success(entity.getData());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GsonUtils.post(new HttpCallBack<User>() {

			@Override
			public void success(User t) {
				if(t != null) {
					System.out.println(t.getName());
				}
			}
			
		});
	}

}
