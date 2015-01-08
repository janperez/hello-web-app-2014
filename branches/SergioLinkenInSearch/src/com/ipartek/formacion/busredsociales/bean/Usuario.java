package com.ipartek.formacion.busredsociales.bean;

public class Usuario 
{
	private int id;
	private String username;
	private String apellidos;
	private String password;
	private String eMail;
	private String status;
	private String timezone;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Usuario(String username, String apellidos) {
		super();
		this.username = username;
		this.apellidos = apellidos;
	}
	public Usuario(int id, String username, String apellidos) {
		super();
		this.id = id;
		this.username = username;
		this.apellidos = apellidos;
	}
	
	
	
	
	
	
	
	

}