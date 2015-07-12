/**
 * 
 */
package com.xing.p2papp.secret.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.xing.p2papp.Config;

/**
 * 获取短信  验证码
 *
 * @author xingxing  
 * @version 0.1  2015-5-31
 */
public class GetCode {

	public GetCode(String phone,final SuccessCallback successCallback,final FailCallback failCallback){
		
		new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				
				try {
					JSONObject jsonObj=new JSONObject(result);
					switch (jsonObj.getInt(Config.KEY_STATUS)) {
					case Config.RESULT_STATUS_SUCCESS:
						if(successCallback!=null){
							successCallback.onSuccess();
						}
						break;

					default:
						if(failCallback!=null){
							failCallback.onFail();
						}
						break;
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					if(failCallback!=null){
						failCallback.onFail();
					}
				}
				
			}
		},new NetConnection.FailCallback() {
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				if(failCallback!=null){
					failCallback.onFail();
				}
			}
		}, Config.KEY_ACTION,Config.ACTION_GET_CODE,Config.KEY_PHONE_NUM, phone);
	}
	
	public static interface SuccessCallback{
		void onSuccess();
	}
	
	public static interface FailCallback{
		void onFail();
	}
}
