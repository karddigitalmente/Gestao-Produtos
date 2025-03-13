package model.collection;

import java.util.ArrayList;

import dao.AdministradorDAO;
import dao.BancoProdutosDAO;
import model.entity.Produto;
import model.entity.ProdutoAtributos;
import model.entity.Administrador;
import model.entity.AdministradorAtributos;
import model.entity.AdministradorCargo;

public class BancoProdutos {
	private int quantidade;
	private static long idAtual = 1;
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
	
	public boolean editar(ProdutoAtributos atributo, Produto produto, Object valor, Object user, ArrayList<Administrador> banco) {
		if(permissaoProduto(user)) {
			if(banco.contains(user)) {
				
				System.out.println("Aqui");
				
				switch(atributo) {
				
				case nome:
					produto.setNome((String) valor);
					break;
				case descricao:
					produto.setDescricao((String) valor);
					break;
				
				case quantidade:
					produto.setQuantidade((int) valor);
					break;
				
				case valorProduto:
					produto.setValorProduto((double) valor);
					break;
				
				default:
					System.err.println("Passe um Atributo válido");
				}
				
				try {
					
					System.out.println(produto.toString());
					
					System.out.println("AAAAAAAAAIn");
					produtos.set(produtos.indexOf(produto), produto);
					BancoProdutosDAO.editar(atributo, (String) valor, produto.getId());
					
					System.out.println("Produto editado com sucesso!");
					
				} catch(IndexOutOfBoundsException e) {
					System.err.println("Produto inválido");
				}
				
				return true;
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
		ArrayList<Produto> produtos = BancoProdutosDAO.carregar();
		
		if(produtos.size() > 0) {
			//faz o idAtual ser = ao maior +1
			idAtual = produtos.get(0).getId() + 1;
		}
		
		return produtos;
	}
	
}
