<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CTF :: Sobre</title>
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
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">CTF</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/">Inicio</a></li>
				<li><a href="/adminlogin">Administrador</a></li>
				<li><a href="/userlogin">Usuario</a></li>
				<li class="active"><a href="/sobre">Sobre</a></li>
			</ul>
		</div>
		</nav>

		<div class="jumbotron">
			<h1>CTF</h1>
			<h3>Controle, Finanças e Financeiro</h3>
			<h4>Controle e  Gerencie suas tarafas, agenda de contatos e gastos financeiros</h4>
		</div>
		
		<hr/>
		
		<div class="texto">
			<p>Esta aplicação tem como objetivo, o uso de ferramentas e conceitos aprendidos ao longo de estudos; <br/>
			 De forma objetiva e clara, o sistema realiza cadastro de usuários através de um <b>Administrador</b>,  o mesmo possui acesso a realizar alterações dos próprios dados,
			 como também de seus usuários (<b>CRUD</b>).<br/><br/>
			 
			 Os <b>Usuários</b> por sua vez, possuem acesso as alterações de seus dados, e de outros recursos oferecidos pelo sistema <b>(Agenda de Contatos - Tarefas - Controle Financeiro)</b>
			 
			 <br/><br/>
			 
			 Abaixo temos 
			 
			</p>
			<ul>
				<li>Persistência de Dados com JPA2 +  Hibernate</li>
				<li>Páginas em JSP</li>
				<li>Framework de produtividade MVC: Spring MVC</li>
				<li>WebServices JAX:WS - Arquitetura SOAP</li>
				<li>Javascript</li>
				<li>JQuery</li>
				<li>BootStrap 3</li>
				<li>BD: MySQL</li>
			</ul>
			
					
		</div>
	</div>
</body>
</html>

<style>
*{
	font-family: 'Comfortaa', cursive;
	font-style: italic;
}
</style>