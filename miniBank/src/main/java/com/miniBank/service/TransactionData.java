package com.miniBank.service;

public class TransactionData {

	private Integer id;
	private int userId;
	private double deposit;
	private double withdrawal;
	private String curUser;
	private String doUserAccountNo;
	private String curDoUser;
	private String description;
	private String date;
	
	public TransactionData(Integer id, int userId, double deposit, double withdrawal, String curUser,
			String doUserAccountNo, String curDoUser, String description, String date) {
		super();
		this.id = id;
		this.userId = userId;
		this.deposit = deposit;
		this.withdrawal = withdrawal;
		this.curUser = curUser;
		this.doUserAccountNo = doUserAccountNo;
		this.curDoUser = curDoUser;
		this.description = description;
		this.date = date;
	}

	public TransactionData() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getWithdrawal() {
		return withdrawal;
	}

	public void setWithdrawal(double withdrawal) {
		this.withdrawal = withdrawal;
	}

	public String getCurUser() {
		return curUser;
	}

	public void setCurUser(String curUser) {
		this.curUser = curUser;
	}

	public String getDoUserAccountNo() {
		return doUserAccountNo;
	}

	public void setDoUserAccountNo(String doUserAccountNo) {
		this.doUserAccountNo = doUserAccountNo;
	}

	public String getCurDoUser() {
		return curDoUser;
	}

	public void setCurDoUser(String curDoUser) {
		this.curDoUser = curDoUser;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
