package edu.mum.cs544;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LocaleController {
	@RequestMapping(value="/locale", method=RequestMethod.GET)
	public String changeLocale(@RequestParam("language") String locale) {
		if ("es".equalsIgnoreCase(locale)) {
			LocaleContextHolder.setLocale(new Locale("es-ES"));
		} else {
			LocaleContextHolder.setLocale(Locale.ENGLISH);
		}
		return "redirect:/index.jsp";
	}
}
