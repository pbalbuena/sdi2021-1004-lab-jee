package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Departamento;
import com.uniovi.repositories.DepartamentosRepository;

@Service
public class DepartamentoService {
	@Autowired
	private DepartamentosRepository repo;
	
	public List<Departamento> getDepartamentos() {
		List<Departamento> deplist = new ArrayList<Departamento>();
		repo.findAll().forEach(deplist::add);
		return deplist;

	}

	public Departamento getDepartamento(Long id) {
		return repo.findById(id).get();
	}

	public void addDepartamento(Departamento d) {
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		repo.save(d);

	}

	public void deleteDepartamento(Long id) {
		repo.deleteById(id);
	}
}
