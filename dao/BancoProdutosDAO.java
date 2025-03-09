package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Produto;
import util.ConexaoBanco;

public class BancoProdutosDAO { // id, nome, descricao, quantidade

	public static void salvar(Produto produto) {
		// comando pro SQL
		String query = "insert into Produto (id, nome, descricao, quantidade) values (?, ?, ?, ?)";
		
		// executa a query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			// substitui o "?"
			statement.setString(1, Long.toString(produto.getId()));
			statement.setString(2, produto.getNome());
			statement.setString(3, produto.getDescricao());
			statement.setString(4, Integer.toString(produto.getQuantidade()));
			
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException("Erro ao salvar o produto: " + e.getMessage());
		}
	}
	
	public static void pegar(long idProduto) {
		// busca o produto pelo id
		String query = "select * from produto where id=?";
		Produto produto;
		String nome, descricao;
		int quantidade;
		
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);){
			
			statement.setString(1, Long.toString(idProduto));
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				nome = result.getString("nome");
				descricao = result.getString("descricao");
				quantidade = result.getInt("quantidade");
				
				// criar um produto com os valores pegados do banco
				produto = new Produto(idProduto, nome, descricao, quantidade);
				System.out.println(produto);
				return;
			}
	
			System.out.println("Não foi encontrado um produto com esse id!");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar o produto: " + e.getMessage());
		}
	}
	long id;
	private String nome;
	private String descricao; 
	private int quantidade;
	
	public static void filtrar(ProdutoAtributos atributos, String valor) {
		String query = "select * from Produto where " + atributos + "=?";	
		long id;
		String nome, descricao;
		int quantidade;
		
		// executar query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setLog(1,id);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				
				id = result.getLong("id");
				nome = result.getString("nome");
				descricao = result.getString("descricao");
				quantidade = result.getInt("quantidade");
				
				Produto produto = new Produto(id, nome, descricao, quantidade);
				System.out.println(produto);
			}
				
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar o produto: " + e.getMessage());
		}
	}
	
}
