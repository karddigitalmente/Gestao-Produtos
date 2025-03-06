package util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConexaoBanco {
	// URL da database (vc só precisa **talvez** mudar a porta que seria o 1521)
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	// troca o c##pedraugusto pelo seu usuario (o c## é do meu msm ent pode trocar)
    private static final String USUARIO = "c##pedraugusto";
    
    // Botei a senha em um arquivo e usei o método 
    //getSenha pra pegar, pq ai n mostra minha senha no git
    private static final String SENHA = getSenha();
    
    private static String getSenha() {
    	try(Scanner sc = new Scanner(new FileReader("src/util/senha.txt"))) {
    		
    		String senha = sc.nextLine();
    		return senha;
    		
    	} catch(Exception e) {
    		System.err.println(e.getMessage());
    		
    		return null;
    	}
    }
    
    
    public static Connection getConnection() {
        try {
        	// o getConnection retorna um objeto Connection, e usa os atributos para se conectar
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            
            throw new RuntimeException("Erro ao conectar ao banco de dados Oracle: " + e.getMessage());
        }
    }
}
