package model.collection;

import java.util.ArrayList;

import model.entity.Administrador;
import model.entity.Usuario;

public class BancoAdministrador {

	private int quantidade;
	
	// Id gerenciado pelo programa
	private long idAtual = 1;
	private ArrayList<Administrador> administradores;
	
	public BancoAdministrador() {
		quantidade = 0;
		administradores = new ArrayList<Administrador>();
	}
	
	public void adicionar(Administrador administrador) {
		// Id gerenciado pelo programa
		administrador.setId(idAtual++);
		administradores.add(administrador);
		
		quantidade++;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public ArrayList<Administrador> getAdminsitradores() {
		return administradores;
	}
	
	public void setUsuarios(ArrayList<Administrador> administrador) {
		this.administradores = administrador;
	}
	
	@Override
	public String toString() {
		StringBuilder resultado = new StringBuilder();
		
		// Para não printar o newLine ao chegar no último produto
		int count = 1;
		
		for(Administrador administrador : administradores) {
			resultado.append(administrador.getId() + " " + administrador.getNome() + " " + administrador.getCpf() + " " + administrador.getSenha());
			
			// Comentário acima kk
			if(count < quantidade) {
				resultado.append("\n");
			}
						
			count++;
		}
		
		return resultado.toString();	
	}
}
