package edu.mum.waa;

import java.io.FileInputStream;
import java.util.Properties;

public class AppSetting {
	public static final String DOT_WEB_CLASS_CONFIG = "dot.web.class";
	public static final String DOT_WEB_CLASS_CONFIG_DEFAULT = "edu.mum.waa.DotWebClass";
	public static final String DOT_WEB_URI_CONFIG = "dot.web.uri";
	public static final String DOT_WEB_URI_CONFIG_DEFAULT = "welcome.web,contacts.web";
	
	private Properties appProps = new Properties();
	
	public AppSetting() {
		try {
			String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			String appConfigPath = rootPath + "app.properties";		 
			appProps = new Properties();
			appProps.load(new FileInputStream(appConfigPath));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public String getConfig(String config, String defaultVal) {	
		return appProps.getProperty(config, defaultVal);
	}

}
