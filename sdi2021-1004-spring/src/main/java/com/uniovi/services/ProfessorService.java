package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Mark;
import com.uniovi.entities.Professor;
import com.uniovi.entities.User;
import com.uniovi.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	// private List<Professor> professorList = new LinkedList<Professor>();

	@Autowired
	private ProfessorRepository repo;

	/*
	 * @PostConstruct public void init() { professorList.add(new Professor(1L,
	 * "1234567", "Juan", "Pérez", "1")); professorList.add(new Professor(2L,
	 * "6543216", "Pablo", "García", "3")); }
	 */

	public List<Professor> getProfessors() {
		List<Professor> list = new ArrayList<Professor>();
		repo.findAll().forEach(list::add);
		return list;
	}

	public Professor getProfessor(Long id) {
		return repo.findById(id).get();
	}

	public void addProfessor(Professor p) {
		repo.save(p);
	}

	public void deleteProfessor(Long id) {
		repo.deleteById(id);
	}

	public Professor getProfessorByDni(String dni) {
		return repo.findByDni(dni);
	}
	
}
