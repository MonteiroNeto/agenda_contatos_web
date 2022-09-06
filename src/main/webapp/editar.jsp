<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda</title>
<link rel="icon" href="images/ic_fave.png">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>Editar contato</h1>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input id="cx3" type="text" name="idcon" readonly value="<% out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input class="cx1" type="text" name="nome" value="<% out.print(request.getAttribute("nome"));%>"></td>
			</tr>

			<tr>
				<td><input class="cx2" type="text" name="fone" value="<% out.print(request.getAttribute("fone"));%>"></td>
			</tr>

			<tr>
				<td><input class="cx1" type="text" name="email" value="<% out.print(request.getAttribute("email"));%>"></td>
			</tr>

		</table>
		<input type="button" value="Editar" class="btn1" onclick="validar()">
	</form>


	<script src="scripts/validador.js"></script>
</body>
</html>