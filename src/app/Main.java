package app;

import java.util.Scanner;

import model.collection.BancoAdministrador;
import model.collection.BancoUsuarioPadrao;
import model.entity.Administrador;
import model.entity.AdministradorCargo;
import model.entity.Turno;
import model.entity.Usuario;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static BancoAdministrador bancoAdm;
	static BancoUsuarioPadrao bancoUser;
	static Usuario user;
	static Administrador adm;
	//conta padrao
	static Administrador padrao = new Administrador("padrao","00000000000","system", AdministradorCargo.GERENTE);
	// garantir que o adm crie uma conta
	
	public static void main(String[] args) {
		bancoAdm = new BancoAdministrador();

		// garantir a pessoa criar a primeira conta(adm)
		if(bancoAdm.getAdminsitradores().size() == 0) {
			System.out.print("Você não possui uma conta administrador, vamos criar!!\n");
			criarAdm();
			telaAdm();
		}
			
			
			
		System.out.println("!!Bem vindo!!");
		System.out.print("1- Login\n> ");
		int escolha = sc.nextInt();
		
		switch(escolha) {
		case 1:
			criarAdm();
				
			
		
		sc.close();
		}
	}
	
	public static void criarAdm() {
		String nomeAdm, cpf, senha;
		int cargo;
		
		AdministradorCargo obj;

		// verifica se existe alguma conta de adm
		System.out.print("Informe seu nome: ");
		nomeAdm = sc.next();
		System.out.print("Informe seu cpf: ");
		cpf = sc.next();
		System.out.print("Informe sua senha: ");
		senha = sc.next();
		System.out.print("Informe o seu cargo: \n" 
						+ "1- Gerente"
						+ "\n2- Supervisor de Produtos"
						+ "\n3- Supervisor de Revendedores\n> ");
		cargo = sc.nextInt();
			if(cargo != 1) {
				System.out.print("Você não possui permissão!");
				return;
			} 
			
		obj = AdministradorCargo.GERENTE;
		adm = new Administrador(nomeAdm, cpf, senha, obj);
		if(bancoAdm.adicionar(adm, padrao)) {
			System.out.println("Conta criada com sucesso!");
		} else {
			System.out.println("Erro ao criar a conta.");
		}			
	}
	
	static void telaAdm() {
		System.out.println("Olá, " + adm.getNome());
		System.out.print("1- Gerenciar usuário"
						+ "\n2- Gerenciar produtos\n> ");
		int escolha = sc.nextInt();
		switch(escolha) {
		case 1:
			System.out.print("1- Adicionar"
							+ "\n2- Procurar"
							+ "\n3- Editar"
							+ "\n4- Remover\n> ");
			escolha = sc.nextInt();
			if(escolha == 1) {
				
			}
		}
		
	}

	static void criarUser() {
		String nome,cpf,senha;
		double salario;
		Turno turno;
		
		System.out.print("Informe o nome: ");
		nome = sc.next();
		System.out.print("Informe o cpf: ");
		cpf = sc.next();
		System.out.print("Informe a senha: ");
		senha = sc.next();
		System.out.print("Informe o salario: ");
		salario = sc.nextDouble();
		System.out.println("Informe o turno: "
						+ "\n1- Manhã"
						+ "\n2- Tarde"
						+ "\n3- Noite");
		int escolha = sc.nextInt();
		switch(escolha) {
		case 1:
			turno = Turno.MANHA;
			break;
		case 2:
			turno = Turno.TARDE;
			break;
		case 3:
			turno = Turno.NOITE;
			break;
		default:
			System.out.println("Turno inválido.");
		}
	}
}
