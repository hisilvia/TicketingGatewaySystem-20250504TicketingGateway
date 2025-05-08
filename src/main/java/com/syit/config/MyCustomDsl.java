package com.syit.config;

import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
	private boolean flag;

	@Override
	public void init(HttpSecurity http) throws Exception {
		// any method that adds another configurer
		// must be done in the init method
		
		/*Cross-Site Request Forgery(CSRF)
		-> Spring Security can protect against CSRF attacks
		-> Embed additional authentication data/token into all HTML forms
		-> On subsequent requests, web app will verify token before processing
		-> Primary use case is traditional web applications (HTML forms etc...)
		
		When to use CSRF Protection? 148->7mins21s
		*/
		http.csrf().disable();
	}

	public MyCustomDsl flag(boolean value) {
		this.flag = value;
		return this;
	}

	public static MyCustomDsl customDsl() {
		return new MyCustomDsl();
	}
}