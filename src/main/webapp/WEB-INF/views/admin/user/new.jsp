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
<link href='https://fonts.googleapis.com/css?family=Comfortaa'
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
				<li><a href="/admin/home">Inicio</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Admin<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/admin/show">Visualizar Dados</a></li>
						<li><a href="/admin/edit">Alterar</a></li>
					</ul></li>
				<li class="dropdown" class="active"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Usuario<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/admin/user/register">Novo Usuario</a></li>
						<li><a href="/admin/user/list">Mostrar Usuarios</a></li>
					</ul></li>
				<li><a href="/admin/exit">Sair</a></li>
			</ul>
		</div>
		</nav>
		
		<hr/>
		
		<c:if test="${not empty sucesso}">
		<div class="alert alert-success fade in">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Status: </strong> ${sucesso}
		</div>
		</c:if>

		<c:if test="${not empty erro}">
			<div class="alert alert-danger fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Erro:</strong> ${erro}
			</div>
		</c:if>

		<form role="form" action="/admin/user/register" method="post"> 
		<input type="hidden" name="cadastrar" value="cadastrar">
			<fieldset>
				<legend>Novo Usuario</legend>
				<div class="form-group">
					<label for="usr">Usuario:</label> <input type="text"
						class="form-control" id="usr" required="true" placeholder="Nome de Usuário" name="name" value="">
				</div>
				<div class="form-group">
					<label for="pwd">Email:</label> <input type="email"
						class="form-control" id="pwd" required="true" name="email" placeholder="Seu email" value="">
				</div>
				<div class="form-group">
					<label for="pwd">CPF:</label> <input type="text"
						class="form-control" id="pwd" required="true" name="cpf" placeholder="Seu CPF" value="">
				</div>
				<div class="form-group">
					<label for="pwd">Senha:</label> <input type="password"
						class="form-control" id="pwd" required="true" name="password" placeholder="Sua senha" value="">
				</div>
				<button type="reset" class="btn btn-danger">Limpar</button>
				<button type="submit" class="btn btn-success" style="margin-left: 250px">Cadastrar</button>
			</fieldset>
		</form>

	</div>
</body>
</html>

<style>
* {
	font-family: 'Comfortaa', cursive;
	font-style: italic;
}

form {
	margin: auto;
	width: 450px;
}
</style>