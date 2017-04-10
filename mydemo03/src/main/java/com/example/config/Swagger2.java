package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * @Configuration 让spring来加载该类配置
 * @EnableSwagger2 启动Swagger2
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				   .apiInfo(apiInfo())
				   .select()
				   .apis(RequestHandlerSelectors.basePackage("com.example.web"))
				   .paths(PathSelectors.any())
				   .build();
	}
	
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				    .title("Spring Boot中使用Swagger2构建RESTful APIs")
				    .description("参考资料:http://blog.didispace.com/springbootswagger2/")
				    .termsOfServiceUrl("http://blog.didispace.com/springbootswagger2/")
				    .contact("ZXZ")
				    .version("1.0")
				    .build();
	}
}
