package com.https;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.utils.StrUtil;

/*
 * HttpsЭ�鰲ȫͨ�Ź�����
 */
public class HttpsUtil {
	
	private static final Logger log = LoggerFactory.getLogger(HttpsUtil.class);
	
	/*
	 * ��װHttps����
	 * urlpath  ����·��
	 * method   ����ʽget/post
	 * outputStr ����������
	 */
	public static JSONObject httpsRequest(String urlpath,String method,String outputStr){
		URL url;
		BufferedReader reader;
		OutputStream out;
		String resultstr;
		StringBuffer resultbuf = new StringBuffer();
		try {
			//����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
			TrustManager[] tm = {new MyX509TrustManager ()}; 
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE"); 
			sslContext.init(null, tm, new java.security.SecureRandom()); 

			//������SSLContext�����еõ�SSLSocketFactory����
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			//����URl����
			url = new URL(urlpath);
			
			//����HttpsURLConnection���󣬲�������SSLSocketFactory����
			HttpsURLConnection httpsConn = (HttpsURLConnection)url.openConnection();
			
			//�������Ӳ���
			httpsConn.setRequestMethod(method);
			httpsConn.setSSLSocketFactory(ssf);
			httpsConn.setDoOutput(true);
			httpsConn.setDoInput(true);
			httpsConn.setUseCaches(false);
			
			//��΢�ŷ���������https���������ݵ�����
			if(!StrUtil.isBank(outputStr)){
				out = httpsConn.getOutputStream();
				out.write(outputStr.getBytes("UTF-8"));
				out.close();
			}
			
			//��΢�ŷ�������÷���
			reader = new BufferedReader(new InputStreamReader(httpsConn.getInputStream()));
			while(null != (resultstr = reader.readLine())){
				resultbuf.append(resultstr);
			}
			
			//�ͷ���Դ
			reader.close();
			httpsConn.disconnect();
		}catch (ConnectException e) {
			log.error("�����쳣 {}", e);
		}catch (Exception e) {
			log.error("Https�����쳣 {}", e);
		}
		return JSONObject.fromObject(resultbuf.toString());
	}

}
