/**
 * 
 */
package com.xing.p2papp.secret.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5 工具
 * @author xingxing  
 * @version 0.1  2015-7-12
 */
public class MD5Utils {
	
	
	public static String Md5(String string){
		byte[] hash; 
		
		try{
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		}catch(NoSuchAlgorithmException e){
			throw new RuntimeException("huh, Md5 should be supported ?",e);
		}catch(UnsupportedEncodingException e){
			throw new RuntimeException("huh, UTF-8 should be supported ?",e);
		}catch(Exception e){
			throw new RuntimeException("unknown exception");
		}
		StringBuilder hex = new StringBuilder(hash.length*2);
		
		for (byte b : hash) {
			if((b & 0xFF)<0x10) hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		
		return hex.toString();
	}

}
