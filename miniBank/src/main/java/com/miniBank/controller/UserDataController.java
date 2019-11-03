package com.miniBank.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.miniBank.service.AccountNumberGenerator;
import com.miniBank.service.CurrentUser;

@Controller
public class UserDataController {

	CurrentUser currentUser = CurrentUser.getInstance();

	@RequestMapping(value = "/getuserdata", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> userData(@RequestBody Map<String, String> query) {
		Map<String, String> userData = new HashMap<>();
		if (currentUser.getId() != null) {
			if (query.get("userdata") != null && query.get("userdata").equalsIgnoreCase("createaccount")) {
				userData.put("userId", String.valueOf(currentUser.getId()));
				userData.put("name", currentUser.getName());
				userData.put("accountNo", AccountNumberGenerator.generat());
			} else if (query.get("userdata") != null && query.get("userdata").equalsIgnoreCase("getdata")) {
				userData.put("userId", String.valueOf(currentUser.getId()));
				userData.put("name", currentUser.getName());
				userData.put("accountNo", currentUser.getAcountNo());
				userData.put("accountCur", currentUser.getAcountCur());
				userData.put("balance", String.valueOf(currentUser.getBalance()));
			} else if (query.get("userdata") != null && query.get("userdata").equalsIgnoreCase("logout")) {
				userData.put("userId", "null");
				userData.put("name", "");
				userData.put("accountNo", "");
				userData.put("accountCur", "");
				userData.put("balance", "0.0");
				currentUser.setLogout();
			}
		}
		return userData;
	}
}
