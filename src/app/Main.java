package app;

import java.util.Scanner;

import model.collection.BancoAdministrador;
import model.entity.AdministradorCargo;
public class Main {
	static Scanner sc = new Scanner(System.in);
	static BancoAdministrador adm;
	
	public static void main(String[] args) {
		adm = new BancoAdministrador();
		
		System.out.println("!!Bem vindo!!");
		System.out.print("1-Login\n>");
		
		int escolha = sc.nextInt();
		
		switch(escolha) {
		
		case 1:
			long id;
			String nomeAdm;
			String cpf;
			String senha;
			int cargo;
			AdministradorCargo obj;
			if(adm.getAdminsitradores().size() == 0) {
				System.out.print("Vamos criar uma conta administrador?\n" 
								+ "Informe o id: ");
				id = sc.nextLong();
				sc.nextLine();
				System.out.print("Informe o seu nome: ");
				nomeAdm = sc.nextLine();
				System.out.print("Informe o seu cpf: ");
				cpf = sc.nextLine();
				System.out.print("Informe a sua senha: ");
				senha = sc.nextLine();
				System.out.print("Informe o seu cargo: \n" 
								+ "1-GERENTE"
								+ "\n2-SUPERVISOR_PRODUTOS"
								+ "\n3-SUPERVISOR_REVENDEDORES");
				cargo = sc.nextInt();
				
				sc.nextLine();
				if(cargo != 1) {
					System.out.print("Você não possui permissão!");
				}else {
					obj = AdministradorCargo.GERENTE;
				}
				
			}
		}
		
		
	}
	
}
