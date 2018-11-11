package com.misofertas.data.model;

public class Usuario {
	private String nombre;
	private String apellido;
	private String email;
	

	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "nombre: " + nombre + " " + apellido + " email:" + email;
	}
	
}
