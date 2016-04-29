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
 * Https协议安全通信工具类
 */
public class HttpsUtil {
	
	private static final Logger log = LoggerFactory.getLogger(HttpsUtil.class);
	
	/*
	 * 封装Https请求
	 * urlpath  请求路径
	 * method   请求方式get/post
	 * outputStr 请求发送数据
	 */
	public static JSONObject httpsRequest(String urlpath,String method,String outputStr){
		URL url;
		BufferedReader reader;
		OutputStream out;
		String resultstr;
		StringBuffer resultbuf = new StringBuffer();
		try {
			//创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = {new MyX509TrustManager ()}; 
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE"); 
			sslContext.init(null, tm, new java.security.SecureRandom()); 

			//从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			//创建URl对象
			url = new URL(urlpath);
			
			//创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
			HttpsURLConnection httpsConn = (HttpsURLConnection)url.openConnection();
			
			//设置连接参数
			httpsConn.setRequestMethod(method);
			httpsConn.setSSLSocketFactory(ssf);
			httpsConn.setDoOutput(true);
			httpsConn.setDoInput(true);
			httpsConn.setUseCaches(false);
			
			//向微信服务器发送https带请求数据的请求
			if(!StrUtil.isBank(outputStr)){
				out = httpsConn.getOutputStream();
				out.write(outputStr.getBytes("UTF-8"));
				out.close();
			}
			
			//从微信服务器获得反馈
			reader = new BufferedReader(new InputStreamReader(httpsConn.getInputStream()));
			while(null != (resultstr = reader.readLine())){
				resultbuf.append(resultstr);
			}
			
			//释放资源
			reader.close();
			httpsConn.disconnect();
		}catch (ConnectException e) {
			log.error("连接异常 {}", e);
		}catch (Exception e) {
			log.error("Https请求异常 {}", e);
		}
		return JSONObject.fromObject(resultbuf.toString());
	}

}
