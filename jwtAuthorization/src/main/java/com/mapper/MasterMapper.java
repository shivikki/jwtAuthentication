package com.mapper;
import java.util.Map;

import com.modal.Role;
import com.modal.User;
public class MasterMapper {

	public User mapUser(Map<String,Object> userMap) {
		User user=new User();
		user.setId((int) userMap.get("id"));
		user.setUsername((String) userMap.get("username"));
		user.setPassword((String) userMap.get("password"));
		user.setFirstName((String) userMap.get("firstName"));
		user.setLastName((String) userMap.get("lastName"));
		user.setEmail((String) userMap.get("email"));
		user.setPhone((String) userMap.get("phone"));
		user.setEnabled((boolean) userMap.get("enabled"));
		return user;
	}
	
	public Role mapRole(Map<String,Object> roleMap) {
		Role role=new Role();
		role.setRoleId((int) roleMap.get("role_id"));
		role.setRoleName((String) roleMap.get("role_name"));
		return role;
	}
}
