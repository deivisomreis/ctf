<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CTF :: ${user.name}</title>
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
		<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">CTF ::  ${user.name}</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="/admin/home">Inicio</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Admin<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/admin/show">Visualizar Dados</a></li>
						<li><a href="/admin/edit">Alterar</a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Usuario<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/admin/user/register">Novo Usuario</a></li>
						<li><a href="/admin/user/list">Mostrar Usuarios</a></li>
					</ul></li>
				<li><a href="/admin/exit">Sair</a></li>
			</ul>
		</div>
		</nav>
	</div>
</body>
</html>

<style>
* {
	font-family: 'Muli';
}

form {
	margin: auto;
	width: 450px;
}
</style>