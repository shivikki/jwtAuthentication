package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Set;

import com.modal.Role;

import com.dao.UserDao;
import com.modal.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modal.Role;
import com.modal.ResultResponse;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public User deleteUserById(int id) {
		return userDao.deleteUserById(id);
	}

	@Override
	public List<Role> getUserRoles(String email) {
		return userDao.getUserRoles(email);
	}

	@Override
	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}
	

	@Override
	public ResultResponse createUser(User user, List<Role> roles) {
		return userDao.createUser(user,roles);
	}

}
