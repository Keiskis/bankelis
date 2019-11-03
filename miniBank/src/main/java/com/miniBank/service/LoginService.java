package com.miniBank.service;

import java.util.HashMap;
import java.util.Map;
import com.miniBank.model.Users;
import com.miniBank.model.UsersAccounts;

public class LoginService {

	static CurrentUser currentUser = CurrentUser.getInstance();
	static Map<String, String> userData = new HashMap<>();
	
	public static Map<String, String> userData(Users user, UsersAccounts account){

		currentUser.setId(user.getId());
		currentUser.setName(user.getName());
		currentUser.setAcountNo(account.getAccountNo());
		currentUser.setAcountCur(account.getCurrency());
		currentUser.setBalance(account.getBalance());
		userData.put("userId", String.valueOf(currentUser.getId()));
		userData.put("name", currentUser.getName());
		userData.put("accountNo", currentUser.getAcountNo());
		userData.put("accountCur", currentUser.getAcountCur());
		userData.put("balance", String.valueOf(currentUser.getBalance()));
		return userData;
	}
	
	public static Map<String, String> userDataClear()
	{
		userData.clear();
		return userData;
	}
	
}
