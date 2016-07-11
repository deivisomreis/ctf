<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>CTF :: ${user.name}</title>
		<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<link href='https://fonts.googleapis.com/css?family=Comfortaa'	rel='stylesheet' type='text/css'>
		<link href="/layout.css" rel="stylesheet" type="text/css">
	</head>
<body>
	<div class="container">
			<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">CTF :: ${user.name}</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/user/home">Inicio</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Usuário<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/user/show">Visualizar Dados</a></li>
						<li><a href="/user/edit">Alterar</a></li>
					</ul></li>
				<li class="active" class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Tarefas<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/user/tasks/register">Nova Tarefa</a></li>
						<li><a href="/user/tasks/list">Mostrar Tarefas</a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Agenda<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/user/agenda/register">Novo Contato</a></li>
						<li><a href="/user/agenda/list">Mostrar Contatos</a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Finanças<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/user/financialcontrol/register">Novo
								Lançamento</a></li>
						<li><a href="/user/financialcontrol/list">Mostrar
								Lançamentos</a></li>
					</ul></li>
				<li><a href="/user/exit">Sair</a></li>
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
		
		<div id="form">
			<form  action="/user/tasks/update" method="post"> 
				<input type="hidden" name="id" value="${task.id}">
				<input type="hidden" name="option" value="editar">
				<fieldset>
					<legend>Editar Tarefa</legend>
					<div class="form-group">
						<label for="usr">Nome:</label> 
						<input type="text" class="form-control" id="usr" required="true" placeholder="Nome da Tarefa" name="name" value="${task.name}">
					</div>
					<div class="form-group">
						<label for="pwd">Registrado:</label> 
						<input type="text"	class="form-control" id="pwd" required="true" placeholder="Data de registro" value="<fmt:formatDate value='${task.registered}' pattern="dd/MM/yyyy HH:mm:ss" />" disabled="disabled">
					</div>
					<div class="form-group">
						<label for="pwd">Anotação:</label><br/>
						<textarea placeholder="Descreva sua Tarefa" name="note">${task.note}</textarea>
					</div>				
					<div class="form-group">
						<label for="pwd">Status:</label> 
						<input type="text" class="form-control" id="pwd" required="true" id="password" disabled="disabled" placeholder="Status da Tarefa" value="<c:if test='${task.status == true }'>Finalizada</c:if> <c:if test='${task.status == false }'>Tarefa em Aberto</c:if>"/></div>
					</div>
					<div class="form-group">
						<label for="pwd">Data Finalizado:</label> 
						<input type="text" class="form-control"  id="pwd" placeholder="Data de Finalização" value="<fmt:formatDate value='${task.complete}' pattern='dd/MM/yyyy HH:mm:ss'/>" disabled="disabled">
					</div>
					<button type="reset" class="btn btn-danger">Cancelar</button> 
					<button type="submit" class="btn btn-success" style="margin-left: 300px">Editar</button>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>

<style>
* {font-family: 'Comfortaa', cursive; font-style: italic;}

#form{margin: auto;}
</style>

<script>


</script>