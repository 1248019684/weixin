package com.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
 * 认证微信
 * 微信请求校验工具类
 */
public class SignUtil {
	
	public static final Logger log = LoggerFactory.getLogger(SignUtil.class);
	
	/*
	 * 检查请求是否来自于微信
	 * @param token     我们自己设定的token值
     * @param signature 微信传来的变量
     * @param timestamp 微信传来的变量
     * @param nonce     微信传来的变量
     * @return 是否合法
	 */
	public static boolean checkwechatsign(String token,String signature,
			String timestamp,String nonce){
		if(StrUtil.hasBank(token,signature,timestamp,nonce)){
			return false;
		}
		String[] params = {token,timestamp,nonce};
		Arrays.sort(params);
		StringBuffer sbf = new StringBuffer();
		for(String param : params){
			sbf.append(param);
		}
		String checkcontent = sbf.toString();
		MessageDigest msd = null;
		String ature = null;
		try {
			msd = MessageDigest.getInstance("SHA-1");
			byte[] digest = msd.digest(checkcontent.getBytes("UTF-8"));
			ature = formatbyte(digest,16);
		} catch (NoSuchAlgorithmException e) {
			log.error("加密异常 {}", e);
		}catch (UnsupportedEncodingException e) {
			log.error("编码格式异常 {}", e);
		}
		return null != ature ? ature.equalsIgnoreCase(signature):false;
	}
	
	public static String formatbyte(byte[] bytes,int radix){
		if(radix > Character.MAX_RADIX || radix < Character.MIN_RADIX){
			log.error("编码位数超出范围");
		}
		return new BigInteger(bytes).toString(radix);
	}

}
