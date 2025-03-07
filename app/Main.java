package app;

import dao.AdministradorDAO;
import dao.UsuarioDAO;
import model.collection.BancoAdministrador;
import model.collection.BancoProdutos;
import model.collection.BancoUsuarioPadrao;
import model.entity.Administrador;
import model.entity.Produto;
import model.entity.Turno;
import model.entity.Usuario;

public class Main {

	public static void main(String[] args) {
		BancoProdutos produtos = new BancoProdutos();
		BancoUsuarioPadrao usuarios = new BancoUsuarioPadrao();
		BancoAdministrador administradores = new BancoAdministrador();
		
		Produto produto = new Produto("Produto1", "ProdutoTal", 15);
		produtos.adicionar(produto);
		
		produto = new Produto("Produto2", "ProdutoTal", 12);
		produtos.adicionar(produto);
		
		produto = new Produto("Produto3", "ProdutoTal", 13);
		produtos.adicionar(produto);
		
		produto = new Produto("Produto4", "ProdutoTal", 14);
		produtos.adicionar(produto);
		
		Usuario usuario = new Usuario("Usuario1", Turno.NOITE);
		usuarios.adicionar(usuario);
		
		usuario = new Usuario("Usuario1", Turno.MANHA);
		usuarios.adicionar(usuario);
		
		usuario = new Usuario("Usuario2", Turno.TARDE);
		usuarios.adicionar(usuario);
		
		usuario = new Usuario("Usuario3", Turno.TARDE);
		usuarios.adicionar(usuario);
		
		Administrador adm = new Administrador("Adm1");
		
		adm = new Administrador("Adm1");
		administradores.adicionar(adm);
		
		adm = new Administrador("Adm2");
		administradores.adicionar(adm);
		
		adm = new Administrador("Adm3");
		administradores.adicionar(adm);
		
		System.out.println(produtos.toString());
		System.out.println();
		
		System.out.println(usuarios.toString());
		System.out.println();
		
		System.out.println(administradores.toString());
		
	}

}
