package com.miniBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.miniBank.model.UsersAccounts;
import com.miniBank.repos.UserAccountRepository;

@Controller
public class AccountController {

	@Autowired
	private UserAccountRepository userAccountRepo;

	@RequestMapping("/createaccount")
	public String showAccount() {
		return "createaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public @ResponseBody UsersAccounts account(@RequestBody UsersAccounts accountData) {
		if (accountData.getUserId() != null) {
			if (accountData.getAccountNo() != null) {
				if (accountData.getBalance() >= 0) {
					if (accountData.getCurrency() != null) {
						userAccountRepo.save(accountData);
					}
				}
			}
		}
		return accountData;
	}
	//test for find all users accounts
	@GetMapping(path = "/allaccounts")
	public @ResponseBody Iterable<UsersAccounts> getAllUsers() {
		return userAccountRepo.findAll();
	}
}
