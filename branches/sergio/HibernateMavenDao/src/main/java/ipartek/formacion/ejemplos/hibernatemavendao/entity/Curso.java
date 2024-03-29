package ipartek.formacion.ejemplos.hibernatemavendao.entity;

import java.util.LinkedList;
import java.util.List;

public class Curso {

	private Integer id;
	private String nombre;
	private List<Alumno> alumnos = new LinkedList<Alumno>();
	
	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	public void setAlumno(Alumno obj) {
		this.getAlumnos().add(obj);
	}

	public Curso() {
	}

	public Curso(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
