package cs545.bank.dao;

import java.io.Serializable;
import java.util.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;

import cs545.bank.domain.Account;

/*@Model
@ApplicationScoped*/
public class AccountDAO implements IAccountDAO, Serializable {
	private static final long serialVersionUID = 1L;
	
	private Collection<Account> accountlist;

	public AccountDAO() {
		accountlist = new ArrayList<Account>();
	}
	
	public void saveAccount(Account account) {
		// System.out.println("AccountDAO: saving account with accountnr ="+account.getAccountnumber());
		accountlist.add(account); // add the new
	}

	public void updateAccount(Account account) {
		// System.out.println("AccountDAO: update account with accountnr ="+account.getAccountnumber());
		Account accountexist = loadAccount(account.getAccountnumber());
		if (accountexist != null) {
			accountlist.remove(accountexist); // remove the old
			accountlist.add(account); // add the new
		}

	}

	public Account loadAccount(long accountnumber) {
		// System.out.println("AccountDAO: loading account with accountnr ="+accountnumber);
		for (Account account : accountlist) {
			if (account.getAccountnumber() == accountnumber) {
				return account;
			}
		}
		return null;
	}

	public Collection<Account> getAccounts() {
		return accountlist;
	}

}
