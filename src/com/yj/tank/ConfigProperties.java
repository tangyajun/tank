package com.yj.tank;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-8:15
 **/
public class ConfigProperties {
	static ConfigProperties configProperties=new ConfigProperties();
	static Properties configProps=new Properties();
	static {
		loadConfig();
	}
	private ConfigProperties() {

	}

	public static ConfigProperties getInstance() {
		return configProperties;
	}

	public static void loadConfig() {
		InputStream inputStream=ConfigProperties.class.getClassLoader().getResourceAsStream("config");
		if (Objects.nonNull(inputStream)) {
			try {
				configProps.load(inputStream);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public  String getString(String key) {
		if (Objects.nonNull(configProps)) {
			return configProps.getProperty(key);
		}
		return "";
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public Integer getInteger(String key) {
		String value="";
		if (Objects.nonNull(configProps)) {
			value=getString(key);
			if (value!= null && !"".equals(value)) {
				return Integer.valueOf(value);
			}
		}
		return null;
	}


	public  Object getObject(String key) {
		if (Objects.nonNull(configProps)) {
			return configProps.get(key);
		}
		return null;
	}

	public static void main(String[] args) {
		//System.out.println(getString("initEnemyTankCount"));
	}
}
