package com.utils;

import org.apache.commons.lang3.StringUtils;

/*
 * �ַ���У�鹤����
 */
public class StrUtil {
	
	public static final String[] msgtype = {"text","image","voice","video","location","link"};
	public static final String reqmsgpath = "com.message.request.";
	public static final String message = "Message";
	
	/*
	 * У���ַ����Ƿ�Ϊ��
	 */
	public static boolean isBank(String str){
		if(null == str || "".equals(str.trim())){
			return true;
		}
		if(str.length() < 1){
			return true;
		}
		return false;
	}
	
	
	/*
	 * У���ַ����������Ƿ��п�ֵ
	 */
	public static boolean hasBank(String... strs){
		if(null == strs || strs.length < 1){
			return true;
		}
		for(String str : strs){
			if(isBank(str)){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * ���ݸ������������ͣ���������װ��ȫ·��
	 */
	public static String getClassFullName(String msgType){
		if(isBank(msgType)){
			return null;
		}
		StringBuffer classfullname = new StringBuffer();
		for(int i = 0;i<msgtype.length;i++){
			if(msgType.contains(msgtype[i])){
				classfullname.append(reqmsgpath).append(firstToUpper(msgtype[i])).
				append(message);
			}
		}
		return classfullname.toString();
	}
	
	/*
	 * �׸��ַ���д
	 */
	public static String firstToUpper(String str){
		if(str.length()>0){
			str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
		}
		return str;
	}

}
