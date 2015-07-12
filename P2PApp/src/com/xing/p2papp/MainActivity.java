package com.xing.p2papp;

import com.xing.p2papp.secret.aty.AtyLogin;
import com.xing.p2papp.secret.aty.AtyTimeline;
import com.xing.p2papp.secret.ld.MyContacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		MyContacts.getContactsJSONString(this);
		
//		setContentView(R.layout.atytime); 
//		
//		String token =Config.getCachedToken(this);
//		if(token!=null){
//			Intent i = new Intent(MainActivity.this, AtyTimeline.class);
//			i.putExtra(Config.KEY_TOKEN, token) ;
//			startActivity(i);
//		}else{
//			startActivity(new Intent(MainActivity.this,AtyLogin.class));
//		}
		
	}

	
}
