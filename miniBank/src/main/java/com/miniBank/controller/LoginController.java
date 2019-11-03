package com.miniBank.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.miniBank.model.Login;
import com.miniBank.model.Users;
import com.miniBank.model.UsersAccounts;
import com.miniBank.repos.UserAccountRepository;
import com.miniBank.repos.UserRepository;
import com.miniBank.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserAccountRepository userAccountRepo;
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> userData(@RequestBody Login login) {
		Users user = userRepo.findByUserNameAndPassword(login.getUserName(), login.getPassword());
		if (user != null) {
		UsersAccounts account = userAccountRepo.findByUserId(user.getId());
			return LoginService.userData(user, account);
		}
		return LoginService.userDataClear();
	}
}
