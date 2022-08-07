package com.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// if username is Durgesh then only valid user
		//return user class
		if(username.equals("Durgesh")) {
			//User calss not created then can user User Obj of spring security. Select User of 3 arg
			return new User("Durgesh","Durgesh",new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("Not valid user");
		}
		
	}

}
