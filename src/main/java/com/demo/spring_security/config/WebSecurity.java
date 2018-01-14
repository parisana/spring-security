package com.demo.spring_security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	public WebSecurity(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		log.debug("**Configuring HttpSecurity**");
		http
				// all of HttpSecurity will only be applied if the URL starts with security
				// this includes HTTP Response headers, processing of username/password, CSRF, etc
					.csrf().disable()
				.headers().disable()
				.authorizeRequests()
					.antMatchers("/webjars/**", "/h2-console/**", "/signup", "/css/**").permitAll()
					.anyRequest().hasRole("USER")
				.and()
					.formLogin()
						.loginPage("/login") //add custom login page
							.permitAll()
						.failureForwardUrl("/login?error")
						.successForwardUrl("/messages")
			/*	.and()
					.oauth2Login()
						.loginPage("/login")
						.defaultSuccessUrl("/oauth2/index")*/
				.and()
					.logout()
						.permitAll();
	}
	// configure authenticationManager using the userDetailsService implementation.
	@Autowired
	protected void configureGlobal(UserDetailsService userDetailsService, AuthenticationManagerBuilder auth) throws Exception {
		log.debug("***configuring AuthenticationManagerBuilder");
		auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}
}
