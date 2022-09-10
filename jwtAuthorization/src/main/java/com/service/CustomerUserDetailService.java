package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.modal.User;
import com.queryConst.QueryConstant;
import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.mapper.MasterMapper;

@Service
public class CustomerUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateNamed;
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerUserDetailService.class);
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// if username is Durgesh then only valid user
		//return user class
//		if(username.equals("Durgesh")) {
//			//User calss not created then can user User Obj of spring security. 
//			//Select User of 3 arg
//			return new User("Durgesh","Durgesh",new ArrayList<>());
//		}
//		else {
//			throw new UsernameNotFoundException("Not valid user");
//		}
		
		User user = getUser(email);
		if (user == null) {
			System.out.println("User not found");
			LOG.error("No user found with this credentials");
			throw new UsernameNotFoundException("Not valid user");

		}
		LOG.info("loadUserByUsername md " + user);
		return (UserDetails) user;
		
	}
	
	public User getUser(String email) {
		User getUser = new User();
		try {
			List<Map<String, Object>> userData = jdbcTemplate.queryForList(QueryConstant.FIND_USER_BY_EMAIL, email);
			if (userData.size() > 0) {
				for (Map<String, Object> u : userData) {
					getUser = (User) new MasterMapper().mapUser(u);
				}

			}

		} catch (Exception e) {
			LOG.error("Exception in getUserByEmail" + e);
		}
		// if user already exists with email null value returned
		System.out.println(getUser + "getby email+ method");
		return getUser;
	}
}
