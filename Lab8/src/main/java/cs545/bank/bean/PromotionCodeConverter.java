package cs545.bank.bean;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named("promotionCodeConverter")
@ApplicationScoped
public class PromotionCodeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String input) {
		if (input != null) {
			int idx = input.toString().lastIndexOf("-");
			String amtString = input.toString().substring(idx + 1);
			int amt = Integer.parseInt(amtString);
			if (amt >= 0) {
				return new Integer(amt);
			}	
		} 
		
		return new Integer(0);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object output) {
		return "promo-" + output;
	}

}
