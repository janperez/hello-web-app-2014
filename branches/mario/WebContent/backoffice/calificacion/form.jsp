
<%@include file="../includes/head.jsp" %>
<%@include file="../includes/nav.jsp" %>
       

	<%@page import="com.ipartek.formacion.helloweb.Rol"%>
	<%@page import="com.ipartek.formacion.helloweb.bean.Calificacion"%>
	<%@page import="com.ipartek.formacion.helloweb.Constantes"%>
	 
	<%
		//Crear Persona nueva
		Calificacion cal =(Calificacion) request.getAttribute(Constantes.ATT_PERSONA);
		//inicializar variables para el formulario
		String cabecera= "Crear nueva Persona";
		String buttonValue = "Crear";
		boolean isNew = false;
		String op = Constantes.OP_UPDATE;
		
		//nueva persona
		if( cal == null ){
		    cal = new Calificacion(0);
		    isNew= true;
		    op=Constantes.OP_CREATE;
		//modificar persona
		}else{
		    cabecera = "Modificar " + cal.getDescripcion();
		    buttonValue = "Modificar";
		}
	%>
	<p class="top-margin">
		<a href="<%=request.getContextPath()+"/"+Constantes.CONTROLLER_CALIFICACION %>" class="btn btn-default btn-ms" title="volver" >
			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> Volver
		</a>
	</p>
	<div class="panel panel-primary ">
	  <div class="panel-heading">
		 <h1 class="panel-title"> <%=cabecera %></h1> 
	  </div>
	  <div class="panel-body">

		<form action="<%= request.getContextPath()+"/"+ Constantes.CONTROLLER_PERSONA %>" method="post">
			<div class="form-group">
				
				<input type="text" name="id" readonly value="<%= cal.getId() %>" class="form-control">
			</div>
			<div class="form-group">
				<label>Valor</label>
				<input type="text" name="valor" value="<%= cal.getValor() %>" class="form-control">
			</div>
			<div class="form-group">
				<label>Descripcion</label>
				<input type="number" name="descripcion" value="<%= cal.getDescripcion() %>" min="0" class="form-control">
			</div>
			
			<input type="hidden" name="<%=Constantes.OP_KEY %>" value="<%= op %>" >			
			
			<div class="form-group">
				<input type="submit" value="<%=buttonValue %>" class="btn btn-primary btn-outline">
			</div>
		</form>
		
        </div>
		<% if (!isNew){ %>
		    <form action="<%=request.getContextPath()+"/"+ Constantes.CONTROLLER_PERSONA %>" method="post">
			
			<input type="hidden" name="id" value="<%= cal.getId() %>">
			<input type="hidden" name="<%=Constantes.OP_KEY %>" value="<%=Constantes.OP_DELETE%>">
			<div class="form-group">
				<input type="submit" value="Eliminar" class="btn btn-danger btn-outline left-margin">
			</div>
		</form>
		<%} %>	 
	
	</div>
	</div><!-- Fin panel content -->
<%@include file="../includes/footer.jsp" %>   
