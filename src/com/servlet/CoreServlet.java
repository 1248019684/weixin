package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.config.ConfigFileFactory;
import com.utils.SignUtil;
import com.utils.StrUtil;
import com.utils.XmlUtil;

public class CoreServlet extends HttpServlet {
	
	private Logger log = LoggerFactory.getLogger(CoreServlet.class);
	
	/*
	 * get请求校验是否来自微信的请求
	 * 校验成功，原样返回echostr
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String token = ConfigFileFactory.getInstance().get("application").getValue("token");
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		PrintWriter out = resp.getWriter();
		if(SignUtil.checkwechatsign(token, signature, timestamp, nonce)){
			log.info("微信接口校验成功");
			out.print(echostr);
		}
		out.close();
	}
	
	/*
	 * 从微信服务器接收客户发送的信息
	 * 处理信息并应答客户
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String,String> paramsmap = XmlUtil.getRequestMap(req);
		String msgType = paramsmap.get("msgType");
		String msgclassname = StrUtil.getClassFullName(msgType);
		try {
			Object msgClass = Class.forName(msgclassname).newInstance();
			BeanUtils.copyProperties(msgClass, paramsmap);
		}catch (ClassNotFoundException e) {
			log.error("{} can not find", msgclassname);
		}catch (Exception e) {
			log.error("获得请求信息封装对象失败", e);
		}
	}

}
