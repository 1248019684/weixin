package com.config;

import java.util.HashMap;

/**
 * <pre>
 * ���������ļ���ȡ�����أ�ͨ���ù���ػ�õ������ļ�������
 * �����Զ����������ļ��ĸ����Զ�ˢ�£���������Ӧ�÷���
 * ʹ��ʵ��
 * [1]:���������ļ�application.properties��Ŀ¼src��ʹ�����·�ʽ
 * <p>
 * 	ConfigFile cfg = ConfigFileFactory.getInstance().get("application");
 * 	String value = cfg.getValue("value");
 * </p>
 * [2]:�����ļ�boss.properties����com.maywide.common.SessionUtil��ͬĿ¼�£�����ʹ�����·�
 * <p>
 * 	ConfigFile cfg = ConfigFileFactory.getInstance().get("boss", SessionUtil.class);
 * 	String value = cfg.getValue("value");
 * </p>
 * [3]:�����ļ�smp.properties�ڰ�com.maywide.common.config,����ʹ�����·�ʽ
 * <p>
 *	ConfigFile cfg = ConfigFileFactory.getInstance().get("smp", "/com/maywide/common/config/");
 * 	String value = cfg.getValue("value");
 * </p>
 * </pre>
 * 
 * @author luoxian
 * @since Jul 10, 2008 8:49:55 AM
 * @version 1.0
 */
public class ConfigFileFactory {
	private static ConfigFileFactory instance = null;
	private final String extendName = ".properties"; // Ĭ�������ļ���ʽ
	private HashMap hashMap = new HashMap();

	private ConfigFileFactory() {
	}

	public static synchronized ConfigFileFactory getInstance() {
		if (instance == null) {
			instance = new ConfigFileFactory();
		}
		return instance;
	}

	/**
	 * �ú�����ʾ�����ļ��ڸ�Ŀ¼������classesĿ¼
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public ConfigFile get(String fileName) {
		ConfigFile file = (ConfigFile) hashMap.get(fileName);
		try {
			if (file == null) {
				ConfigFile newFile = new ConfigFile(fileName + extendName);
				hashMap.put(fileName, newFile);
				return newFile;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ConfigFile();
		}
		return file;
	}

	/**
	 * �ú�����ʾ�����ļ���resendClass����������ͬ��Ŀ¼
	 * 
	 * @param fileName
	 * @param presentClass
	 * @return
	 * @throws Exception
	 */
	public ConfigFile get(String fileName, Class presentClass) throws Exception {
		ConfigFile file = (ConfigFile) hashMap.get(fileName);
		if (file == null) {
			ConfigFile newFile = new ConfigFile(fileName + extendName,
					presentClass);
			hashMap.put(fileName, newFile);
			return newFile;
		}
		return file;
	}

	/**
	 * �ú�����ȡ�����ļ����ļ�Ŀ¼�ɲ���pathָ��<br>
	 * 
	 * <pre>
	 * ���磺�����ļ�config.properties�ڰ�com.maywide.common.test����
	 * �����path���� &quot;/com/maywide/common/test/&quot;
	 * </pre>
	 * 
	 * @param fileName
	 * @param packageName
	 * @return
	 * @throws Exception
	 */
	public ConfigFile get(String fileName, String packageName) throws Exception {
		ConfigFile file = (ConfigFile) hashMap.get(fileName);
		if (file == null) {
			ConfigFile newFile = new ConfigFile(fileName + extendName,
					packageName);
			hashMap.put(fileName, newFile);
			return newFile;
		}
		return file;
	}

	public int size() {
		return hashMap.size();

	}
}