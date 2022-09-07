<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
//out.println(lista.size()+"JS.jsp");//Teste recebimento
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda</title>
<link rel="icon" href="images/ic_fave.png">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="new_contact.html" class="btn1">Novo contato</a>
	<a href="relatorio" class="btn2">Relatório</a>
	<table id="tabela">
		<thead>
			<th>Id</th>
			<th>Nome</th>
			<th>Telefone</th>
			<th>Email</th>
			<th>Opções</th>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
				
				/* a (?) dentro do SELEC no TD, está informando que será passando um paramentro de uma classe para outro, nesse exemplo,
				estaremos passando um ID de um contato.*/
			%>
			<tr>
				<td><%=lista.get(i).getIdcon()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getFone()%></td>
				<td><%=lista.get(i).getEmail()%></td>
				<td>
					<a href="select?idcon=<%=lista.get(i).getIdcon()%>" class="btn1">Editar</a>
					<a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)" class="btn2">Excluir</a>
				</td>
			</tr>
			<%}%>
		</tbody>

	</table>
<script src="scripts/confirmador.js"></script>
</body>
</html>