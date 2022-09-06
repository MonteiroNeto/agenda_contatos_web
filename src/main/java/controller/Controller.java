package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TesteConexao
		dao.testConexao();

		// Conexao
		String action = request.getServletPath();
		System.out.println();

		// se a pagina for direcionada para o main ele ira buscar os contatos
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			newContact(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			atualizarContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	// Criar contato
	private void newContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste de recebimento de dados
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));
		// Setar as variaveis JAVA BEANS é um model
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// Invocar o método inserirContato() da classe DAO passando o objeto contato
		dao.inserirContato(contato);
		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");

	}

	// exibir os contatos no AGENDA.jsp
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp");
		// criar um objeto que irá receber os dados JavaBeans DAO
		ArrayList<JavaBeans> list = dao.listarContatos();
		System.out.println(list.size());

		// encaminhar a lista ao documento AGENDA.jsp
		request.setAttribute("contatos", list);
		RequestDispatcher rDispatcher = request.getRequestDispatcher("agenda.jsp");
		rDispatcher.forward(request, response);// linha que de fato envia os dados para o arquivo AGENDA.jsp

	}

	// Editar contato
	private void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato que será editado
		String idcon = request.getParameter("idcon");

		// Setar a variavel JavaBeans model
		contato.setIdcon(idcon);

		// Execultar metodo SelecionarContato da class (DAO)
		dao.selecionarContato(contato);

		// Teste recebimento
		System.out.println(contato.getIdcon());
		System.out.println(contato.getNome());
		System.out.println(contato.getFone());
		System.out.println(contato.getEmail());

		// Setar os atributos do formulario com o conteudo do obejto JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());

		// Encaminhar os dados ao documento editar.jsp
		RequestDispatcher rDispatcher = request.getRequestDispatcher("editar.jsp");
		rDispatcher.forward(request, response);

	}

	private void atualizarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// Execultar metodo de alterar o contato
		dao.alterarContato(contato);

		// redirecionar para o documento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");

	}

}
