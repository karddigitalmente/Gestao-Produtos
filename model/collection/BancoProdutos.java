package model.collection;

import java.util.ArrayList;

import model.entity.Produto;

public class BancoProdutos {
	private int quantidade;
	
	// Id gerenciado pelo programa
	private long idAtual = 1;
	private ArrayList<Produto> produtos;
	
	public BancoProdutos() {
		quantidade = 0;
		produtos = new ArrayList<Produto>();
	}
	
	public void adicionar(Produto produto) {
		
		// Id gerenciado pelo programa
		produto.setId(idAtual++);
		
		produtos.add(produto);
		quantidade++;
	}
	
	public void remover(Produto produto) {
		produtos.remove(produto);
		
		quantidade--;
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
		
		// Para poder não printar o newLine quando chegar no último produto
		int count = 1;
		
		for(Produto produto : produtos) {
			resultado.append(produto.getId() + " " + produto.getNome() + " " + produto.getDescricao() + " " + produto.getQuantidade());
			
			// Comentário acima kk
			if(count < quantidade) {
				resultado.append("\n");
			}
			
			count++;
		}
		
		return resultado.toString();	
	}
}
