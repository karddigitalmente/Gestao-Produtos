package model.entity;

public class Usuario extends Pessoa {
	
	private int vendasTotais;
	private Turno turno;
	
	public Usuario(String nome, Turno turno) {
		super(nome);
		this.turno = turno;
	}
	
	public Usuario(long id, String nome, String cpf, String senha, Turno turno) {
		super(id, nome, cpf, senha);
		this.turno = turno;
	}

	public int getVendasTotais() {
		return vendasTotais;
	}

	public void setVendasTotais(int vendasTotais) {
		this.vendasTotais = vendasTotais;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
}
