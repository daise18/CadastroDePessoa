<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border=3> 
		<tr>
			<th>Nome</th>
			<th>CPF</th>
			<th>RG</th>
			<th>Endereço</th>
			<th>CEP</th>
			<th>E-mail</th>
			<th>Celular</th>
			<th>Data de nascimento</th>
			<th>Sexo</th>
		</tr>

		<c:forEach items="${pessoas}" var="pessoa">
			<tr>
				<td>${pessoa.nome}</td>
				<td>${pessoa.cpf}</td>
				<td>${pessoa.rg}</td>
				<td>${pessoa.endereco}</td>
				<td>${pessoa.cep}</td>
				<td>${pessoa.email}</td>
				<td>${pessoa.celular}</td>
				<td>${pessoa.dataNasc}</td>
				<td>${pessoa.sexo}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>