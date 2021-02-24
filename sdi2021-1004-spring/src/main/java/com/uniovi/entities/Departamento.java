package com.uniovi.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departamento {
	@Id @GeneratedValue
	private Long id;
	private String descripcion;
	
	@OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
	private Set<Professor> professors;
	
	public Departamento() {
		
	}

	public Departamento(Long id, String descripcion, Set<Professor> professors) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.professors = professors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(Set<Professor> professors) {
		this.professors = professors;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", descripcion=" + descripcion + ", professors=" + professors + "]";
	}
	
	
	
}
