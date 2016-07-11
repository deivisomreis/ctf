<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CTF :: ${user.name}</title>
	<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
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
				<li class="dropdown"><a class="dropdown-toggle"
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
				<li  class="active" class="dropdown"><a class="dropdown-toggle"
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

		<form role="form" action="/user/financialcontrol/register" method="post"> 
		<input type="hidden" name="option" value="cadastrar">
			<fieldset>
				<legend>Novo Lançamento</legend>
				<div class="form-group">
					<label for="usr">Nome:</label> <input type="text"
						class="form-control" id="usr" required="true" placeholder="Nome do lançamento (Dívida)" name="name" value="">
				</div>
				<div class="form-group">
					<label for="pwd">Valor:</label> <input type="text"
						class="form-control" id="pwd" required="true" name="value" placeholder="Valor" value="">
				</div>
				<div class="form-group">
					<label for="pwd">Observação:</label>
					<textarea class="form-control" rows="5" name="note" placeholder="Descreva aqui detalher da dívida"></textarea>
				</div>
				<button type="reset" class="btn btn-danger">Limpar</button>
				<button type="submit" id="button_enviar" class="btn btn-success" style="margin-left: 250px">Cadastrar</button>
			</fieldset>
		</form>

	</div>
</body>
</html>

<script>

$("#button_enviar").click(function(){
	$("#pwd").val($("#pwd").val().replace(",","."));
});

</script>


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