package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Turno;
import model.entity.Usuario;
import util.ConexaoBanco;

public class UsuarioDAO {

	public static void salvar(Usuario user) {
		String query = "INSERT INTO Usuario(id, nome, cpf, senha, turno, salario, comissao, taxa) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		// Executar a query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, Long.toString(user.getId()));
			statement.setString(2, user.getNome());
			statement.setString(3, user.getCpf());
			statement.setString(4, user.getSenha());
			statement.setString(5, user.getTurno().toString());
			statement.setString(6, Double.toString(user.getSalario()));
			statement.setString(7, Double.toString(user.getComissao()));
			statement.setString(8, Float.toString(user.getTaxaVenda()));
		
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar o usuário: " + e.getMessage());
		}
		
	}
	
	public static void pegar(long id) {
		String query = "SELECT * FROM Usuario WHERE id=?";
		Usuario usuario;
		String nome, cpf, senha;
		Turno turno;
		double comissao;
		double salario;
		float taxa;
		
		// Executar a query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, Long.toString(id));
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				nome = result.getString("nome");
				cpf = result.getString("cpf");
				senha = result.getString("senha");
				turno = Turno.valueOf(result.getString("turno"));
				comissao = result.getDouble("comissao");
				salario = result.getDouble("salario");
				taxa = result.getFloat("taxa");
				
				usuario = new Usuario(id, nome, cpf, senha, turno, comissao, salario, taxa);
					
				System.out.println(usuario);
			} else {
				System.out.println("Usuário Não encontrado!");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar o usuário: " + e.getMessage());
		}
		
	}
	
}
