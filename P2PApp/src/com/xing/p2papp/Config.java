/**
 * 
 */
package com.xing.p2papp;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * 系统常量
 * 
 * @author xingxing
 * @version 0.1 2015-5-31
 */
public class Config {

	// public static final String
	// SERVER_URL="http://demo.eoeschool.com/api/v1/nimings/io";// 文档需求地址
	public static final String SERVER_URL = "http://192.168.56.1:8080/WebTest/api.jsp";//

	public static final String KEY_ACTION = "action";
	public static final String KEY_STATUS = "status";
	public static final int RESULT_STATUS_SUCCESS = 1;
	public static final int RESULT_STATUS_FAIL = 0;
	public static final int RESULT_STATUS_INVAILD_TOKEN = 2;

	public static final String ACTION_GET_CODE = "send_pass";
	public static final String ACTION_LOGIN = "login";

	public static final String KEY_PHONE_NUM = "phone";
	public static final String KEY_PHONE_MD5 = "phone_md5";
	public static final String KEY_CODE="code";
	
	public static final String APP_ID = "com.xing.p2papp";
	public static final String KEY_TOKEN = "token";
	public static final String CHAERSET = "utf-8";

	public static String getCachedToken(Context context) {
		return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE)
				.getString(KEY_TOKEN, null);
	}

	public static void cacheToken(Context context, String token) {
		Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE)
				.edit();
		e.putString(KEY_TOKEN, token);
		e.commit();
	}
	public static String getCachedPhoneNum(Context context) {
		return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE)
				.getString(KEY_PHONE_NUM, null);
	}

	public static void cachePhoneNum(Context context, String phone) {
		Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE)
				.edit();
		e.putString(KEY_PHONE_NUM, phone);
		e.commit();
	}
}
