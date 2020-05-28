package com.legalZoom.customutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class propertyFile {
	static Properties prop = new Properties();

	public static String getProperty(String key) {
		String value = "";
		try {
			String basepath = System.getProperty("user.dir"); 
					
			System.out.println("Absolute path:" + basepath);
			FileInputStream fis = new FileInputStream(basepath +"/src/main/resources/legalZoomRepository.properties");

			prop.load(fis);
			value = (String) prop.get(key);
		} catch (FileNotFoundException e) {
			System.err.println("Object Repository not found");

		} catch (IOException e) {
			System.out.println("Unable to load properties file");
			e.printStackTrace();
		}
		return value;
	}

public static String[] getLocator(String locatorName) {
	
	return getProperty(locatorName).split("##") ;
}
	
}


