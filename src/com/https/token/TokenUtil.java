package com.https.token;

import net.sf.json.JSONObject;

import com.config.ConfigFileFactory;
import com.https.HttpsUtil;

/*
 * ΢��ƾ֤������
 */
public class TokenUtil {
	
	private static final String token_url = "https://api.weixin.qq.com/" +
			"cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
	
	
	/*
	 * ��ȡ΢�Žӿ�ƾ֤
	 */
	public static Token getToken(String appid,String secret){
		String requesturl = token_url.replace("APPID", appid).replace("SECRET", secret);
		JSONObject result = HttpsUtil.httpsRequest(requesturl, "GET", null);
		Token token = null;
		if(result != null){
			token = (Token) JSONObject.toBean(result, Token.class);
		}
		return token;
	}
}
