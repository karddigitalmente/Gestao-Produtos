package app;

import java.util.Scanner;

import dao.AdministradorDAO;
import dao.UsuarioDAO;
import model.collection.BancoAdministrador;
import model.entity.Administrador;
import model.entity.AdministradorAtributos;
import model.entity.AdministradorCargo;

public class ControllerAdmin {
	ControllerUser control;
	static Scanner sc = new Scanner(System.in);
	static BancoAdministrador bancoAdm = new BancoAdministrador();
	static Administrador adm;
	static Administrador padrao = new Administrador("padrao","00000000000","system", AdministradorCargo.GERENTE);
	
	static void telaAdm() {
		System.out.println("Olá, " + adm.getNome());
		System.out.print("1- Gerenciar usuário"
						+"\n2- Gerenciar produtos\n> ");
		int escolha = sc.nextInt();
		switch(escolha) {
		case 1:
			System.out.print("1- Adicionar"
							+ "\n2- Procurar"
							+ "\n3- Editar Usuario"
							+ "\n4- Editar Administrador"
							+ "\n5- Remover\n> ");
			escolha = sc.nextInt();
			
			switch(escolha) {
			case 1:
				// usuario
				ControllerUser.criarUser();
				break;
			case 2:
				System.out.print("Informe o id: ");
				long id = sc.nextLong();
				System.out.print("Usuarios:\n");
				UsuarioDAO.pegar(id);
				System.out.print("---\n"
							+ "Administradores: ");
				AdministradorDAO.pegar(id);
				break;
			case 3:
				ControllerUser.editarUser();
				break;
			case 4:
				System.out.println("em dev");
				break;
			}
		}
	}
	
	static void criarAdm() {
		String nomeAdm, cpf, senha;
		int cargo;
		
		AdministradorCargo obj;

		// verifica se existe alguma conta de adm
		System.out.print("Informe seu nome: ");
		nomeAdm = sc.nextLine();
		System.out.print("Informe seu cpf: ");
		cpf = sc.nextLine();
		System.out.print("Informe sua senha: ");
		senha = sc.nextLine();
		System.out.print("Informe o seu cargo: \n" 
						+ "1- Gerente"
						+ "\n2- Supervisor de Produtos"
						+ "\n3- Supervisor de Vendedores\n> ");
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
	
	static void editarAdm() {
		System.out.print("Informe o id do adm a ser editado: ");
		long id = sc.nextLong();
		sc.nextLine();
		
		adm = bancoAdm.buscarId(id);
		if(adm == null) {
			System.out.print("Adm não encontrado.");
			return;
		}
		
		System.out.print("Informe o que você quer editar:\n ");
		System.out.print("1- Nome"
					+ "\n2- Cpf"
					+ "\n3- Senha"
					+ "\n4- Cargo");
		int escolha = sc.nextInt();
		sc.nextLine();
		
		AdministradorAtributos atributo;
		Object valorEditado;
		
		switch(escolha) {
		case 1:
			atributo = AdministradorAtributos.nome;
			System.out.print("Informe o novo nome: ");
			valorEditado = sc.nextLine();
			break;
		case 2:
			atributo = AdministradorAtributos.cpf;
			System.out.print("informe o novo cpf: ");
			valorEditado = sc.nextLine();
			break;
		case 3:
			atributo = AdministradorAtributos.senha;
			System.out.print("Informe a nova senha: ");
			valorEditado = sc.nextLine();
			break;
		case 4: 
			atributo = AdministradorAtributos.cargo;
			System.out.print("Informe o novo cargo:"
					+ "\n1- Gerente"
					+ "\n2- Supervisor de Produtos"
					+ "\n3- Supervisor de Vendedores");
			int escolhaCargo = sc.nextInt();
			sc.nextLine();
				switch(escolhaCargo) {
				case 1:
					valorEditado = AdministradorCargo.GERENTE;
					break;
				case 2:
					valorEditado = AdministradorCargo.SUPERVISOR_PRODUTOS;
					break;
				case 3:
					valorEditado = AdministradorCargo.SUPERVISOR_VENDEDORES;
					break;
				default:
					System.out.print("Cargo Inválido.");
					return;
				}
				break;
			default:
			System.out.println("Opção escolhida inválida!");
			return;
		}
			
		//precisa de um adm para verificar
		if(bancoAdm.editar(atributo, adm, valorEditado, adm)) {
			System.out.print("Adm editado!");
		} else {
			System.out.print("Erro ao editar usuário");
		}	
	}
	
}
