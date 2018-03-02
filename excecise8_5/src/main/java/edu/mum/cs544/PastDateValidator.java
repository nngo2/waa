package edu.mum.cs544;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PastDateValidator implements ConstraintValidator<PastDateConstraint, LocalDate> {

	@Override
	public void initialize(PastDateConstraint date) {
	}

	@Override
	public boolean isValid(LocalDate date, ConstraintValidatorContext cxt) {
		return date != null && date.isAfter(LocalDate.now());
	}

}