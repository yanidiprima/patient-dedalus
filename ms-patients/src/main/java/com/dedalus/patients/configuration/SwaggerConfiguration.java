package com.dedalus.patients.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value; 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/** 
 * 
 * The class <code>com.dedalus.patients.configuration.SwaggerConfiguration</code> is
 * an spring component for management swagger beans.
 * */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	 

	@Value("${swagger.package}")
	  private String SWAGGERPACKAGE;

	  @Value("${swagger.title}")
	  private String SWAGGERTITLE;

	  @Value("${swagger.description}")
	  private String SWAGGERDESCRIPTION;

	  @Value("${swagger.version}")
	  private String SWAGGERVERSION;

	  @Value("${swagger.termsofservice}")
	  private String SWAGGERTERMSOFSERVICE;

	  @Value("${swagger.name}")
	  private String SWAGGERNAME;

	  @Value("${swagger.url}")
	  private String SWAGGERURL;

	  @Value("${swagger.mail}")
	  private String SWAGGEREMAIL;

	  @Value("${swagger.license}")
	  private String SWAGGERLICENSE;

	  @Value("${swagger.license.url}")
	  private String SWAGGERLICENSEURL;

	  /**
	   * Se le indica a swagger donde estan ubicadas las clases a documentar.
	   */
	  @Bean
	  public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2).select()
	        .apis(RequestHandlerSelectors.basePackage(SWAGGERPACKAGE)).paths(PathSelectors.any())
	        .build().apiInfo(apiInfo());
	  }

	  /**
	   * Details Api
	   */
	  private ApiInfo apiInfo() {
		    ApiInfo apiInfo = new ApiInfo(SWAGGERTITLE, SWAGGERDESCRIPTION, SWAGGERVERSION,
		        SWAGGERTERMSOFSERVICE, new Contact(SWAGGERNAME, SWAGGERURL, SWAGGEREMAIL), SWAGGERLICENSE,
		        SWAGGERLICENSEURL, Collections.emptyList());
		    
		     return apiInfo;
	  }


}
