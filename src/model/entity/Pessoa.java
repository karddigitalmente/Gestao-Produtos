
package model.entity;

// Classe abstrata para não ser instanciada e somente herdada
public abstract class Pessoa {
	
	private long id;
	private String nome;
	private String cpf;
	private String senha;
	
	public Pessoa() {}
	
	public Pessoa(String nome) {
		this.nome = nome;
	}
	
	public Pessoa(String nome, String cpf, String senha) {
		setId(id);
		this.nome = nome;
		setCpf(cpf);
		this.senha = senha;
	}
	
	public Pessoa(long id, String nome, String cpf, String senha) {
		setId(id);
		this.nome = nome;
		setCpf(cpf);
		this.senha = senha;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public boolean setCpf(String cpf) {
		
		// cpf tem 11 caracteres
		if(cpf.toCharArray().length < 11) {
			return false;
		}
		
		// transforma ele de 00000000000 para 000.000.000-00
		StringBuilder sb = new StringBuilder();
		
		sb.append(cpf);
		sb.insert(3, '.');
		sb.insert(7, '.');
		sb.insert(11, '-');
		
		this.cpf = sb.toString();
		
		return true;
	}

	public String getSenha() {
		return senha;
	}

	public boolean setSenha(String senha) {
		
		// senha tem que ter no min 8 chars
		if(senha.toCharArray().length < 8) {
			return false;
		}
		
		this.senha = senha;
		
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("Id: %d; Nome: %s; Cpf: %s; Senha: %s;", id, nome, cpf, senha);
	}
	
}
