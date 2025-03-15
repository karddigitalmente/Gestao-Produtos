package model.collection;

import java.util.ArrayList;

import dao.UsuarioDAO;
import model.entity.Administrador;
import model.entity.AdministradorCargo;
import model.entity.Usuario;
import model.entity.UsuarioAtributos;

public class BancoUsuarioPadrao {
	
	private int quantidade;
	
	// id gerenciado pelo programa
	private static long idAtual = 1;
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
	
	public Usuario buscarId(long id) {
		for(Usuario user: usuarios) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public boolean editar(UsuarioAtributos atributo, Usuario usuario, Object valor, Object user, ArrayList<Usuario> banco) {
		if(permissaoUsuario(user)) {
			
			if(banco.contains(user)) {
				System.out.println("aqui");
				
				switch(atributo) {
				
				case nome:
					usuario.setNome((String) valor);
					break;
				case cpf:
					usuario.setCpf((String) valor);
					break;
				
				case senha:
					usuario.setSenha((String) valor);
					break;
				
				case salario:
					usuario.setSalario((double) valor);
					break;
					
				case vendasTotais:
					usuario.setVendasTotais((int) valor);
					break;
					
				case comissao:
					usuario.setComissao((double) valor);
					break;
					
				case taxaVenda:
					usuario.setTaxaVenda((float) valor);
					break;
				
				default:
					System.err.println("Passe um Atributo válido");
				
				//Troca os administradores
			}
				try {
					usuarios.set(usuarios.indexOf(usuario), usuario);
					UsuarioDAO.editar(atributo, (String) valor, usuario.getId());
					
					return true;
					
				} catch(IndexOutOfBoundsException e) {
					System.err.println("Administrador inválido");
				}
			
			return false;
			
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
	
	public static ArrayList<Usuario> carregar(){
		ArrayList<Usuario> usuarios = UsuarioDAO.carregar();
		
		if(usuarios.size() > 0) {
			//faz o idAtual ser = ao maior +1
			idAtual = usuarios.get(0).getId() + 1;
		}
		
		return usuarios;
	}
	
}
