package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.gorest.frameworkexception.ApiFrameWorkException;

public class ConfigurationManager {
	private Properties prop;
	private FileInputStream ip;
	
	public Properties init_prop() {

		prop=new Properties();
		String envName=System.getProperty("env");
		
		try {
		if(envName==null) {
			System.out.println("No env is given hence running in qa env");
			ip=new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
		}
		else {
			switch (envName.toLowerCase().trim()) {
			
			case "qa":
				ip=new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
				break;
			case "dev":
				ip=new FileInputStream(".\\src\\test\\resources\\config\\dev.config.properties");
				break;
			case "stage":
				ip=new FileInputStream(".\\src\\test\\resources\\config\\stage.config.properties");
				break;
			case "prod":
				ip=new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
				break;

			default:
				System.out.println("please pass the right env");
				throw new ApiFrameWorkException("NO ENV GIVEN");
			}
		}
		
		prop.load(ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
	}
	

}
