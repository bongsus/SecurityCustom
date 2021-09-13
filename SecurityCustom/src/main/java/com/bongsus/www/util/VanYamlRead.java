package com.bongsus.www.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:constant.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "responsecode")
public class VanYamlRead {
	
	private String success;
	private String error;
	
	/**
	 * success을(를) 가져옵니다.
	 * @return success
	 */
	public String getSuccess() {
		return success;
	}
	/**
	 * success을(를) 설정합니다.
	 * @param success 
	 */
	public void setSuccess(String success) {
		this.success = success;
	}
	/**
	 * error을(를) 가져옵니다.
	 * @return error
	 */
	public String getError() {
		return error;
	}
	/**
	 * error을(를) 설정합니다.
	 * @param error 
	 */
	public void setError(String error) {
		this.error = error;
	}
	
	
}
