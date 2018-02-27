package edu.mum.waa;

import java.io.FileInputStream;
import java.util.Properties;

public class AppSetting {	
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
	
	public String getConfig(String config) {	
		return appProps.getProperty(config);
	}


}
