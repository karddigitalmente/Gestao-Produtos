package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.AdministradorAtributos;
import model.entity.Turno;
import model.entity.Usuario;
import model.entity.UsuarioAtributos;
import util.ConexaoBanco;

public class UsuarioDAO {

	public static void salvar(Usuario user) {
		String query = "INSERT INTO Usuario(id, nome, cpf, senha, turno, salario, comissao, taxa) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		// Executar a query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setLong(1, user.getId());
			statement.setString(2, user.getNome());
			statement.setString(3, user.getCpf());
			statement.setString(4, user.getSenha());
			statement.setString(5, user.getTurno().toString());
			statement.setDouble(6, user.getSalario());
			statement.setDouble(7, user.getComissao());
			statement.setFloat(8, user.getTaxaVenda());
		
			
			int linhas = statement.executeUpdate();
			
			if(linhas > 0) {
				System.out.println("Usuário adicionado com sucesso");
			} else {
				System.out.println("Usuário não adicionado");
			}
			
		} catch (SQLException e) {
			System.err.println("Erro ao salvar o usuário: " + e.getMessage());
		}
		
	}
	
	public static void pegar(long id) {
		String query = "SELECT * FROM U;suario WHERE id=?";
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
			System.err.println("Erro ao recuperar o usuário: " + e.getMessage());
		}
		
	}
	
	public static ArrayList<Usuario> carregar() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		String query = "SELECT * FROM Usuario ORDER BY id DESC";
		
	try(Connection conn = ConexaoBanco.getConnection();
		PreparedStatement statement = conn.prepareStatement(query)){
		Usuario usuario;
		String nome, cpf, senha;
		Turno turno;
		double comissao;
		double salario;
		float taxa;
		long id;
		
		ResultSet result= statement.executeQuery();
		
		while(result.next()) {
			id = result.getLong("id");
			nome = result.getString("nome");
			cpf = result.getString("cpf");
			senha = result.getString("senha");
			turno = Turno.valueOf(result.getString("turno"));
			comissao = result.getDouble("comissao");
			salario = result.getDouble("salario");
			taxa = result.getFloat("taxa");
			
			usuario = new Usuario(id, nome, cpf, senha, turno, comissao, salario, taxa);
			usuarios.add(usuario);
		}
		
		return usuarios;
		
		
	}catch(SQLException e) {
		throw new RuntimeException("Erro ao carregar usuários" + e.getMessage());
		}
	}
	
	public static void editar(UsuarioAtributos atributo, String valor, long id) {
		
		String query = "UPDATE Usuario SET " + atributo + " = ?" + " WHERE  id " + "= ?";
		
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, valor);
			statement.setLong(2,id);
			
			int linhasAfetadas = statement.executeUpdate();
			
			if(linhasAfetadas == 0) {
				System.err.println("Não foi possível alterar os dados!");
			} else {
				System.out.println("Usuário alterado!");
			}
			
		} catch (SQLException e) {
			System.err.println("Erro ao editar o usuario: " + e.getMessage());
		}
	}
	
}
