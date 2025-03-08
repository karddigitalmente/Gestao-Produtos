package model.entity;

public class Administrador extends Pessoa {
	private AdministradorCargo cargo;
	
	public Administrador(String nome) {
		super(nome);
	}
	
	public Administrador(long id, String nome, String cpf, String senha, AdministradorCargo cargo) {
		super(id, nome, cpf, senha);
		this.cargo = cargo;
	}

	public AdministradorCargo getCargo() {
		return cargo;
	}

	public void setCargo(AdministradorCargo cargo) {
		this.cargo = cargo;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Cargo: " + cargo;
	}
}
