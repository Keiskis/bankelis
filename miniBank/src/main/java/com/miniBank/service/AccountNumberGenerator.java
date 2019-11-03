package com.miniBank.service;

import java.util.Date;

public class AccountNumberGenerator {
	
public static String generat(){
	String accountNumber = "LT1230000" + (int)(new Date().getTime()/1000);
	return accountNumber;
}
}
