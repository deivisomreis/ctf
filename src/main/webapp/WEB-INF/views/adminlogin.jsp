<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CTF :: Admin</title>
	<link rel="stylesheet"
		href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link href='https://fonts.googleapis.com/css?family=Muli'
		rel='stylesheet' type='text/css'>
	<link href="/layout.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">CTF</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/">Inicio</a></li>
				<li class="active"><a href="/adminlogin">Administrador</a></li>
				<li><a href="/userlogin">Usuario</a></li>
				<li><a href="/sobre">Sobre</a></li>
			</ul>
		</div>
		</nav>
		
		<c:if test="${not empty erro}">
			<div class="alert alert-danger">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Erro! </strong>  ${erro}
			</div>
		</c:if>
		<form role="form" action="/adminlogin" method="post"> 
			<fieldset>
				<legend>Login</legend>
				<div class="form-group">
					<label for="usr">Usuario:</label> <input type="email"
						class="form-control" id="usr" required="true" placeholder="E-mail" name="admin">
				</div>
				<div class="form-group">
					<label for="pwd">Senha:</label> <input type="password"
						class="form-control" id="pwd" required="true" name="password" placeholder="********">
				</div>
				<button type="reset" class="btn btn-danger">Limpar</button> 
				<button type="submit" class="btn btn-success" style="margin-left: 280px">Logar</button>
			</fieldset>
		</form>
	</div>
</body>
</html>

<style>
*{font-family: 'Muli';}
form {margin: auto; width: 450px;}
</style>