package util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConexaoBanco {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USUARIO = "c##pedraugusto";
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
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            
            throw new RuntimeException("Erro ao conectar ao banco de dados Oracle: " + e.getMessage());
        }
    }
}
