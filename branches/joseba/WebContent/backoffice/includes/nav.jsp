 <!-- Navigation -->
        <%@page import="com.ipartek.formacion.helloweb.Constantes"%>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand">Backoffice <%= p.getNombre() %></a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
               <li>
               		<form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
   					 	<input type="submit" value="<fmt:message key="menu.logout"></fmt:message>" class="btn btn-lg btn-danger"/>
					</form>
               </li>
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        
                        <li>
                            <a class="active" href="<%= Constantes.CONTROLLER_PERSONA %>" title="Gestionar personas"><i class="fa fa-user fa-fw"></i><fmt:message key="nav.personas"></fmt:message></a>
                        </li>
                        <li>
                            <a class="active" href="<%= Constantes.CONTROLLER_ROLE %>" title="Gestionar roles"><i class="fa fa-user fa-fw"></i>Roles</a>
                        </li>
                        <li>
                            <a class="active" href="" title="Gestionar personas"><i class="fa fa-cog fa-fw"></i><fmt:message key="nav.ajustes"></fmt:message></a>
                        </li>                    
                       
                       
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
         <div id="page-wrapper">