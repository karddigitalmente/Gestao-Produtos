package model.collection;

import java.util.ArrayList;

import dao.UsuarioDAO;
import model.entity.Administrador;
import model.entity.AdministradorCargo;
import model.entity.Usuario;

public class BancoUsuarioPadrao {
	
	private int quantidade;
	
	// id gerenciado pelo programa
	private long idAtual = 1;
	private ArrayList<Usuario> usuarios;
	
	public BancoUsuarioPadrao() {
		quantidade = 0;
		usuarios = carregar();
		
	}
	
	public boolean adicionar(Usuario usuario, Object user) {
		// id gerenciado pelo programa
		if(user instanceof Administrador) {
			Administrador adm = (Administrador) user;
			if(adm.getCargo() == AdministradorCargo.GERENTE) {
				usuario.setId(idAtual++);
				usuarios.add(usuario);
				UsuarioDAO.salvar(usuario);
				quantidade++;
				return true;
			}
		}
		
		return false;
	}
	
	public boolean editarUsuario(long buscarId, Usuario usuarioAtual, Object user) {
		if(permissaoUsuario(user)) {
			for(Usuario usuario:usuarios) {
				if(usuario.getId() == buscarId) {
					usuario.setNome(usuarioAtual.getNome());
					usuario.setCpf(usuarioAtual.getCpf());
					usuario.setSenha(usuarioAtual.getSenha());
					usuario.setTurno(usuarioAtual.getTurno());
					usuario.setSalario(usuarioAtual.getSalario());
					usuario.setTaxaVenda(usuarioAtual.getTaxaVenda());
					return true;
				}
			}
		}
		
		return false;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@Override
	public String toString() {
		StringBuilder resultado = new StringBuilder();
		
		// para não printar o newLine ao chegar no último produto
		int count = 1;
		
		for(Usuario usuario : usuarios) {
			resultado.append(usuario.getId() + " " + usuario.getNome() + " " + usuario.getCpf() + " " + usuario.getSenha() + " " + usuario.getTurno() );
			
			if(count < quantidade) {
				resultado.append("\n");
			}
						
			count++;
		}
		
		return resultado.toString();	
	}
	
	
	public boolean permissaoUsuario(Object user) {
		if(user instanceof Administrador) {
			Administrador adm = (Administrador) user;
			
			if(adm.getCargo() == AdministradorCargo.GERENTE || adm.getCargo() == AdministradorCargo.SUPERVISOR_VENDEDORES) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public boolean comissao(long id, double valorProduto) {
	
		for(Usuario usuario : usuarios) {
			
			if(usuario.getId() == id) {
				usuario.setComissao(usuario.getComissao() + valorProduto * usuario.getTaxaVenda());
				vendaQuantidade(id);
				return true;
			}
		}
		return false;
		
	}
	
	// decidir se vai juntar com produto ou deixar separado.
	public boolean vendaQuantidade(long id) {
		
		for(Usuario usuario : usuarios) {
			if(usuario.getId() == id) {
				usuario.setVendasTotais(usuario.getVendasTotais() +1);
				return true;
			}
		}
		return false;
		
	}
	
	public static ArrayList<Usuario> carregar() {
		return UsuarioDAO.carregar();
	}
	
}
