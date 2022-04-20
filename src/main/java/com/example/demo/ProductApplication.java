package com.example.demo;

import com.example.demo.database.util.DBConnectionService;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
		System.out.println(System.getProperty("DB_URL"));
		System.out.println(System.getenv("DB_URL"));
//		String url = System.getenv("DB_URL");
//		String schema = System.getenv("DB_SCHEMA");
//		DBConnectionService.connect(url, schema);
	}

	public class TerminateBean {

		@PreDestroy
		public void onDestroy() throws Exception {
//			DBConnectionService.disconnect();
		}
	}

	@Configuration
	public class ShutdownConfig {

		@Bean
		public TerminateBean getTerminateBean() {
			return new TerminateBean();
		}
	}

}
