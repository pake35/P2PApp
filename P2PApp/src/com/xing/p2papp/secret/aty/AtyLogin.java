/**
 * 
 */
package com.xing.p2papp.secret.aty;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.xing.p2papp.Config;
import com.xing.p2papp.R;
import com.xing.p2papp.secret.net.GetCode;
import com.xing.p2papp.secret.net.Login;
import com.xing.p2papp.secret.tools.MD5Utils;

/**
 * 
 * @author xingxing
 * 
 */
public class AtyLogin extends Activity {

	private EditText edPhone =null,edCode;
//	private EditText edCode;

	@SuppressLint("ShowToast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.aty_login);

		edPhone = (EditText) findViewById(R.id.etPhoneNum);
		edCode = (EditText) findViewById(R.id.edCode);
		findViewById(R.id.btngetCode).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("onclick Here:"+TextUtils.isEmpty(edPhone.getText()));
				if (TextUtils.isEmpty(edPhone.getText())) {
					Toast.makeText(AtyLogin.this,
							R.string.phone_number_can_not_be_empty,
							Toast.LENGTH_LONG).show();
					return;
				}
				final ProgressDialog pd = ProgressDialog.show(AtyLogin.this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));
				new GetCode(edPhone.getText().toString(),
						new GetCode.SuccessCallback() {

							@Override
							public void onSuccess() {
								pd.dismiss();
								Toast.makeText(AtyLogin.this,
										R.string.success_to_get_code,
										Toast.LENGTH_LONG).show();
							}
						}, new GetCode.FailCallback() {

							@Override
							public void onFail() {
								pd.dismiss();
								Toast.makeText(AtyLogin.this,
										R.string.fail_to_get_code,
										Toast.LENGTH_LONG).show();
							}
						});
			}
		});
		
		findViewById(R.id.btnLogin).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(TextUtils.isEmpty(edPhone.getText())){
					Toast.makeText(AtyLogin.this, R.string.phone_number_can_not_be_empty,Toast.LENGTH_LONG).show();
					return;
				}
				if(TextUtils.isEmpty(edCode.getText())){
					Toast.makeText(AtyLogin.this, R.string.edcode_cannot_be_empty, Toast.LENGTH_LONG).show();
					return;
				}
				
				new Login(MD5Utils.Md5(edPhone.getText().toString()), edCode.getText().toString(), new Login.SuccessCallback() {
					
					@Override
					public void onSuccess(String token) {
						Config.cacheToken(AtyLogin.this, token);
						Config.cachePhoneNum(AtyLogin.this, edPhone.getText().toString());
						
						Intent i = new Intent(AtyLogin.this,AtyTimeline.class);
						i.putExtra(Config.KEY_TOKEN, token);
						i.putExtra(Config.KEY_PHONE_NUM, edPhone.getText().toString());
						startActivity(i);
						finish();
					}
				}, new Login.FailCallback() {
					
					@Override
					public void onFail() {
						Toast.makeText(AtyLogin.this, R.string.fail_to_login, Toast.LENGTH_LONG).show();
						
					}
				});
				
				
			}
		});

	}

}
