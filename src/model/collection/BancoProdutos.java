package model.collection;

import java.util.ArrayList;

import dao.BancoProdutosDAO;
import model.entity.Produto;
import model.entity.Administrador;
import model.entity.AdministradorCargo;

public class BancoProdutos {
	private int quantidade;
	private long idAtual = 1;
	// array para armazenar os produtos
	private ArrayList<Produto> produtos;
	
	public BancoProdutos() {
		quantidade = 0;
		produtos = carregar();
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
			resultado.append(produto.getId() + " | " + produto.getNome() + " | " + produto.getDescricao() + " | " + produto.getQuantidade() + " | " + produto.getValorProduto());
			
			// evita pular linha no ultimo produto
			if(count<quantidade){
				resultado.append("\n");
			}
			
			count++;
		}
		return resultado.toString();
	}
	
	
	public boolean adicionarProduto(Produto produto, Object user) {
		// verifica se é um adm
		if(permissaoProduto(user)) {
			produto.setId(idAtual++);
			produtos.add(produto);
			BancoProdutosDAO.salvar(produto);
			quantidade++;
			return true;
		}
		
		return false;
	}
	
	
	public boolean removerProdutos(Produto produto, Object user) {
		// verifica se é um adm
		if(permissaoProduto(user)) {
			// verifica se o produto está no array
			if(produtos.contains(produto)) {
				produtos.remove(produto);
				BancoProdutosDAO.remover(produto.getId());
				quantidade--;
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	public boolean editarProduto(long buscarId,Produto produtoAtual, Object user) {
		if(permissaoProduto(user)) {
			for(Produto produto:produtos) {
				if(produto.getId() == buscarId) {
					produto.setNome(produtoAtual.getNome());
					produto.setDescricao(produtoAtual.getDescricao());
					produto.setQuantidade(produtoAtual.getQuantidade());
					produto.setValorProduto(produtoAtual.getValorProduto());
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean permissaoProduto(Object user) {
		if(user instanceof Administrador) {
			Administrador adm = (Administrador) user;
			
			if(adm.getCargo() == AdministradorCargo.GERENTE || adm.getCargo() == AdministradorCargo.SUPERVISOR_PRODUTOS) {
				return true;
			}
		}
		
		return false;
	}
	
	public void listar() {
		System.out.println(toString());
	}
	
	public boolean vendaProduto(long idProduto, double valorProduto, long idUsuario) {
		BancoUsuarioPadrao bancoUsuario = new BancoUsuarioPadrao();
		
		for(Produto produto:produtos) {
			if(produto.getId() == idProduto && produto.getQuantidade() > 0) {
				produto.setQuantidade(produto.getQuantidade() - 1);
				
				if(produto.getQuantidade() == 0) {
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
	
	public static ArrayList<Produto> carregar(){
		return BancoProdutosDAO.carregar();
	}
	
}
