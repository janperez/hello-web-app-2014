
<%@page import="com.ipartek.formacion.helloweb.Constantes"%>


<!DOCTYPE html>

<html>
 
<head>

  <meta charset="UTF-8">

  <title>Log-in</title>

  <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
  <link href="<%=Constantes.BACKOFFICE_FOLDER %>css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
   
  
	
</head>

<body>
	<%@include file="includes/alerts.jsp" %> 
  <div class="login-card ">
  
    <h1>Log-in</h1><br>
  <form action="<%=Constantes.PATH_LOGIN %>" method="post"> 
    <input type="text" name="<%=Constantes.PARAMETRO_USER %>" placeholder="Username">
    <input type="password" name="<%=Constantes.PARAMETRO_PASS %>" placeholder="Password">
    <input type="submit" name="login" class="login login-submit" value="login">
  </form>

  <div class="login-help">
    <a href="#">Register</a> • <a href="#">Forgot Password</a>
  </div> 
</div>

<!-- <div id="error"><img src="https://dl.dropboxusercontent.com/u/23299152/Delete-icon.png" /> Your caps-lock is on.</div> -->

  <script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%=Constantes.BACKOFFICE_FOLDER %>js/bootstrap.min.js"></script>
</body>


</html>