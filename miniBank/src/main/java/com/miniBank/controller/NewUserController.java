package com.miniBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.miniBank.model.Users;
import com.miniBank.repos.UserRepository;
import com.miniBank.service.CurrentUser;

@Controller
public class NewUserController {
	CurrentUser currentUser = CurrentUser.getInstance();
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping("/newuser")
	public String showCreateAccount() {
		return "newuser";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public @ResponseBody Users user(@RequestBody Users user) {
		if (user.getName() != null && user.getName().length() > 2) {
			if (user.getLastName() != null && user.getLastName().length() > 2) {
				if (user.getUserName() != null && user.getUserName().length() > 4) {
					if (user.getPassword() != null && user.getPassword().length() > 6) {
						userRepo.save(user);
						currentUser.setId(user.getId());
						currentUser.setName(user.getName());
					}
				}
			}
		}
		return user;
	}
	//test for find all users
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Users> getAllUsers() {
		return userRepo.findAll();
	}
}
