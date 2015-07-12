/**
 * 
 */
package com.xing.p2papp.secret.ld;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xing.p2papp.Config;
import com.xing.p2papp.secret.tools.MD5Utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;

/**
 * 读取手机上联系人数据
 *
 * @author xingxing  
 * @version 0.1  2015-6-28
 */
public class MyContacts {

	public static String getContactsJSONString(Context context){
		
		Cursor cur=context.getContentResolver().query(Phone.CONTENT_URI, null,null, null, null);
		String phoneNum;
		JSONArray jsonArray= new JSONArray();
		JSONObject jsonObj ;
		
		while(cur.moveToNext()){
			phoneNum = cur.getString(cur.getColumnIndex(Phone.NUMBER));
			
			if(phoneNum.charAt('0')=='+' 
					&& phoneNum.charAt('1')=='8'
					&& phoneNum.charAt('2')=='6'){
				phoneNum=phoneNum.substring(3);
			}
			jsonObj= new JSONObject();
			try {
				jsonObj.put(Config.KEY_PHONE_MD5, MD5Utils.Md5(phoneNum));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonArray.put(jsonObj);
			
			System.out.println(phoneNum);
		}
		
		return jsonArray.toString();
	}
}
