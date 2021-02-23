package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorService;

@Component
public class ProfessorAddFormValidator implements Validator {
	@Autowired
	private ProfessorService pservice;

	@Override
	public boolean supports(Class<?> aClass) {
		return Professor.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Professor p = (Professor) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");

		if(p.getDni().length()!=9) {
			errors.rejectValue("dni", "Error.signup.dni.length");
		}
		if(p.getDni().length()==9) {
			Character last = p.getDni().substring(p.getDni().length()-1).charAt(0);
			if(!Character.isLetter(last)) {
				errors.rejectValue("dni", "Error.dni.endInLetter");
			}
		}
		
		if (pservice.getProfessorByDni(p.getDni()) != null) {
			errors.rejectValue("dni", "Error.signup.dni.duplicate");
		}
		if (p.getNombre().length() < 5 || p.getNombre().length() > 24) {
			errors.rejectValue("nombre", "Error.signup.name.length");
		}
		if (p.getApellidos().length() < 5 || p.getApellidos().length() > 24) {
			errors.rejectValue("apellidos", "Error.signup.lastName.length");
		}
		if (!(p.getCategoria().equals("A") || p.getCategoria().equals("B") || p.getCategoria().equals("C")) ) {
			errors.rejectValue("categoria", "Error.professor.add.categoria");
		}
	}
}