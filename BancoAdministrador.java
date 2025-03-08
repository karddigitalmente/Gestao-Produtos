package model.collection;

import java.util.ArrayList;

import dao.AdministradorDAO;
import model.entity.Administrador;

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
		AdministradorDAO.salvar(administrador);
		
		quantidade++;
	}
	
	public boolean remover(Administrador administrador) {
		if(administradores.contains(administrador)) {
			administradores.remove(administrador);
			AdministradorDAO.remover(administrador.getId());
			
			quantidade --;
			
			return true;
		}
		
		return false;
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
	
	// Só pra facilitar o desenvolvimento caso esqueça que o toString tbm lista kk
	public void listar() {
		this.toString();
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
}
