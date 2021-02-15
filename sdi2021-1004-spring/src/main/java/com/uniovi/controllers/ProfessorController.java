package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorService;

@RestController
public class ProfessorController {
	@Autowired
	private ProfessorService service;

	@RequestMapping("/professor/list")
	public String getList() {
		return service.getProfessors().toString();
	}

	@RequestMapping(value = "/professor/add", method = RequestMethod.POST)
	public String setProfessor(@ModelAttribute Professor prof) {
		service.addProfessor(prof);
		return "Added: " + prof.getDni() + ": " + prof.getNombre() + " " + prof.getApellidos() + " --- "
				+ prof.getCategoria();
	}

	@RequestMapping("/professor/details/{id}")
	public String getDetail(@PathVariable Long id) {
		return service.getProfessor(id).toString();
	}
	
	@RequestMapping("/professor/delete/{id}")
	public String deleteProfessor(@PathVariable Long id) {
		service.deleteProfessor(id);
		return "redirect:/professor/list";
	}

}
