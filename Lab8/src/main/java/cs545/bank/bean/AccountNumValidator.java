package cs545.bank.bean;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("accountNumValidator")
@ApplicationScoped
public class AccountNumValidator implements Validator{
	private String INVALID_ACCT_NUM = "Account num must be five digis";	
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		boolean valid = false;
		
		long acct = Long.parseLong(object.toString());	
		if (acct > 10000) {
			valid = true;
		}
		
		if (!valid) {
			FacesMessage msg = new FacesMessage(INVALID_ACCT_NUM);
			context.addMessage("", msg);
			throw new ValidatorException(msg);
		}
	}
}
