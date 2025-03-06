package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Administrador;
import util.ConexaoBanco;

public class AdministradorDAO {
	
	public static void salvar(Administrador adm) {
		// os ? ? ? seriam parâmetros que com o objeto 
		//PreparedStatement eu consigo inserir os valores pelo índice
		String sql = "INSERT INTO Pessoa(id, nome, cpf, senha, cargo) VALUES(DEFAULT, ?, ?, ?, ?)";
		
		// Crio o objeto connnection com a Classe de ConexaoBanco
		//Crio o objeto que mexe com os statements (seriam as ? ? ?)
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)) {
			
			// "troco" o ? pelo nome e assim vai...
			statement.setString(1, adm.getNome());
			statement.setString(2, adm.getCpf());
			statement.setString(3, adm.getSenha());
			statement.setString(4, "administrador");
			
			// Executa a query de update (qualquer comando DDL é executado pelo executeUpdate())
			statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar o administrador: " + e.getMessage());
		}
		
	}
	
}
