package model;

public abstract class Pessoa {
	
	private long id;
	private String nome;
	private String cpf;
	private String senha;
	
	public Pessoa() {}
	
	public Pessoa(String nome) {
		this.nome = nome;
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
		
		if(cpf.toCharArray().length < 11) {
			return false;
		}
		
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
		
		if(senha.toCharArray().length < 8) {
			return false;
		}
		
		this.senha = senha;
		
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("%d %s %s %s", id, nome, cpf, senha);
	}
	
}
