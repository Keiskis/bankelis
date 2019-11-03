package com.miniBank.service;

public class CurrentUser {
	private static CurrentUser currentUser;
	private Integer id;
	private String name;
	private String acountNo;
	private String acountCur;
	private double balance;
	
	private  CurrentUser() {
	}
	
	public static synchronized CurrentUser getInstance() {
		if(currentUser == null) {
			currentUser = new CurrentUser();
		}
		return currentUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcountNo() {
		return acountNo;
	}

	public void setAcountNo(String acountNo) {
		this.acountNo = acountNo;
	}

	public String getAcountCur() {
		return acountCur;
	}

	public void setAcountCur(String acountCur) {
		this.acountCur = acountCur;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setLogout() {
		id = null;
		name = "";
		acountNo = "";
		acountCur = "";
		balance = 0.0;
	}
}
