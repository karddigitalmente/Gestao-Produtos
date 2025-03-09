package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Produto;
import model.entity.ProdutoAtributos;
import util.ConexaoBanco;

public class BancoProdutosDAO {

	public static void salvar(Produto produto) {
		// comando pro SQL
		String query = "INSERT INTO Produto (id, nome, descricao, quantidade, valor) values (?, ?, ?, ?, ?)";
		
		// executa a query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			// substitui o "?"
			statement.setLong(1, produto.getId());
			statement.setString(2, produto.getNome());
			statement.setString(3, produto.getDescricao());
			statement.setInt(4, produto.getQuantidade());
			statement.setDouble(5, produto.getValorProduto());
			
			int linhasAfetadas = statement.executeUpdate();
			
			if(linhasAfetadas > 0) {
				System.out.println("Produto adicionado com sucesso!");
			} else {
				System.out.println("Produto não adicionado!");
			}
			
		} catch(SQLException e) {
			System.err.println("Erro ao salvar o produto: " + e.getMessage());
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
	
	public static void remover(long id) {
		String query = "delete from Produto where id=?";
		
		// Executar a query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, Long.toString(id));
			
			int linhasAfetadas = statement.executeUpdate();
			
			if(linhasAfetadas == 0) {
				System.out.println("Não foi encontrado produto com este id!");
			} else {
				System.out.println("Administrador removido.");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar o produto: " + e.getMessage());
		}
	}

	public static void filtrar(ProdutoAtributos atributos, double valor) {
		String query = "select * from Produto where " + atributos + "=?";	
		long id;
		String nome, descricao;
		int quantidade;
		
		// executar query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setDouble(1,valor);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				
				id = result.getLong("id");
				nome = result.getString("nome");
				descricao = result.getString("descricao");
				quantidade = result.getInt("quantidade");
				
				Produto produto = new Produto(id, nome, descricao, quantidade, valor);
				System.out.println(produto);
			}
				
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar o produto: " + e.getMessage());
		}
	}
	
	public static ArrayList<Produto> carregar() {
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		String query = "select * from Produto";
		
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			long id;
			String nome, descricao;
			int quantidade;
			double valor;
			
			ResultSet set = statement.executeQuery();
			
			while(set.next()) {
				
				id = set.getLong("id");
				nome = set.getString("nome");
				descricao = set.getString("descricao");
				quantidade = set.getInt("quantidade");
				valor = set.getInt("valor");
				
				produtos.add(new Produto(id, nome, descricao, quantidade, valor));
			}
			
		} catch(SQLException e) {
			throw new RuntimeException("Erro ao carregar o produto: " + e.getMessage());
		}
		
		return produtos;
	}	
}
