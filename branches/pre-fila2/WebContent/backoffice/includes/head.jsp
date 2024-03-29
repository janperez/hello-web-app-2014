<!DOCTYPE html>
<%@page import="com.ipartek.formacion.helloweb.i18n.Idioma"%>
<%@page import="com.ipartek.formacion.helloweb.i18n.I18n"%>
<%@page errorPage="includes/error.jsp"  %>
<%@page import="com.ipartek.formacion.helloweb.util.Rol"%>
<%@page import="com.ipartek.formacion.helloweb.Constantes"%>
<%@page import="com.ipartek.formacion.helloweb.bean.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="language" value="<%= (session.getAttribute(Constantes.USER_LANGUAGE)!=null)?session.getAttribute(Constantes.USER_LANGUAGE):Idioma.INGLES.getLocale()%>" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="<%=Constantes.PROPERTY_I18N%>" /> 
<html lang="${language }">
<head>
	
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Backoffice</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() + "/" + Constantes.JSP_BACKOFFICE %>/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() + "/" + Constantes.JSP_BACKOFFICE %>/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath() + "/" + Constantes.JSP_BACKOFFICE %>/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- Datatables CSS -->
	<link href="<%=request.getContextPath() + "/" + Constantes.JSP_BACKOFFICE %>/css/plugins/dataTables.bootstrap.css" rel="stylesheet" type="text/css">
	<link href="//cdn.datatables.net/1.10.4/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
		
	<%
		//recuperar usuario de sesion
		Persona p = (Persona)session.getAttribute(Constantes.USER_SESSION);
		if((p == null) || !p.getRol().equals(Persona.Rol.ADMINISTRADOR)){
		   p = new Persona("anonimo");
		   
		   response.setStatus(response.SC_UNAUTHORIZED);
		   response.sendRedirect(request.getContextPath() + "/" + Constantes.JSP_LOGIN);
		}
	%>
    <div id="wrapper">