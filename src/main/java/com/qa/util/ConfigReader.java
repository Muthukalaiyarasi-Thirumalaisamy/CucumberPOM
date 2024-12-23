package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	/*
	 * This method is used to load the properties from config.properties file
	 * @return it returns Properties prop object
	 */
	
	private Properties prop;
	
	public Properties init_prob()
	{
		prop= new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

		
	}
}
