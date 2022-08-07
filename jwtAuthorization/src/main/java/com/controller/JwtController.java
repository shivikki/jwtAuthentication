package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.helper.JwtHelper;
import com.modal.JwtRequest;
import com.modal.JwtResponse;
import com.service.CustomerUserDetailService;

@RestController //tp generate token for 1st time
public class JwtController {

	@Autowired
	private CustomerUserDetailService customUserDetailsService;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@RequestMapping(value="/token",method=RequestMethod.POST)//use url for which access check is not required
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		//jwtRequest contains user data
		System.out.println(jwtRequest);
		try {
			//authenticate username pass
			this.authManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
			
			
		}catch (Exception e) {
			throw new Exception("Bad Credentials");
		}
		UserDetails userDetails= this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		//Implementations are not used directly by Spring Security for security purposes. Theysimply store user information which is later encapsulated into Authenticationobjects. This allows non-security related user information (such as email addresses,telephone numbers etc) to
		//be stored in a convenient location. 
		
		//generate token
		String token=this.jwtHelper.generateToken(userDetails);
		System.out.println("token generated"+token);
		
		//send token in json
		//{"token","value"}
		return ResponseEntity.ok(new JwtResponse(token));
		
		
		
	}
}
