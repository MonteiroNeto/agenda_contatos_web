package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	// paramentros de conexao
	private Connection connection = null;
	private final String host = "localhost";
	private final String database = "dbagenda";
	private final int port = 5432;
	private final String user = "postgres";
	private final String pass = "root";

	private String url = "jdbc:postgresql://%s:%d/%s";

	public DAO() {
		this.url = String.format(this.url, this.host, this.port, this.database);
	}

	// metodo de conexao
	private Connection conectar() {

		try {
			// Criar instancia de conexao com o postgreeSql
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, user, pass);
			return connection;
		} catch (Exception e) {
			System.out.println(e + "***ERRO2*********");

			return null;
		}
	}

	// teste de conexao
	public void testConexao() {
		try {
			Connection con = conectar();
			System.out.println(con + "***CONECTADO*****************************************");
			con.close();
		} catch (Exception e) {
			System.out.println(e + "***ERRO1*****************************************");

		}

	}

	// ***** CRUD CREATE**/
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,fone,email) values (?,?,?)";

		try {
			// abrir conexao
			Connection connection = conectar();
			// Preparar a query para a execução no banco de dados
			PreparedStatement pst = connection.prepareStatement(create);
			// subsituir os paramentros (?) pelo conteudo das variaveis JavaBeans que é o
			// model do objeto
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// execultar a query
			pst.executeUpdate();
			System.out.println("INSERIDO COM SUCESSO**********************+");
			// encerrar conexão com o banco por motivos de segurança
			connection.close();

		} catch (Exception e) {
			System.out.println(e + "**********************+");
		}
	}

	// ** CRUD READ **/
	public ArrayList<JavaBeans> listarContatos() {
		ArrayList<JavaBeans> lista = new ArrayList<>();
		String readAll = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pStatement = con.prepareStatement(readAll);
			ResultSet resultSet = pStatement.executeQuery();

			// laço abaixo irá ser execultado enquanto houver linha com contato no banco
			while (resultSet.next()) {
				// variaveis de apoio que recebe linhas do banco
				String idcon = resultSet.getString(1);
				String nome = resultSet.getString(2);
				String fone = resultSet.getString(3);
				String email = resultSet.getString(4);

				// adicionar um contato na lista
				JavaBeans item = new JavaBeans(idcon, nome, fone, email);
				lista.add(item);

			}
			con.close();
			return lista;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}

	}

	// ** CRUD UPDATE **/
	// selecionar um contato
	public void selecionarContato(JavaBeans contato) {
		String readWhere = "select * from contatos where idcon = ?";

		int idDb = Integer.parseInt(contato.getIdcon());

		try {
			Connection con = conectar();
			PreparedStatement psStatement = con.prepareStatement(readWhere);
			psStatement.setInt(1, idDb);
			// receber os dados
			ResultSet resultSet = psStatement.executeQuery();

			System.out.println(contato.getIdcon());
			// definir um objto contato com os dados recebidos
			while (resultSet.next()) {
				idDb = resultSet.getInt(1);

				contato.setIdcon(String.valueOf(idDb));
				contato.setNome(resultSet.getString(2));
				contato.setFone(resultSet.getString(3));
				contato.setEmail(resultSet.getString(4));

				System.out.println("SUCESSO********************************");
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e + "error********************************");
		}

	}

	// Editar o contato
	public void alterarContato(JavaBeans contato) {
		String updateString = "update contatos set nome=?,fone=?,email=? where idcon=?";
		int idDb = Integer.parseInt(contato.getIdcon());
		try {
			Connection con = conectar();
			PreparedStatement pStatement = con.prepareStatement(updateString);
			pStatement.setString(1, contato.getNome());
			pStatement.setString(2, contato.getFone());
			pStatement.setString(3, contato.getEmail());
			pStatement.setInt(4, idDb);
			pStatement.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e + "*********ERRO atualizar");
		}

	}

	/** CRUD DELETE **/
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";
		int idcon = Integer.parseInt(contato.getIdcon());
		try {
			Connection con = conectar();
			PreparedStatement pStatement = con.prepareStatement(delete);
			pStatement.setInt(1, idcon);
			pStatement.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e + "*******ERRO DELETAR CONTATO****************");
		}

	}

}
