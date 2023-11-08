package com.projetofcv.rosangelaestetica;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.projetofcv.rosangelaestetica.config.TestConfig;

public class ServeletInicialization extends SpringBootServletInitializer {

    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TestConfig.class);
	}
    
}
