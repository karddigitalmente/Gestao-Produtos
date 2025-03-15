package app;

import java.util.ArrayList;
import java.util.Scanner;

import model.collection.BancoAdministrador;
import model.collection.BancoProdutos;
import model.collection.BancoUsuarioPadrao;
import model.entity.Administrador;
import model.entity.AdministradorCargo;
import model.entity.Produto;
import model.entity.Usuario;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	// bancos
	static BancoAdministrador bancoAdm = new BancoAdministrador();
	static BancoUsuarioPadrao bancoUser = new BancoUsuarioPadrao();
	static BancoProdutos bancoProduto = new BancoProdutos();
	static ArrayList<Usuario> usuarios;
	
	static Produto produto;
	static Usuario user;
	static Administrador adm;
	//conta padrao
	static Administrador padrao = new Administrador("padrao","00000000000","system", AdministradorCargo.GERENTE);
	
	public static void main(String[] args) {
		// não remova! esta linha define o id inicial como 1
		// sem ela, não será possível adicionar nada.
		padrao.setId(1);
		
		// garantir a pessoa criar a primeira conta(adm)
		if(bancoAdm.getAdministradores().size() == 0) {
			System.out.print("Você não possui uma conta administrador, vamos criar!!\n");
			ControllerAdmin.criarAdm();
			//telaAdm();
		}	
		
		System.out.println("!!Bem vindo!!");
		System.out.print("1- Login\n");
		int escolha = sc.nextInt();
		
		switch(escolha) {
		// logar
		case 1:
			int cargo;
			System.out.print("Informe o cargo:"
					+ "\n1- Gerente"
					+ "\n2- Supervisor de Produtos\n"
					+ "\n3- Supervisor de Vendedor"
					+ "\n4- Vendedor");
			cargo = sc.nextInt();
			
			switch(cargo) {
				case 1:
					//telaAdm();
					break;
				case 2:
					break;
				case 3: 
					break;
				case 4:
					break;
				default:
					System.out.print("Opção escolhida inválida!");
			}
		}
		
	sc.close();
	}		
}
