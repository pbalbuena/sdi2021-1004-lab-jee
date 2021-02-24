package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Departamento;
import com.uniovi.services.DepartamentoService;
import com.uniovi.validators.DepartamentoAddValidator;

@Controller
public class DepartamentosController {
	@Autowired
	private DepartamentoService service;
	
	@Autowired
	private DepartamentoAddValidator validator;

	@RequestMapping("/departamento/list")
	public String getList(Model model) {
		model.addAttribute("depList", service.getDepartamentos());
		return "departamento/list";
	}
	
	@RequestMapping(value = "/departamento/add")
	public String getDepartamento(Model model) {
		model.addAttribute("departamento", new Departamento());
		return "departamento/add";
	}
	

	@RequestMapping(value = "/departamento/add", method = RequestMethod.POST)
	public String setDepartamento(@Validated Departamento d, BindingResult result) {
		validator.validate(d, result);
		if(result.hasErrors()) {
			return "departamento/add";
		}
		
		service.addDepartamento(d);
		return "redirect:/departamento/list";
	}

	@RequestMapping("/departamento/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("dep", service.getDepartamento(id));
		return "departamento/details";
	}
	
	@RequestMapping("/departamento/delete/{id}")
	public String deleteDepartamento(@PathVariable Long id) {
		service.deleteDepartamento(id);
		return "redirect:/departamento/list";
	}
	
	@RequestMapping(value = "/departamento/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("dep", service.getDepartamento(id));
		return "departamento/edit";
	}

	@RequestMapping(value = "/departamento/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Departamento p) {
		p.setId(id);
		service.addDepartamento(p);
		return "redirect:/departamento/details/" + id;
	}

}
