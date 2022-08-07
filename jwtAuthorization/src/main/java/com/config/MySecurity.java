package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.service.CustomerUserDetailService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class MySecurity extends WebSecurityConfigurerAdapter{//2 imports
	 @Autowired
	 private CustomerUserDetailService customUserDetailService;
	 
	 @Autowired
	 private JwtAuthenticationFilter jwtFilter;
	 
	 @Autowired
	 private AuthenticationEntryPoint entryPoint;
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf() 
		.disable()
		.cors()
		.disable()
		.authorizeRequests()
		.antMatchers("/token").permitAll()
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling().authenticationEntryPoint(entryPoint);
		//url defined - which should be permitted
		//csrf disabled
		//cors disable 
		//through antMatcher specify which all url weil be permitted
		//anyRewuest () all other request needsa to be authenticated
		//and() - if any other config needs to br done
		//exceptionHandling - handle unauthorized requests
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// mode of authentication
		auth.userDetailsService(customUserDetailService);
	}

	@Bean //Indicates that a method produces a bean to be managed by the Spring container. 
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}
	
	//in pom file add 2 de[endency
}
