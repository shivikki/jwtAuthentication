package com.dao;

import com.modal.ResultResponse;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mapper.MasterMapper;
import com.modal.User;
import com.queryConst.QueryConstant;
import com.modal.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateNamed;

	@Override
	public User getUserByEmail(String email) {
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
		System.out.println(getUser + "getby email+suthority");
		return getUser;
	}
	
	@Override
	public User getByEmail(String email) {
		User getUser = new User();
		System.out.println(email+" email");
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
		System.out.println(getUser + "getby email+suthority");
		return getUser;
	}
	
	
	// delete user by id
	@Override
	public User deleteUserById(int id) {
		User user = new User();
		try {

			// find user by id
			List<Map<String, Object>> userData = jdbcTemplate.queryForList(QueryConstant.FIND_USER_BY_ID, id);
			//first deleting from user_role table then from user table
			if (!userData.isEmpty()) {
				int roleDel=jdbcTemplate.update(QueryConstant.DELETE_FROM_USER_ROLE, id);
				if(roleDel>0) {
					int result = jdbcTemplate.update(QueryConstant.DELETE_USER_BY_ID, id);
					if (result > 0) {
						user.setUsername("User Deleted !!!");
					}
				}
				
			} else {
				user.setUsername("No user exist with this id.");
			}

		} catch (Exception e) {
			LOG.error("Exception in deleteUserById" + e);
		}
		return user;
	}
	
	
	@Override
	public List<Role> getUserRoles(String email) {
		
		User user=getUserByEmail(email);
		List<Role> roleList=new ArrayList<>();
		Role role=new Role();
		Map<String, Object> params = new HashMap<>();
		params.put("id", user.getId());
		List<Map<String, Object>> roleData = jdbcTemplateNamed.queryForList(QueryConstant.FIND_ROLE_TAGGED_TO_USER,
				params);

		// List<Map<String, Object>> roleData =
		// jdbcTemplate.queryForList(QueryConstant.FIND_ROLE_TAGGED_TO_USER, id);
		if (!roleData.isEmpty()) {
			for (Map<String, Object> r : roleData) {
				role = (Role) new MasterMapper().mapRole(r);
				// add role name to authority
				roleList.add(role);
			}	
		}
		return roleList;
	}
	
	// Create new user
		@Override
		public ResultResponse createUser(User user, List<Role> roles) {
			ResultResponse response = new ResultResponse();
			response.setValidationFlag(true); // by default val flag true

			boolean update = true;
			boolean userExist = false;
			boolean roleExist = false;
			User getUser = findUserBEmail(user, response);

			// calling func to check user already exists or not using email( as email is
			// unique)
			System.out.println("getUser obj from val" + getUser);
			try {
				if (getUser.getEmail() == null) {
					int result = 0;

					// create new user if email id does not exist
					result = jdbcTemplate.update(QueryConstant.CREATE_NEW_USER, user.getEmail(), user.getFirstName(),
							user.getLastName(), user.getPassword(), user.getPhone(), user.getUsername(), user.isEnabled());
					System.out.println("result " + result);

					// get newly added user bcoz user is required to insert data in user_role table
					List<Map<String, Object>> userData = jdbcTemplate.queryForList(QueryConstant.FIND_USER_BY_EMAIL,
							user.getEmail());
					System.out.println(userData + "userData created");

					if (!userData.isEmpty()) {
						for (Map<String, Object> u : userData) {
							getUser = (User) new MasterMapper().mapUser(u);
							User storeUser = new User(getUser.getId(),  null, null, null, null,null,null, roleExist);
			
							break;
						}

					}

					if (result > 0) {
						// insert userid and roleid in user_role table
						for (int i = 0; i < roles.size(); i++) {

							System.out.println("uid" + getUser.getId() + " roleid" + roles.get(i).getRoleId());
							int updateUserRole = jdbcTemplate.update(QueryConstant.INSERT_IN_USER_ROLE, getUser.getId(),
									roles.get(i).getRoleId());
							if (updateUserRole > 0) {
								System.out.println("USER ROLE UPDATED");
								response.setValidationFlag(false);
								response.setValidationStatus("User registered successfully !!!");

							}
						}
					} else {
						response.setValidationFlag(true);
						response.setValidationStatus("User not created");
					}

				} else {
					response.setValidationFlag(true);
					response.setValidationStatus("User already registered with this email.");

				}
			} catch (Exception e) {
				response.setValidationFlag(true);
				response.setValidationStatus("Something went wrong. Try again later !!!");
				LOG.error("Exception in createUser" + e);
			}
			return response;

		}
		// find user exists or not using email
		public User findUserBEmail(User user, ResultResponse response) {
			User getUser = new User();
			try {
				List<Map<String, Object>> userData = jdbcTemplate.queryForList(QueryConstant.FIND_USER_BY_EMAIL,
						user.getEmail());
				if (userData.size() > 0) {
					for (Map<String, Object> u : userData) {
						getUser = (User) new MasterMapper().mapUser(u);
					}

				}

			} catch (Exception e) {
				LOG.error("Exception in findUserByEmail" + e);
			}
			// if user already exists with email null value returned
			return getUser;
		}

}
