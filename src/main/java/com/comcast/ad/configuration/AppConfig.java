package com.comcast.ad.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.comcast.ad.service.AdService;
import com.comcast.ad.service.impl.AdServiceImpl;

@Configuration
public class AppConfig {

	@Bean(name="adService")
	public AdService getService() {
		AdService adService = new AdServiceImpl();
		return adService;
	}	
}
