package cs545.bank.bean;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("depositAmountValidator")
@ApplicationScoped
public class DepositValidator implements Validator {
	private String INVALID_DEPOSIT_AMOUNT = "Deposit amount must be greater than 100";	
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		boolean valid = false;
		
		double amt = Double.parseDouble(object.toString());	
		if (amt > 100) {
			valid = true;
		}
		
		if (!valid) {
			FacesMessage msg = new FacesMessage(INVALID_DEPOSIT_AMOUNT);
			context.addMessage("", msg);
			throw new ValidatorException(msg);
		}
	}
}
