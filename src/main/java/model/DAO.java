package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	
	
	//paramentros de conexao
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
    
    
    //metodo de conexao
	private Connection conectar() {
        
		try {
			//Criar instancia de conexao com o postgreeSql
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, user, pass);
			return connection;
		}catch(Exception e){
			System.out.println(e+"***ERRO2*********");
			
			return null;
		}
	}
	
	//teste de conexao
    public void testConexao() {
    	try {
    		Connection con = conectar();
    		System.out.println(con+"***CONECTADO*****************************************");
    		con.close();
    	}catch(Exception e) {
    		System.out.println(e+"***ERRO1*****************************************");
    		
    	}
     
    	
    }
    

}
