package com.test;

import net.sf.json.JSONObject;

import com.https.menu.MenuUtil;
import com.https.menu.Result;
import com.https.token.Token;
import com.https.token.TokenUtil;
import com.menu.Button;
import com.menu.ClickButton;
import com.menu.ComplexButton;
import com.menu.Menu;
import com.menu.ViewButton;

public class Test {


	public static void main(String[] args) {
		
		String appid = "wxe9e57d6812993e4c";
		String secret = "4f4ef5e642192929c95f21d70102deb5";
		
		Token token = TokenUtil.getToken(appid, secret);
		
		Menu menu = new Menu();
		ClickButton btn1 = new ClickButton();
		btn1.setName("≤‚ ‘∞¥≈•1");
		btn1.setKey("12");
		btn1.setType("click");
		
		ViewButton btn2 = new ViewButton();
		btn2.setName("≤‚ ‘∞¥≈•2");
		btn2.setType("view");
		btn2.setUrl("http://www.baidu.com/");
		
		ComplexButton btn3 = new ComplexButton();
		btn3.setName("≤‚ ‘∞¥≈•3");
		btn3.setSub_button(new Button[]{btn1,btn2});
		
		menu.setButton(new Button[]{btn1,btn2,btn3});
		Result result = MenuUtil.createMenu(token.getAccess_token(), menu);
        System.out.println(JSONObject.fromObject(result).toString());
	}

}
