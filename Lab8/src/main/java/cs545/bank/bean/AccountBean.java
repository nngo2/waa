package cs545.bank.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.bank.domain.Account;
import cs545.bank.service.IAccountService;

@Named
@SessionScoped
public class AccountBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private IAccountService  accountService;	
	
	private String message;
	
	private long acctNumber;
	
	private String customer;
	
	private double balance;
	
	private double txAmount;
	
	public AccountBean() {
		super();
	}

	@Inject
	public AccountBean(IAccountService  accountService) {
		this.accountService = accountService;
	}
	
	public void init() {
		message = "";
		txAmount = 0;
		Account acct = accountService.getAccount(acctNumber);
		if (acct != null) {
			acctNumber = acct.getAccountnumber();
			customer = acct.getCustomer().getName();	
			balance = acct.getBalance();
		} 
	}
	
	public void deposit () {
		Account acct = accountService.getAccount(acctNumber);
		if (acct != null && txAmount > 0) {
			acct.deposit(txAmount);
			balance = acct.getBalance();
		}	
	}
	
	public void withdraw () {
		Account acct = accountService.getAccount(acctNumber);
		if (acct != null && txAmount > 0) {
			acct.withdraw(txAmount);
			balance = acct.getBalance();
		}			
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

	public double getTxAmount() {
		return txAmount;
	}

	public void setTxAmount(double txAmount) {
		this.txAmount = txAmount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
