package com.javasampleapproach.twitterbootstrap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.javasampleapproach.twitterbootstrap.service.impl.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter  {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    return bCryptPasswordEncoder;
	}
	
	@Autowired
	CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
	
	@Bean
	public UserDetailsService mongoUserDetails() {
	    return new CustomUserDetailService();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    UserDetailsService userDetailsService = mongoUserDetails();
	    auth
	        .userDetailsService(userDetailsService).
	        passwordEncoder(passwordEncoder());

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	            .antMatchers("/").permitAll()
	            .antMatchers("/login","/register","/contactus","/connect/google").permitAll()
	            .antMatchers("/user/**").permitAll()
	            .antMatchers("/student/**").hasAuthority("STUDENT").anyRequest().authenticated()
	            .antMatchers("/teacher/**").hasAuthority("TEACHER").anyRequest().authenticated()
	            .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated()
	            .antMatchers("/mydashboard/**").hasAuthority("ADMIN").anyRequest()
	            .authenticated().and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
	            .loginPage("/login").failureUrl("/login?error")
	            .usernameParameter("email")
	            .passwordParameter("password").and()
				.rememberMe()
				.key("uniqueAndSecret")
				.rememberMeCookieName("javasampleapproach-remember-me")
				.tokenValiditySeconds(24 * 60 * 60);
	            /*.and().logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/").and().exceptionHandling();*/
	}
	
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {	    
		web
	        .ignoring()
	        .antMatchers("/resources/**","/static/bootstrap/**","/bootstrap/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
}
