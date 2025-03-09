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
		String query = "insert into Produto (id, nome, descricao, quantidade, valor) values (?, ?, ?, ?, ?)";
		
		// executa a query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			// substitui o "?"
			statement.setString(1, Long.toString(produto.getId()));
			statement.setString(2, produto.getNome());
			statement.setString(3, produto.getDescricao());
			statement.setString(4, Integer.toString(produto.getQuantidade()));
			statement.setString(5, Double.toString(produto.getValorProduto()));
			
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
		double valor;

		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);){
			
			statement.setString(1, Long.toString(idProduto));
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				nome = result.getString("nome");
				descricao = result.getString("descricao");
				quantidade = result.getInt("quantidade");
				valor = result.getDouble("valor");
				
				// criar um produto com os valores pegados do banco
				produto = new Produto(idProduto, nome, descricao, quantidade, valor);
				System.out.println(produto);
				return;
			}
	
			System.out.println("Não foi encontrado um produto com esse id!");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar o produto: " + e.getMessage());
		}
		
	}
}
