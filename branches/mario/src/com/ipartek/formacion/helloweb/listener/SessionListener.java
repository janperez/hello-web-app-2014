package com.ipartek.formacion.helloweb.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.ipartek.formacion.helloweb.Constantes;
import com.ipartek.formacion.helloweb.bean.Persona;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener,
	HttpSessionAttributeListener {

    private static Logger log = Logger.getLogger("ACCESOS");

    /* Eventos sobre los atributos de HttpSession */

    /**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se) {
	if (Constantes.USER_SESSION.equalsIgnoreCase(se.getName())) {
	    log.trace("attributeRemoved");
	}

    }

    /**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se) {
	if (Constantes.USER_SESSION.equalsIgnoreCase(se.getName())) {
	    log.trace("attributeAdded");
	    gestionContadorUsuarios(se.getSession(), true);
	}
    }

    /**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se) {
	if (Constantes.USER_SESSION.equalsIgnoreCase(se.getName())) {
	    log.trace("attributeReplaced");
	    gestionContadorUsuarios(se.getSession(), true);
	}
    }

    /* Ciclo de vida de HttpSession */

    /**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se) {
	// marcar el tiempo de expiracion 30 minutos
	HttpSession session = se.getSession();
	// session.setMaxInactiveInterval(60 * 30);
	session.setMaxInactiveInterval(3);
	log.trace("Nueva session Id: " + session.getId() + " - "
		+ session.getMaxInactiveInterval() + " s ");

    }

    /**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se) {
	HttpSession session = se.getSession();
	String motivo = "";

	if (session.getAttribute(Constantes.USER_LOGOUT_PETICION) != null) {
	    motivo = "Logout voluntario ";
	} else {
	    motivo = "Expiracion";
	}

	if (null != session.getAttribute(Constantes.USER_SESSION)) {
	    Persona usuario = (Persona) session
		    .getAttribute(Constantes.USER_SESSION);
	    motivo += " usuario " + usuario.toString();
	    gestionContadorUsuarios(session, false);
	} else {
	    motivo += " usuario nulo";
	}
	log.info(motivo);
    }

    private synchronized void gestionContadorUsuarios(HttpSession session,
	    boolean isSumar) {
	ServletContext sc = session.getServletContext();

	Persona usuario = (Persona) session
		.getAttribute(Constantes.USER_SESSION);
	if (usuario != null) {
	    switch (usuario.getRol()) {
	    case ADMINISTRADOR:
		int contAdmin = (Integer) sc
			.getAttribute(Constantes.USER_ADMIN_CONT);
		contAdmin = (isSumar) ? ++contAdmin : --contAdmin;
		sc.setAttribute(Constantes.USER_ADMIN_CONT, contAdmin);
		if (isSumar) {
		    log.trace(">> Nuevo Administrador en session");
		} else {
		    log.trace("<< Sale Administrador en session");
		}
		break;

	    case USUARIO:
		int contUser = (Integer) sc
			.getAttribute(Constantes.USER_USER_CONT);
		contUser = (isSumar) ? ++contUser : --contUser;
		sc.setAttribute(Constantes.USER_USER_CONT, contUser);
		if (isSumar) {
		    log.trace(">> Nuevo Usuario en session");
		} else {
		    log.trace("<< Sale Usuario en session");
		}
		break;
	    }
	}

    }
}
