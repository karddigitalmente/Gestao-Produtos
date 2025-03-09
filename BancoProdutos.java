package model.colletction;

import java.util.ArrayList;

import model.entity.Produto;
import model.entity.Administrador;

public class BancoProdutos {
	private int quantidade;
	private Administrador adm = new Administrador();
	private long idAtual = 1;
	// array para armazenar os produtos
	private ArrayList<Produto> produtos;
	
	public BancoProdutos() {
		quantidade = 0;
		produtos = new ArrayList<Produto>();
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
	@Override
	public String toString() {
		StringBuilder resultado = new StringBuilder();
		int count = 1;
		
		for(Produto produto:produtos) {
			resultado.append(produto.getId() + " | " + produto.getNome() + " | " + produto.getDescricao() + " | " + produto.getQuantidade());
			
			// evita pular linha no ultimo produto
			if(count<quantidade){
				resultado.append("\n");
			}
			
			count++;
		}
		return resultado.toString();
	}
	
	
	public void adicionarProduto(Produto produto) {
		// verifica se é um adm
		if(adm!=null) {
			produto.setId(idAtual++);
			produtos.add(produto);
			quantidade++;
			System.out.println("Produto adicionado.");
		} else {
			System.out.println("Apenas administrador tem permissão!");
		}
	}
	
	public void removerProdutos(Produto produto) {
		// verifica se é um adm
		if(adm!=null) {
			
			// verifica se o produto está no array
			if(produtos.contains(produto)) {
				produtos.remove(produto);
				quantidade--;
				System.out.println("Produto removido.");
			} else {
				System.out.println("Produto não encontrado.");
			}
			
		} else {
			System.out.println("Apenas administrador tem permissão!");
			
		}
	}
	
	public void editarProdutos(long buscarId,Produto produtoAtual) {
		if(adm!=null) {
			for(Produto produto:produtos) {
				if(produto.getId() == buscarId) {
				produto.setNome(produtoAtual.getNome());
				produto.setDescricao(produtoAtual.getDescricao());
				produto.setQuantidade(produtoAtual.getQuantidade());
				produto.setPreco(produtoAtual.getPreco());
				System.out.println("Produto editdo.");
				// finalizar o looping
				return;
				}
			
			}
		}
	}
	
	public void listar() {
		System.out.println(this.toString());
	}
	
	public boolean vendaProduto(long idProduto, double valorProduto, long idUsuario) {
		BancoUsuarioPadrao bancoUsuario = new BancoUsuarioPadrao();
		
		for(Produto produto:produtos) {
			if(produto.getId() == idProduto && produto.getQuantidade() > 0) {
				produto.setQuantidade(produto.getQuantidade() - 1);
				
				if(produto.getQuantidade == 0) {
					System.out.println("O estoque do: " + produto.getNome() + " está 0.");
				}
				
				if(bancoUsuario.comissao(idUsuario, valorProduto) && bancoUsuario.vendaQuantidade(idUsuario)) {
						return true;
				}
			}
			
			return false;
		}
		
		return false;
	}
	
}
