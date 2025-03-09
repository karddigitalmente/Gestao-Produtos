package app;

import java.util.Scanner;

import model.collection.BancoAdministrador;
import model.entity.Administrador;
import model.entity.AdministradorCargo;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static BancoAdministrador bancoAdm;
	static Administrador adm;
	//conta padrao
	static Administrador padrao = new Administrador("padrao","00000000000","system", AdministradorCargo.GERENTE);
	
	public static void main(String[] args) {
		bancoAdm = new BancoAdministrador();

		
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
		if(bancoAdm.getAdminsitradores().size() == 0) {
			System.out.print("Você não possui uma conta administrador, vamos criar!!\n" 
			+ "Informe seu nome: ");
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
				System.out.print("Conta criada com sucesso!");
			} else {
				System.out.print("Erro ao criar a conta.");
			}
			
		}
	}
}
		
