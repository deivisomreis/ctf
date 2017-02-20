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
				<li class="dropdown"  class="active"><a class="dropdown-toggle"
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
		
		<hr/>
		
		<c:if test="${not empty sucesso}">
		<div class="alert alert-success fade in">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Status: </strong> ${sucesso}
		</div>
		</c:if>
		<table class="table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Email</th>
					<th>CPF</th>
					<th>Status</th>
					<th>Registrado</th>
					<th colspan="2">Opções</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty usuarios}">
				<c:forEach items="${usuarios}" var="usuario">
					<tr>
						<td>${usuario.id}</td>
						<td>${usuario.name}</td>
						<td><a href="mailto:${usuario.email}">${usuario.email}</a></td>
						<td>${usuario.cpf}</td>
						<c:if test="${usuario.status ==true}">
							<td><a href="/admin/user/activeordesactive?id=${usuario.id}">Ativo</a></td>
						</c:if>
						<c:if test="${usuario.status ==false}">
							<td><a href="/admin/user/activeordesactive?id=${usuario.id}">Desativado</a></td>
						</c:if>
						<td><fmt:formatDate value="${usuario.registered}" pattern="dd/MM/yyyy"/></td>
						<td><a href="/admin/user/edit?id=${usuario.id}"><button type="button" class="btn btn-info">Editar</button></a></td>
						<td><a href="/admin/user/remove?id=${usuario.id}"><button type="button" class="btn btn-danger">Remover</button></a></td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty usuarios}">
					<tr>
						<td colspan="8" align="center" style="color: red; font-weight: bold;">Nenhum usuário cadastrado</td>
					</tr>
				</c:if>
			</tbody>
		</table>

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