package com.service;

import java.util.List;

import com.modal.ResultResponse;
import com.modal.Role;
import com.modal.User;

public interface UserService {

	public User getUserByEmail(String email);

	public User deleteUserById(int id);

	public List<Role> getUserRoles(String email);

	public User getByEmail(String email);

	// create user - 1 user can have multiple roles
	public ResultResponse createUser(User user, List<Role> roleList);

}
