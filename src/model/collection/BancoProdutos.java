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
	
	
	public boolean adicionarProduto(Produto produto) {
		// verifica se é um adm
		if(adm!=null) {
			produto.setId(idAtual++);
			produtos.add(produto);
			quantidade++;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removerProdutos(Produto produto, Object user) {
		// verifica se é um adm
		if(verificarPermissao(user)) {
			
			// verifica se o produto está no array
			if(produtos.contains(produto)) {
				produtos.remove(produto);
				quantidade--;
				return true;
			} else {
				return false
			}
			
		} else {
			return false;
			
		}
	}
	
	public boolean editarProdutos(long buscarId,Produto produtoAtual, Object user) {
		if(verificarPermissao(user)) {
			for(Produto produto:produtos) {
				if(produto.getId() == buscarId) {
				produto.setNome(produtoAtual.getNome());
				produto.setDescricao(produtoAtual.getDescricao());
				produto.setQuantidade(produtoAtual.getQuantidade());
				produto.setPreco(produtoAtual.getPreco());
				System.out.println("Produto editdo.");
				// finalizar o looping
				return true;
				}
				
				return false;
			}
		}
		
		return false;
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
	
	public boolean verificarPermissao(Object user) {
		if(user instanceof Administrador) {
			Administrador adm = (Administrador) user;
			
			if(adm.getCargo() == AdministradorCargo.GERENTE || adm.getCargo() == AdministradorCargo.SUPERVISOR_PRODUTOS) {
				return true;
			}
		}
		
		return false;
	}
	
	
}
