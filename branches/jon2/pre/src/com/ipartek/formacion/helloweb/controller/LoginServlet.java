package com.ipartek.formacion.helloweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.helloweb.Constantes;
import com.ipartek.formacion.helloweb.bean.Mensaje;
import com.ipartek.formacion.helloweb.bean.Persona;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	RequestDispatcher dispatch = null;
	HttpSession session = null;
       
    //parametros
    String pUser = null;
    String pPass = null;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recuperar session
		session = request.getSession();
		
		//recoger parametros del login
		getParameters(request);
		
		//validar el usuario
		validateUser(request);		
		
		//despachar o servir JSP
		dispatch.forward(request, response);	
			
	}

	/**
	 * Comprobar los datos del Login y mirar si el rol es "administrador" o "usuario"
	 * 
	 * <ol>
	 *  <li>Usuario: ir a saluda </li>
	 * 	<li>Administrador: ir a backoffice </li>	   
	 *  <li>Si no Validado: retornar al login </li>
	 * </ol>
	 * 
	 * 
	 */
	private void validateUser(HttpServletRequest request) {
		
		//Usuario: ir a saluda 
		if ( Constantes.USER_USER_NAME.equals(pUser) && 
				 Constantes.USER_USER_PASS.equals(pPass)	){
				//correcto: rediriguir a saludo.jsp
				dispatch = request.getRequestDispatcher(Constantes.JSP_SALUDO);
				//guardar usuario en session
				//TODO recuperar usuario de la BBDD
				Persona p = new Persona(pUser, 0);
				session.setAttribute(Constantes.USER_SESSION, p);
				
		//Administrador: ir a backoffice 		
		}else if ( Constantes.USER_ADMIN_NAME.equals(pUser) && 
				 Constantes.USER_ADMIN_PASS.equals(pPass)	 ){
			
			dispatch = request.getRequestDispatcher(Constantes.JSP_BACK_INDEX );
			//guardar usuario en session
			//TODO recuperar usuario de la BBDD
			Persona p = new Persona(pUser, 0);
			p.setRol(Persona.Rol.ADMINISTRADOR);
			session.setAttribute(Constantes.USER_SESSION, p);
			
		//Si no Validado: retornar al login
		}else{	
			//incorrecto: enviar de nuevo a login.jsp
			dispatch = request.getRequestDispatcher(Constantes.JSP_LOGIN);
			Mensaje msg = new Mensaje( Constantes.MSG_LOGIN_INCORRECT, Mensaje.MSG_TYPE_DANGER );
			request.setAttribute( Constantes.MSG_KEY,  msg );
		}	
		
	}

	/**
	 * Recoger parametros de request
	 */
	private void getParameters(HttpServletRequest request) {
		pUser = request.getParameter( Constantes.PARAMETRO_USER );
		pPass = request.getParameter( Constantes.PARAMETRO_PASS );
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
