package com.https.menu;

import net.sf.json.JSONObject;

import com.https.HttpsUtil;
import com.menu.Menu;

/*
 * 菜单工具类，提供菜单创建，查询，删除
 */
public class MenuUtil {
	
	private static final String create_menu_irl = "https://api.weixin.qq.com/" +
			"cgi-bin/menu/create?access_token=TOKEN";
	
	private static final String get_menu_irl = "https://api.weixin.qq.com/" +
			"cgi-bin/menu/get?access_token=TOKEN";
	
	private static final String delete_menu_irl = "https://api.weixin.qq.com/" +
			"cgi-bin/menu/delete?access_token=TOKEN";
	
	/*
	 * 向微信服务器发送创建菜单请求
	 * access_token 微信凭证
	 * menu 菜单
	 */
	public static Result createMenu(String access_token,Menu menu){
		String createmenu_url = create_menu_irl.replace("TOKEN", access_token);
		String menustr = JSONObject.fromObject(menu).toString();
		JSONObject resultobj = HttpsUtil.httpsRequest(createmenu_url, "POST", menustr);
		Result result = null;
		if( null != resultobj){
			result = (Result)JSONObject.toBean(resultobj,Result.class);
		}
		return result;
	}
	
	/*
	 * 向微信服务器发送查询菜单请求
	 * access_token 微信凭证
	 */
	public static String getMenu(String access_token){
		String getmenu_url = get_menu_irl.replace("TOKEN", access_token);
		JSONObject resultobj = HttpsUtil.httpsRequest(getmenu_url, "GET", null);
		String resultstr = null;
		if( null != resultobj){
			resultstr = resultobj.toString();
		}
		return resultstr;
	}
	
	/*
	 * 向微信服务器发送删除菜单请求
	 * access_token 微信凭证
	 */
	public static Result deleteMenu(String access_token){
		String delmenu_url = delete_menu_irl.replace("TOKEN", access_token);
		JSONObject resultobj = HttpsUtil.httpsRequest(delmenu_url, "GET", null);
		Result result = null;
		if( null != resultobj){
			result = (Result)JSONObject.toBean(resultobj,Result.class);
		}
		return result;
	}

}
