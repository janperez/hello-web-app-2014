<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@include file="../includes/head.jsp" %>
<%@include file="../includes/nav.jsp" %>

<%@page import="com.ipartek.formacion.helloweb.util.Rol"%>
<%@page import="com.ipartek.formacion.helloweb.bean.Persona"%>
<%@page import="com.ipartek.formacion.helloweb.Constantes"%>
	
<%
	Persona persona = (Persona) request.getAttribute(Constantes.ATTR_PERSONA);
	String buttonValue ="Crear";
	int op = Constantes.OP_UPDATE;
	boolean isNew = false;
	
	// Nueva Persona
	if (persona == null) {
		persona = new Persona("");
		isNew = true;
		op = Constantes.OP_INSERT;
	// Modificar Persona
	} else {
		buttonValue = "Modificar";
	}
%>

	<a href="<%=request.getContextPath() + "/" + Constantes.CONTROLLER_PERSONA%>" 
	   title="ir a la lista de personas">Lista Personas</a>

	<form action="<%=request.getContextPath() + "/" + Constantes.CONTROLLER_PERSONA%>" method="post">
		<div class="form-group"><input type='hidden' name='op' value=<%=op%>></div>
		
		<div class="form-group">
			<input class="form-control" type='text' name='id' readonly value='<%=persona.getId()%>'>
		</div>
		
		<div class="form-group">
			<label>Nombre</label>
			<input class="form-control" type='text' name='nombre' value="<%=persona.getNombre()%>">
		</div>
		
		<div class="form-group">
			<label>Nombre</label>
			<input class="form-control" type='number' name='edad' value="<%=persona.getEdad()%>">
		</div> 
		
		<br>
		
		<div class="form-group">
			<label>Rol</label>
			<!-- <label class="radio-inline"><input type='radio' name='rol' value='<%=Rol.ADMINISTRADOR%>'/>Administrador</label>
			<label class="radio-inline"><input type='radio' name='rol' checked value='<%=Rol.USUARIO%>'/>Usuario</label> -->
			<select class="form-control" name='rol'>
				<% for(Rol rol : Rol.values()){ 
					if(rol == persona.getRol()){
						out.println("<option selected value=" + rol + ">" + rol + "</option>");
					} else {
						out.println("<option value=" + rol + ">" + rol + "</option>");
					}
				}%>
			</select>
		</div>

		<input type='submit' class="btn btn-primary" value='<%=buttonValue%>'>
		
	</form>
	
	<% if(!isNew) { %>
		<form action='<%=request.getContextPath() + "/" + Constantes.CONTROLLER_PERSONA%>' method='post'>
			<input type="hidden" name="id" value="<%=persona.getId()%>">
			<input type='hidden' name='op' value='<%=Constantes.OP_DELETE %>'>
			<input type='submit' class="btn btn-danger" value='Eliminar'>
		</form>
	<% } %>

<%@include file="../includes/footer.jsp" %> 