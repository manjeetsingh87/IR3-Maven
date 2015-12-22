package com.ir3.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesFileReader {
	
	private static Map<String, String> propItems = new HashMap<String, String>();
	private static final String file = "target/classes/config.properties";
	public static Map<String, String> getProperties() {
		try {
			Properties prop = new Properties();
			FileInputStream stream = new FileInputStream(file);
			if(null != stream)
				prop.load(stream);
			stream.close();
			
			formatPropFileData(prop);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return propItems;
	}
	
	private static void formatPropFileData(Properties prop) {
		Enumeration<?> keys = prop.keys();
		while(keys.hasMoreElements()) {
			String key = (String)keys.nextElement();
			propItems.put(key, prop.getProperty(key));
		}
	}
}