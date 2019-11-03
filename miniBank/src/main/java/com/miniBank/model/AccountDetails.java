package com.miniBank.model;

public class AccountDetails {
	
	private String date;
	private String description;
	private String sum;
	
	public AccountDetails() {
		super();
	}
	
	public AccountDetails(String date, String description, String sum) {
		super();
		this.date = date;
		this.description = description;
		this.sum = sum;
	}


	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
}
