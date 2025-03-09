package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Administrador;
import model.entity.AdministradorAtributos;
import model.entity.AdministradorCargo;
import util.ConexaoBanco;

public class AdministradorDAO {
	
	public static void salvar(Administrador adm) {
		String query = "INSERT INTO Administrador(id, nome, cpf, senha, cargo) VALUES(?, ?, ?, ?, ?)";
		
		// Executar a query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, Long.toString(adm.getId()));
			statement.setString(2, adm.getNome());
			statement.setString(3, adm.getCpf());
			statement.setString(4, adm.getSenha());
			statement.setString(5, adm.getCargo().toString());
			
			int linhaAfetada = statement.executeUpdate();
			
			if(linhaAfetada > 0){
				System.out.println("Administrador Salvo com sucesso!");
			}
			
		} catch (SQLException e) {
			System.err.println("Erro ao salvar o administrador: " + e.getMessage());
		}
		
	}
	
	public static void pegar(long id) {
		String query = "SELECT * FROM Administrador WHERE id=?";
		Administrador administrador;
		String nome, cpf, senha;
		AdministradorCargo cargo;
		
		// Executar a query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, Long.toString(id));
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				nome = result.getString("nome");
				cpf = result.getString("cpf");
				senha = result.getString("senha");
				cargo = AdministradorCargo.valueOf(result.getString("cargo"));
				
				administrador = new Administrador(id, nome, cpf, senha, cargo);
				
				System.out.println(administrador);
			}
			
			System.out.println("Não foi encontrado administrador com este id!!");
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar o administrador: " + e.getMessage());
		}
		
	}
	
	public static void remover(long id) {
		String query = "DELETE FROM Administrador WHERE id=?";
		
		// Executar a query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, Long.toString(id));
			
			int linhasAfetadas = statement.executeUpdate();
			
			if(linhasAfetadas == 0) {
				System.out.println("Não foi encontrado administrador com este id!!");
			} else {
				System.out.println("Administrador Removido com Sucesso!");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar o administrador: " + e.getMessage());
		}
	}
	
	public static void editar(AdministradorAtributos atributo, String valor, long id) {
		
		String query = "UPDATE Administrador SET " + atributo + " = " + valor + " WHERE  id " + "= ?";
		
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setLong(1,id);
			
			int linhasAfetadas = statement.executeUpdate();
			
			if(linhasAfetadas == 0) {
				System.err.println("Não foi possível alterar os dados!");
			}
			
		} catch (SQLException e) {
			System.err.println("Erro ao editar o administrador: " + e.getMessage());
		}
	}
	
	public static void filtrar(AdministradorAtributos atributo, String valor) {
		String query = "SELECT * FROM Administrador WHERE " + atributo + "=?";
		long id;
		String nome, cpf, senha;
		AdministradorCargo cargo;
		
		// Executar a query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, valor);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				
				id = result.getLong("id");
				nome = result.getString("nome");
				cpf = result.getString("cpf");
				senha = result.getString("senha");
				cargo = AdministradorCargo.valueOf(result.getString("cargo"));
				
				Administrador administrador = new Administrador(id, nome, cpf, senha, cargo);
				
				System.out.println(administrador);
			}
			
		} catch (SQLException e) {
			System.err.println("Erro ao salvar o administrador: " + e.getMessage());
		}	
	}
	
	public static ArrayList<Administrador> carregar() {
 		ArrayList<Administrador> administradores = new ArrayList<Administrador>();
 		
 		String sql = "SELECT * FROM Administrador";
 		
 		try(Connection conn = ConexaoBanco.getConnection();
 			PreparedStatement statement = conn.prepareStatement(sql)) {
 			long id;
 			String nome, cpf, senha;
 			AdministradorCargo cargo;
 			
 			ResultSet set = statement.executeQuery();
 			
 			while(set.next()) {
 				
 				id = set.getLong("id");
 				nome = set.getString("nome");
 				cpf = set.getString("cpf");
 				senha = set.getString("senha");
 				cargo = AdministradorCargo.valueOf(set.getString("cargo"));
 				
 				administradores.add(new Administrador(id, nome, cpf, senha, cargo));
 			}
 			
 		} catch(SQLException e) {
 			System.err.println(e.getMessage());
 		}
 		
 		return administradores;
 		
 	}
 	
}