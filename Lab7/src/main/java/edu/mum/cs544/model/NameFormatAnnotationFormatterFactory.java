package edu.mum.cs544.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

public class NameFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<NameFormat>{

	@Override
	public Set<Class<?>> getFieldTypes() {
		return new HashSet<>(Arrays.asList(String.class));
	}

	@Override
	public Parser<?> getParser(NameFormat arg0, Class<?> arg1) {
		return new NameFormatter();
	}

	@Override
	public Printer<?> getPrinter(NameFormat arg0, Class<?> arg1) {
		return new NameFormatter();
	}

}
