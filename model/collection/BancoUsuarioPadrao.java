package model.collection;

import java.util.ArrayList;

import model.entity.Usuario;

public class BancoUsuarioPadrao {
	
	private int quantidade;
	
	// Id gerenciado pelo programa
	private long idAtual = 1;
	private ArrayList<Usuario> usuarios;
	
	public BancoUsuarioPadrao() {
		quantidade = 0;
		usuarios = new ArrayList<Usuario>();
	}
	
	public void adicionar(Usuario usuario) {
		// Id gerenciado pelo programa
		usuario.setId(idAtual++);
		usuarios.add(usuario);
		
		quantidade++;
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
			resultado.append(usuario.getId() + " " + usuario.getNome() + " " + usuario.getCpf() + " " + usuario.getSenha() + " " + usuario.getTurno());
			
			// Comentário acima kk
			if(count < quantidade) {
				resultado.append("\n");
			}
						
			count++;
		}
		
		return resultado.toString();	
	}
	
}
