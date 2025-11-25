package com.assertiva.sailpointlab.assertiva_sailpoint_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssertivaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssertivaBackendApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<BasicAuthFilter> authFilter(BasicAuthFilter filter) {
    	FilterRegistrationBean<BasicAuthFilter> registration = new FilterRegistrationBean<>();
    	registration.setFilter(filter);
    	registration.addUrlPatterns("/*"); // Protege toda la API
    return registration;
}

}
