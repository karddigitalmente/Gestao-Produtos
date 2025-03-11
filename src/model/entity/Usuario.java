package model.entity;

public class Usuario extends Pessoa {
	
	private double salario;
	private int vendasTotais;
	private double comissao;
	private Turno turno;
	private float taxaVenda;
	
	public Usuario(String nome, String cpf, String senha, Turno turno, double salario, float taxaVenda) {
		super(nome, cpf, senha);
		this.turno = turno;
		this.salario = salario;
		this.taxaVenda = taxaVenda;
	}
	
	public Usuario(long id, String nome, String cpf, String senha, Turno turno, double salario, double comissao, float taxaVenda) {
		super(id, nome, cpf, senha);
		this.turno = turno;
		this.salario = salario;
		this.comissao = comissao;
		this.taxaVenda = taxaVenda;
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

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}
	
	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public float getTaxaVenda() {
		return taxaVenda;
	}

	public void setTaxaVenda(float taxaVenda) {
		this.taxaVenda = taxaVenda;
	}
	
}
