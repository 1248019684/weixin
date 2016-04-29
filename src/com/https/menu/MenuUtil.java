package com.https.menu;

import net.sf.json.JSONObject;

import com.https.HttpsUtil;
import com.menu.Menu;

/*
 * �˵������࣬�ṩ�˵���������ѯ��ɾ��
 */
public class MenuUtil {
	
	private static final String create_menu_irl = "https://api.weixin.qq.com/" +
			"cgi-bin/menu/create?access_token=TOKEN";
	
	private static final String get_menu_irl = "https://api.weixin.qq.com/" +
			"cgi-bin/menu/get?access_token=TOKEN";
	
	private static final String delete_menu_irl = "https://api.weixin.qq.com/" +
			"cgi-bin/menu/delete?access_token=TOKEN";
	
	/*
	 * ��΢�ŷ��������ʹ����˵�����
	 * access_token ΢��ƾ֤
	 * menu �˵�
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
	 * ��΢�ŷ��������Ͳ�ѯ�˵�����
	 * access_token ΢��ƾ֤
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
	 * ��΢�ŷ���������ɾ���˵�����
	 * access_token ΢��ƾ֤
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
