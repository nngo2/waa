package edu.mum.cs545;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.bank.domain.Account;
import cs545.bank.service.IAccountService;

@Named
@ApplicationScoped
public class AccountListBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private IAccountService  accountService;	
	
	public AccountListBean() {
		super();
	}

	@Inject
	public AccountListBean(IAccountService  accountService) {
		this.accountService = accountService;
		
		// create 2 accounts;
		accountService.createAccount(1263862, "Frank Brown");
		accountService.createAccount(4253892, "John Doe");
		// use account 1;
		accountService.deposit(1263862, 240);
		accountService.deposit(1263862, 529);
		accountService.withdrawEuros(1263862, 230);
		// use account 2;
		accountService.deposit(4253892, 12450);
		accountService.depositEuros(4253892, 200);
		accountService.transferFunds(4253892, 1263862, 100, "payment of invoice 10232");
	}
	
	public Collection<Account> getAccounts() {
		return accountService.getAllAccounts();
	}
	
}
