package cs545.bank.bean;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("promotionCodeValidator")
@ApplicationScoped
public class PromotionCodeValidator implements Validator {
	private String INVALID_PROMOTION_CODE = "Promotion code must be ended with - then number";	
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		boolean valid = false;
		
		if (object == null) {
			valid = true;
		} else {
			Integer amt = (Integer)object;
			if (amt >= 0 && amt <= 1000) {
				valid = true;
			}			
		}

		if (!valid) {
			FacesMessage msg = new FacesMessage(INVALID_PROMOTION_CODE);
			context.addMessage("", msg);
			throw new ValidatorException(msg);
		}
	}
}
