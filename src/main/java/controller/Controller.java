package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;

@WebServlet(urlPatterns = {"/Controller", "/main"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DAO dao = new DAO();
	
	
    public Controller() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TesteConexao
		dao.testConexao();
		
		//Conexao 
		String action = request.getServletPath();
		System.out.println();
		
		//se a pagina for direcionada para o main ele ira buscar os contatos
		if(action.equals("/main")) {
			contatos(request, response);
		}
	}

	
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("agenda.jsp");
	}
	
	
}
