package com.ipartek.formacion.helloweb.filter;

import java.io.IOException;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ipartek.formacion.helloweb.bean.Mensaje;
import com.ipartek.formacion.helloweb.util.Constante;

/**
 * Servlet Filter implementation class FilterJsp
 */
public class JspFilter implements Filter {

    public FilterConfig filterConfig;
    private final static Logger log = Logger.getLogger(JspFilter.class);
    private HttpServletRequest requesthttp = null;

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
	filterConfig = null;
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(final ServletRequest request,
	    final ServletResponse response, final FilterChain chain)
	    throws IOException, ServletException {

	if (request instanceof HttpServletRequest) {
	    log.trace(((HttpServletRequest) request).getRequestURI());

	    requesthttp = (HttpServletRequest) request;

	    if (null == requesthttp.getSession().getAttribute(
		    Constante.USER_SESSION)
		    && !requesthttp.getServletPath().equals(
			    "/" + Constante.JSP_LOGIN)) {

		URL url = new URL(requesthttp.getRequestURL().toString());

		System.out.println(requesthttp.getRequestURL());
		Mensaje msg = new Mensaje(Constante.MSG_SIN_PERMISOS,
			Mensaje.MSG_TYPE_DANGER);
		request.setAttribute(Constante.MSG_KEY, msg);

		request.getRequestDispatcher("/" + Constante.JSP_LOGIN)
			.forward(request, response);

	    } else {
		chain.doFilter(request, response);
		return;
	    }
	} else {
	    log.warn("");
	}
	// pass the request along the filter chain
	chain.doFilter(request, response);
	return;
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(final FilterConfig fConfig) throws ServletException {
	this.filterConfig = fConfig;
    }

}
