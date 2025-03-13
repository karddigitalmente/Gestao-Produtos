package model.collection;

import java.util.ArrayList;

import dao.AdministradorDAO;
import dao.BancoProdutosDAO;
import model.entity.Administrador;
import model.entity.AdministradorAtributos;
import model.entity.AdministradorCargo;
import model.entity.Produto;

public class BancoAdministrador {
	
	private int quantidade;
	
	// Id gerenciado pelo programa
	private static long idAtual = 1;
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
	
	public Administrador buscarId(long id) {
		for(Administrador adm: administradores) {
			if(adm.getId() == id) {
				return adm;
			}
		}
		return null;
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
					
				try {
					administradores.set(administradores.indexOf(administrador), administrador);
					AdministradorDAO.editar(atributo, (String) valor, administrador.getId());
				} catch(IndexOutOfBoundsException e) {
					System.err.println("Administrador inválido");
				}
				
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
		
		for(Administrador administrador : administradores) {
			resultado.append(administrador.getId() + " " + administrador.getNome() + " " + administrador.getCpf() + " " + administrador.getSenha());
			
			resultado.append("\n");
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
		System.out.println(toString());
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public ArrayList<Administrador> getAdministradores() {
		return administradores;
	}
	
	public void setUsuarios(ArrayList<Administrador> administrador) {
		this.administradores = administrador;
	}
	
	public static ArrayList<Administrador> carregar(){
		ArrayList<Administrador> administradores = AdministradorDAO.carregar();
		
		
		if(administradores.size() > 0) {
			//faz o idAtual ser = ao maior +1
			idAtual = administradores.get(0).getId() + 1;
		}
		
		return administradores;
	}
}
