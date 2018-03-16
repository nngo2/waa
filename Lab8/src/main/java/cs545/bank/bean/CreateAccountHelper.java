package cs545.bank.bean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.bank.domain.Account;
import cs545.bank.service.IAccountService;

@Named
@ApplicationScoped
public class CreateAccountHelper {
	@Inject
	private IAccountService  accountService;	
	
	public String create(CreateAccountBean acct) {
		acct.setMessage("");
		//if (acct.getDeposit()> 0 && acct.getAcctNumber() > 0) {
			if (!acct.isAcctCreated()) {
				accountService.createAccount(acct.getAcctNumber(), acct.getCustomer());
				acct.setAcctCreated(true);
			}
			accountService.deposit(acct.getAcctNumber(), acct.getDeposit() + acct.getPromotionCode());
			Account newAcct = accountService.getAccount(acct.getAcctNumber());
			acct.setBalance(newAcct.getBalance());			
			return "success";
		//} else {
		//	acct.setMessage("Account number/deposit amount is not valid (must be greater than zero)");
		//	return "error";			
		//}
	}	
}
