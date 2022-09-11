package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.modal.ResultResponse;
import com.modal.Role;
import com.modal.User;
import com.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	//make api private ie once user authenticated then only able to access api
	@RequestMapping("/welcome")
	public String welcome() {
		return "Not allowed for unauthorized user";
	}
	
	@PostMapping("/getUserByEmail")
	public User getUserByEmail(@RequestBody String email) {
		User user = new User();
		user = userService.getUserByEmail(email);
		return user;
	}
	
	@DeleteMapping("/deleteUserById/{id}")
	public User deleteUserById(@PathVariable int id) {
		User user = new User();
		user = userService.deleteUserById(id);
		return user;
	}
	
	@PostMapping("/getRoleOfUser")
	public List<Role> getUserRoles(@RequestBody String email) {
		List<Role> roleList=new ArrayList<>();
		roleList=userService.getUserRoles(email);
		return roleList;
	}
	
	@PostMapping("/addUser")
	public ResultResponse addUser(@RequestBody User user) {
		ResultResponse createUserResonse = new ResultResponse();
		//encrpyt pwd
		user.setPassword(this.bCrypt.encode(user.getPassword()));
		// Normal User Role
		Role role = new Role(102, "Normal");
		List<Role> roleList = new ArrayList<>();
		roleList.add(role);
		createUserResonse = userService.createUser(user, roleList);
		return createUserResonse;
	}
	
}
