package com.miniBank.service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.miniBank.model.Transactions;
import com.miniBank.model.UsersAccounts;

public class TransactionService {

	public static String description = "";
	static Map<String, String> userData = new HashMap<>();

	public static double balanceCounter(double balance, double deposit, double withdrawal, String curUser,
			String curDoUser) {

		CurrentUser currentUser = CurrentUser.getInstance();
		if (deposit == withdrawal)
			description = "";
		if (!curUser.equalsIgnoreCase(curDoUser)) {
			if (deposit > 0) {
				description = " (Deposit " + deposit + " " + curDoUser + ",  rate: " + exchange(curDoUser) + ")";
			}
			if (withdrawal > 0) {
				description = " (Withdrawal " + withdrawal + " " + curDoUser + ", rate: " + exchange(curDoUser) + ")";
			}
		} else {
			description = "";
		}

		balance = balance + exchange(curUser, curDoUser, deposit) - exchange(curUser, curDoUser, withdrawal);
		currentUser.setBalance(balance);
		return balance;
	}

	public static Transactions operatoinData(Transactions transaction) {
		transaction.setDeposit(exchange(transaction.getCurUser(), transaction.getCurDoUser(),
				transaction.getDeposit()));
		transaction.setWithdrawal(exchange(transaction.getCurUser(), transaction.getCurDoUser(),
				transaction.getWithdrawal()));
		transaction.setDate(operationDate());
		transaction.setDescription(transaction.getDescription() + TransactionService.description);
		return transaction;
	}

	
	
	public static double exchange(String curFrom, String curTo, double amount) {
		if (amount == 0)
			return 0;
		if (curFrom.equalsIgnoreCase(curTo))
			return amount;
		DecimalFormat df = new DecimalFormat("#.##");
		if (curFrom.equalsIgnoreCase(curTo))
			return amount;
		return Double.parseDouble((df.format((exchange(curTo) * amount) / exchange(curFrom))).replace(",", "."));
	}

	public static String operationDate() {
		LocalDateTime dateObj = LocalDateTime.now();
		DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
		return dateObj.format(formatObj);
	}

	public static Map<String, String> userData(String key, String value) {
		userData.put(key, value);
		return userData;
	}

	public static Transactions transactionDataWithdraval(TransactionData td, UsersAccounts dUserAccount) {
		Transactions transaction = new Transactions();

		transaction.setUserId(td.getUserId());
		transaction.setCurUser(td.getCurUser());
		transaction.setDoUserAccountNo(td.getDoUserAccountNo());
		transaction.setWithdrawal(td.getWithdrawal());
		transaction.setDate(TransactionService.operationDate());
		transaction.setCurDoUser(dUserAccount.getCurrency());
		transaction.setDeposit(0);
		transaction.setDescription(td.getDescription() + "(transfered to: " + td.getDoUserAccountNo() + ")");
		return transaction;
	}

	public static Transactions transactionDataDeposit(TransactionData td, UsersAccounts dUserAccount,
			UsersAccounts wUserAccount) {
		Transactions transaction = new Transactions();

		transaction.setDate(operationDate());
		transaction.setUserId(dUserAccount.getUserId());
		transaction.setCurDoUser(wUserAccount.getCurrency());
		transaction.setCurUser(dUserAccount.getCurrency());
		transaction.setDeposit(exchange(dUserAccount.getCurrency(), wUserAccount.getCurrency(), td.getWithdrawal()));
		transaction.setWithdrawal(0);
		transaction.setDoUserAccountNo(wUserAccount.getAccountNo());
		transaction.setDescription(
				td.getDescription() + "(received from: " + wUserAccount.getAccountNo() + " " + td.getDeposit() + " "
						+ wUserAccount.getCurrency() + ", (EUR) rate: " + exchange(dUserAccount.getCurrency()) + ") ");
		return transaction;
	}

	public static Map<String, String> userDataClear() {
		userData.clear();
		return userData;
	}

	// exchange course 2019-11-01
	private static double exchange(String cur) {

		switch (cur) {
		case "EUR":
			return 1.0;
		case "USD":
			return 0.909;
		case "GBP":
			return 1.176;
		case "RUR":
			return 0.015;
		}
		return 0;
	}

}
