package com.uniovi.validators;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Departamento;

@Component
public class DepartamentoAddValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Departamento.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Departamento d = (Departamento) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion", "Error.empty");

		if(d.getDescripcion().length()<5 || d.getDescripcion().length() > 24) {
			errors.rejectValue("descripcion", "Error.departamento.descripcion");
		}
	}

}
