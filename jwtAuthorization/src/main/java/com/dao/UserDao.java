package com.dao;

import com.modal.User;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import com.modal.ResultResponse;
import org.springframework.security.core.GrantedAuthority;
import com.modal.Role;


public interface UserDao {

	public User getUserByEmail(String email);
	
	public List<Role> getUserRoles(String email);
	
	public User deleteUserById(int id);
	
	public User getByEmail(String email);
	
	public ResultResponse createUser(User user, List<Role> roles);
}
