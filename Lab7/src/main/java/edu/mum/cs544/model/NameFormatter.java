package edu.mum.cs544.model;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class NameFormatter implements Formatter<String> {

	@Override
	public String print(String name, Locale locale) {
		return (name != null? name.toUpperCase() : name);
	}

	@Override
	public String parse(String name, Locale locale) throws ParseException {
		return name;
	}

}
