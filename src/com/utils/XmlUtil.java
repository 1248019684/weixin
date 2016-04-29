package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * XML工具类
 */
public class XmlUtil {
	
	private static final Logger log = LoggerFactory.getLogger(XmlUtil.class);
	
	public static Map<String,String> getRequestMap(HttpServletRequest request){
		Map<String,String> reqmap = null;
		InputStream in = null;
		SAXReader reader = null;
		try {
			reqmap =new HashMap<String, String>();
			in = request.getInputStream();
			reader = new SAXReader();
		    Document  doc = reader.read(in);
		    Element root  = getRootElement(doc);
		    List<Element> elements = root.elements();
		    if(null == elements){
		    	throw new Exception("root don not have element");
		    }
		    for(Element element : elements){
		    	if( !reqmap.containsKey(element.getName())){
		    		reqmap.put(element.getName(), element.getText());
		    	}
		    }
		    in.close();
		} catch (IOException e) {
			log.error("get request InputStream occur problem", e);
		}catch (DocumentException e) {
			log.error("get request document occur problem", e);
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		return reqmap;
		
	}
	
	// 得到根元素
	public static Element getRootElement(Document doc) throws Exception {
		if (null == doc) {
			throw new Exception("the request doc is null!");
		}
		if(null == doc.getRootElement()){
			throw new Exception("the rootElement is null!");
		}
		return doc.getRootElement();
	}
}
