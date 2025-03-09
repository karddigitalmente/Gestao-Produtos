package model.entity;

public class Produto {
	
	private long id;
	private String nome;
	private String descricao;
	private int quantidade;
	private double valorProduto;
	
	public Produto() {}
	
	public Produto(Long id, String nome, String descricao, int quantidade, double valorProduto) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valorProduto = valorProduto;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}
}
