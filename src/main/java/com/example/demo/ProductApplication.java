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
		String url = System.getenv("DB_URL");
		String schema = System.getenv("DB_SCHEMA");
//		String url = "jdbc:postgresql://193.191.177.96:63889/potpal_db";
//		String schema = "webshop";
		DBConnectionService.connect(url, schema);
		SpringApplication.run(ProductApplication.class, args);
	}

	public class TerminateBean {

		@PreDestroy
		public void onDestroy() throws Exception {
			DBConnectionService.disconnect();
			System.out.println("Terminated connection!");
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
