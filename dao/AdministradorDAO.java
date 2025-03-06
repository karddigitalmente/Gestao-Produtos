package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Administrador;
import util.ConexaoBanco;

public class AdministradorDAO {
	
	public static void salvar(Administrador adm) {
		String sql = "INSERT INTO Pessoa(id, nome, cpf, senha, cargo) VALUES(DEFAULT, ?, ?, ?, ?)";
		
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)) {
			
			statement.setString(1, adm.getNome());
			statement.setString(2, adm.getCpf());
			statement.setString(3, adm.getSenha());
			statement.setString(4, "administrador");
			statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar o administrador: " + e.getMessage());
		}
		
	}
	
}
