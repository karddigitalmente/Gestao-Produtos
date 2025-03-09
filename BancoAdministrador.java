package model.collection;

import java.util.ArrayList;

import dao.AdministradorDAO;
import model.entity.Administrador;
import model.entity.AdministradorAtributos;
import model.entity.AdministradorCargo;

public class BancoAdministrador {
	
	private int quantidade;
	
	// Id gerenciado pelo programa
	private long idAtual = 1;
	private ArrayList<Administrador> administradores;
	
	public BancoAdministrador() {
		quantidade = 0;
		administradores = carregar();
	}
	
	public boolean adicionar(Administrador administrador, Object user) {
		
		if(checarPermissao(user)) {
			// Id gerenciado pelo programa
			administrador.setId(idAtual++);
			administradores.add(administrador);
			AdministradorDAO.salvar(administrador);
			
			quantidade++;
			
			return true;
		}
		return false;		
	}
	
	public boolean remover(Administrador administrador, Object user) {
		if(checarPermissao(user)) {
			if(administradores.remove(administrador)) {
				AdministradorDAO.remover(administrador.getId());
				
				quantidade --;
				
				return true;
			}
		}
		
		System.out.println("Erro remover");
		return false;
	}
	
	public boolean editar(AdministradorAtributos atributo, Administrador administrador, Object valor, Object user) {
		if(checarPermissao(user)) {
			if(administradores.contains(administrador)) {
				
				switch(atributo) {
				
				case nome:
					administrador.setNome((String) valor);
					break;
				case cpf:
					administrador.setCpf((String) valor);
					break;
				
				case senha:
					administrador.setSenha((String) valor);
					break;
				
				case cargo:
					administrador.setCargo((AdministradorCargo) valor);
					break;
				
				default:
					System.err.println("Passe um Atributo válido");
				
				//Troca os administradores
				administradores.set(administradores.indexOf(administrador), administrador);
				AdministradorDAO.editar(atributo, (String) valor, administrador.getId());
				
				return true;
			}
			
			return false;
			
			}
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
	
	public boolean checarPermissao(Object user) {
		if(user instanceof Administrador) {
			Administrador adm = (Administrador) user;
			
			if(adm.getCargo().equals(AdministradorCargo.GERENTE)) {
				return true;
			}
			
			return false;	
		}
		
		return false;
	}
	
	// Só pra facilitar o desenvolvimento caso esqueça que o toString tbm lista kk
	public void listar() {
		toString();
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
	
	public static ArrayList<Administrador> carregar(){
		return AdministradorDAO.carregar();
	}
}