/**
 * 
 */
package com.xing.p2papp.secret.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.xing.p2papp.Config;

/**
 * 登陆通信
 *
 * @author xingxing  
 * @version 0.1  2015-6-1
 */
public class Login {

	public Login(String phone_md5,String code,final SuccessCallback successCallback,final FailCallback failCallback ){
		new NetConnection(Config.SERVER_URL, HttpMethod.GET, new NetConnection.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				try {
					JSONObject jsonObj=new JSONObject(result);
					switch (jsonObj.getInt(Config.KEY_STATUS)) {
					case Config.RESULT_STATUS_SUCCESS:
						if(successCallback!=null){
							successCallback.onSuccess(jsonObj.getString(Config.KEY_TOKEN));
						}
						break;
					default:
						if(failCallback!=null){
							failCallback.onFail();
						}
						break;
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
					if(failCallback!=null){
						failCallback.onFail();
					}
				}
			}
		}, new NetConnection.FailCallback() {
			
			@Override
			public void onFail() {
				if(failCallback!=null){
					failCallback.onFail();
				}
			}
		}, Config.KEY_ACTION,Config.ACTION_LOGIN,Config.KEY_PHONE_MD5,phone_md5,Config.KEY_CODE ,code);
	}
	
	public static interface SuccessCallback{
		void onSuccess(String token);
	}
	
	public static interface FailCallback{
		void onFail();
	}
}
