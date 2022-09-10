package com.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dao.UserDaoImpl;
import com.helper.JwtHelper;
import com.service.CustomerUserDetailService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private CustomerUserDetailService customerDetailService;  
	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// get header of jwt
		// check if starting with bearer
		// if yes validate
		System.out.println(request+"request");
		System.out.println(response+"response");
		System.out.println(filterChain+"filter");
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7); // excluding Bearer
			try {
				username = this.jwtHelper.extractUsername(jwtToken);

			} catch (Exception e) {
				e.printStackTrace();
			}

			UserDetails userDetails = this.customerDetailService.loadUserByUsername(username);
			// security check, getAuthentication should nit be null
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UsernamePasswordAuthenticationToken userPwdAuth = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());

				// getAuthorities -- returns authentication token
				userPwdAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(userPwdAuth);
			} else {
				LOG.error("TOKEN not validated");
				//throw new IOException("Token not valid");
				//System.out.println("Token is not validated");
				//return;
			}
		
		}
		filterChain.doFilter(request, response);

	}
//	
//	@Bean//to prevent cors error
//	public CorsConfigurationSource corsConfigurationSource() {
//		
//		final CorsConfiguration config=new CorsConfiguration();
//		config.setAllowedOrigins(Arrays.asList("http://localhost:4200/*"));
//		
//		config.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS","DELETE","PUT","PATCH"));
//		config.setAllowCredentials(true);
//		config.setAllowedHeaders(Arrays.asList("Authorization","Cache-Control","Content-Type"));
//		
//		final UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", config);
//		return source;
//		
//	}
	

}
