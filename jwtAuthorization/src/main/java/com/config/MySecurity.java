package com.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
		.antMatchers("/token","/api/addUser","/api/getUserByEmail","/api/getRoleOfUser").permitAll() //mention url for which authentication is not requird
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
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean//to prevent cors error
	public CorsConfigurationSource corsConfigurationSource() {
		
		final CorsConfiguration config=new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200/*"));
		
		config.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS","DELETE","PUT","PATCH"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Authorization","Cache-Control","Content-Type"));
		
		final UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
		
	}
	//in pom file add 2 de[endency
	
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.antMatcher("/**").exceptionHandling().authenticationEntryPoint(entryPoint).and()
//		.authorizeRequests()
//		.antMatchers("/api/getUserByEmail","/token","/api/addUser")
//		.permitAll()
//		.antMatchers("/**").denyAll().and()
//		.addFilterAt(jwtFilter, AbstractPreAuthenticatedProcessingFilter.class).exceptionHandling()
//		.and().csrf().disable().headers().disable();
//		
//	}
	
	
}
