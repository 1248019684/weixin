package com.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
 * ��֤΢��
 * ΢������У�鹤����
 */
public class SignUtil {
	
	public static final Logger log = LoggerFactory.getLogger(SignUtil.class);
	
	/*
	 * ��������Ƿ�������΢��
	 * @param token     �����Լ��趨��tokenֵ
     * @param signature ΢�Ŵ����ı���
     * @param timestamp ΢�Ŵ����ı���
     * @param nonce     ΢�Ŵ����ı���
     * @return �Ƿ�Ϸ�
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
			log.error("�����쳣 {}", e);
		}catch (UnsupportedEncodingException e) {
			log.error("�����ʽ�쳣 {}", e);
		}
		return null != ature ? ature.equalsIgnoreCase(signature):false;
	}
	
	public static String formatbyte(byte[] bytes,int radix){
		if(radix > Character.MAX_RADIX || radix < Character.MIN_RADIX){
			log.error("����λ��������Χ");
		}
		return new BigInteger(bytes).toString(radix);
	}

}
