/*
 * Copyright (c) 2017 Yodlee, Inc. All Rights Reserved.
 */


package beans;

public class Account {
	int id;
	String accountName;
	String accountNumber;
	String accountStatus;
	String accountType;
	String CONTAINER;
	Balance balance;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCONTAINER() {
		return CONTAINER;
	}
	public void setCONTAINER(String cONTAINER) {
		CONTAINER = cONTAINER;
	}
	public Balance getBalance() {
		return balance;
	}
	public void setBalance(Balance balance) {
		this.balance = balance;
	}
	
	
	
}
