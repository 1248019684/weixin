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
	 * get����У���Ƿ�����΢�ŵ�����
	 * У��ɹ���ԭ������echostr
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
			log.info("΢�Žӿ�У��ɹ�");
			out.print(echostr);
		}
		out.close();
	}
	
	/*
	 * ��΢�ŷ��������տͻ����͵���Ϣ
	 * ������Ϣ��Ӧ��ͻ�
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
			log.error("���������Ϣ��װ����ʧ��", e);
		}
	}

}
