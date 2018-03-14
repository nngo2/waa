package edu.mum.cs545;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.bank.service.IAccountService;

@Named
@RequestScoped
public class CreateAccountBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private IAccountService  accountService;		

	private String message;
	
	private long acctNumber;
	
	private String customer;
	
	public CreateAccountBean() {
		super();
	}

	@Inject
	public CreateAccountBean(IAccountService  accountService) {
		this.accountService = accountService;
	}	
	
	public void init() {
		message = "";
		acctNumber = 0;
		customer = "";
	}

	public void create() {
		accountService.createAccount(acctNumber, customer);
		message = "Account: " + acctNumber + " has been created";
		acctNumber = 0;
		customer = "";
	}	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getAcctNumber() {
		return acctNumber;
	}

	public void setAcctNumber(long acctNumber) {
		this.acctNumber = acctNumber;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
}
