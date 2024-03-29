<%@page import="com.ipartek.formacion.helloweb.bean.Calificacion"%>
<%@include file="../includes/head.jsp" %>
<%@include file="../includes/nav.jsp" %>

<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.helloweb.Constantes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

		
	<!-- <p><a href="<%= Constantes.JSP_BACK_PERSONA_FORM%>" title="crear nueva perosna">Cree una nueva persona</a></p> -->

<%
		ArrayList<Calificacion> calificaciones = (ArrayList<Calificacion>)request.getAttribute( Constantes.ATT_CALIFICACIONES );
		if ( calificaciones == null ){
			%>
				<h2>No existe niguna calificacion</h2>
				
			<%
		}else{
			%>
			
	<table id="tabla" width="100%" cellspacing="0">
        <thead>
            <tr>
                <th>Id</th>
                <th><fmt:message key="calificacion.nota"></fmt:message></th>
                <th><fmt:message key="calificacion.descripcion"></fmt:message></th>
                <th></th>
            </tr>
        </thead>
 
 
        <tbody>
        	<c:forEach var="calificacion" begin="0" items="${calificaciones}">    	
    	
    			<tr>
	        	 	<td><a href="<%=Constantes.CONTROLLER_CALIFICACION+"?id="%>${calificacion.id}">${calificacion.id}</a></td>
	
					<td><a href="<%=Constantes.CONTROLLER_CALIFICACION+"?id="%>${calificacion.id}">${calificacion.clave}</a></td>
					<td>${calificacion.descripcion}</td>
					<td>
					
						<form action="<%=Constantes.CONTROLLER_CALIFICACION%>" method="post">
							<input type="hidden" name="id" value="${calificacion.id}"> 		
							<input type="hidden" name="<%=Constantes.OP_KEY %>" value="<%=Constantes.OP_DELETE%>">
							<input class="btn btn-outline btn-danger btn-xs" type="submit" value=<fmt:message key="boton.eliminar"></fmt:message>>
						</form>
					</td>
        	 
        		 </tr>
        	 
    
    		</c:forEach>
        </tbody>
    </table>
    
   
    
    <%} %>
			
			<form action="<%=Constantes.JSP_BACK_CALIFICACION_FORM%>" method="post">
				<button class="btn btn-primary" type="submit"><fmt:message key="calificacion.nueva"></fmt:message></button>
           </form>
           
           
           <%@include file="../includes/footer.jsp" %>