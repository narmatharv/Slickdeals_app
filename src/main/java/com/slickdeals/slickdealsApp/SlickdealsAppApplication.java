package com.slickdeals.slickdealsApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SlickdealsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlickdealsAppApplication.class, args);
	}

}
