package model.collection;

import java.util.ArrayList;

import dao.UsuarioDAO;
import model.entity.Administrador;
import model.entity.AdministradorCargo;
import model.entity.Usuario;

public class BancoUsuarioPadrao {
	
	private int quantidade;
	
	// Id gerenciado pelo programa
	private long idAtual = 1;
	private ArrayList<Usuario> usuarios;
	
	public BancoUsuarioPadrao() {
		quantidade = 0;
		usuarios = carregar();
		
	}
	
	public void adicionar(Usuario usuario, Object user) {
		// Id gerenciado pelo programa
		if(user instanceof Administrador) {
			Administrador adm = (Administrador) user;
			if(adm.getCargo() == AdministradorCargo.GERENTE) {
				usuario.setId(idAtual++);
				usuarios.add(usuario);
				UsuarioDAO.salvar(usuario);
				
				quantidade++;
			}
		}
		
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
		
		// Para não printar o newLine ao chegar no último produto
		int count = 1;
		
		for(Usuario usuario : usuarios) {
			resultado.append(usuario.getId() + " " + usuario.getNome() + " " + usuario.getCpf() + " " + usuario.getSenha() + " " + usuario.getTurno() );
			
			// Comentário acima kk
			if(count < quantidade) {
				resultado.append("\n");
			}
						
			count++;
		}
		
		return resultado.toString();	
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
	
	//Decidir se vai juntar com produto ou deixar separado.
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
