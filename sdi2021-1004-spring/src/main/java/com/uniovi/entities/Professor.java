package com.uniovi.entities;

public class Professor {
	
	private Long id;
	
	private String dni;
	private String nombre;
	private String apellidos;
	private String categoria;
	
	public Professor() {
		
	}
	
	public Professor(Long id, String dni, String nombre, String apellidos, String categoria) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.categoria = categoria;
	}
	
	public Professor(String dni, String nombre, String apellidos, String categoria) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.categoria = categoria;
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Professor [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", categoria=" + categoria
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
