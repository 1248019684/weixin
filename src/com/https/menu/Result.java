package com.https.menu;

/*
 * 微信菜单接口返回对象
 * errcode 0 代表成功
 * errmsg  成功是 OK
 */
public class Result {
	
	private String errcode;
	private String errmsg;
	
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
