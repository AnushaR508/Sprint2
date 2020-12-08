package com.cg.leavemanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author anusha
 * This class starts the time card application
 * 
 */

@EnableSwagger2
@SpringBootApplication
public class TimeCardApplication {
	 private static final Logger logger = LoggerFactory.getLogger(TimeCardApplication.class);
	   
	   public static void main(String[] args) {
	      logger.info("Welcome to the aplication");
	      logger.warn("this is a warn message");
	      logger.error("this is a error message");
		  SpringApplication.run(TimeCardApplication.class, args);
	}
	   
	   @Bean
		public Docket postApi() {
			return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(metadata()).select().paths(regex("/leave.*")).build();
		}
		
		/*
		 * ApiInfo method to build the description  
		 */
		private ApiInfo metadata() {
			return new ApiInfoBuilder().title("# Time Card Application #")
					.description("Leave Management").build();	
		}

}
