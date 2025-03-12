package app;

import java.util.Scanner;

import dao.UsuarioDAO;
import dao.AdministradorDAO;
import model.collection.BancoAdministrador;
import model.collection.BancoProdutos;
import model.collection.BancoUsuarioPadrao;
import model.entity.Administrador;
import model.entity.AdministradorCargo;
import model.entity.Produto;
import model.entity.Turno;
import model.entity.Usuario;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	static BancoAdministrador bancoAdm = new BancoAdministrador();
	static BancoUsuarioPadrao bancoUser = new BancoUsuarioPadrao();
	static BancoProdutos bancoProduto = new BancoProdutos();
	static Produto produto;
	static Usuario user;
	static Object us;
	static Administrador adm;
	//conta padrao
	static Administrador padrao = new Administrador("padrao","00000000000","system", AdministradorCargo.GERENTE);
	
	public static void main(String[] args) {
		
		// garantir a pessoa criar a primeira conta(adm)
		if(bancoAdm.getAdminsitradores().size() == 0) {
			System.out.print("Você não possui uma conta administrador, vamos criar!!\n");
			criarAdm();
			telaAdm();
		}	
			
		System.out.println("!!Bem vindo!!");
		System.out.print("1- Login\n");
		int escolha = sc.nextInt();
		
		switch(escolha) {
		// logar
		case 1:
			int cargo;
			System.out.print("Informe o cargo: \n"
					+ "1- Gerente\n"
					+ "2- Supervisor de Produtos\n"
					+ "3- Supervisor de Vendedor"
					+ "4- Vendedor");
			cargo = sc.nextInt();
			
			switch(cargo) {
				case 1:
					telaAdm();
					break;
				case 2:
					
					break;
				case 3: 
					
					break;
				case 4:
					telaVendedor();
					break;
				default:
					System.out.print("Opção escolhida inválida!");
			}
		}
		
	sc.close();
	}
	
	
	
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
				criarUser();
			case 2:
				System.out.print("Informe o Id: ");
				long id = sc.nextLong();
				System.out.print("Usuarios:\n");
				UsuarioDAO.pegar(id);
				System.out.print("---\n"
							+ "Administradores: ");
				AdministradorDAO.pegar(id);
			case 3:
				
				
			}
		}
		
	}

	
	static void telaVendedor() {
		long idProduto,idUsuario;
		double valor;
		System.out.print("Informe o id do produto: \n>");
		idProduto = sc.nextLong();
		System.out.print("Informe o valor do produto: \n>");
		valor = sc.nextDouble();
		System.out.print("Informe o seu id:\n> ");
		idUsuario = sc.nextLong();
		
		if(bancoProduto.vendaProduto(idProduto, valor, idUsuario)){
			System.out.print("Operação realizada com sucesso!");
		}
		else {
			System.out.print("Erro!");
		}
		
		
		
	}
	
	static void telaSupervisorProdutos() {
		int opcao, quantidade;
		long id;
		String nome, descricao;
		double valor;
		
		System.out.print("Oque deseja fazer?\n"
					+ "1- Editar Produtos\n"
					+ "2- Remover Produto\n"
					+ "3- Adicionar Produto\n");
		opcao = sc.nextInt();
		
		switch(opcao) {
		
		case 1:
			System.out.print("Informe o id do produto:\n>");
			id = sc.nextLong();
			sc.nextLine();
			System.out.print("Novas informações:\n");
			System.out.print("Nome:\n>");
			nome = sc.nextLine();
			System.out.print("Descrição:\n>");
			descricao = sc.nextLine();
			System.out.print("Quantidade:\n>");
			quantidade = sc.nextInt();
			System.out.print("Valor do produto:\n>");
			valor = sc.nextDouble();
			
			produto = new Produto(nome,descricao,quantidade,valor);
			
			if(bancoProduto.editarProdutos(id, produto, us)) {
				System.out.print("Informações alteradas com sucesso!!");
			}
			else {
				System.out.print("Não foi possivel editar!!");
			}
			
			break;
			
			
			
		}
	}
	
	public static void criarAdm() {
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
	
	static void criarUser() {
		String nome, cpf, senha;
		double salario;
		Turno turno;
		float taxaVenda;
		
		System.out.print("Informe o nome: ");
		nome = sc.nextLine();
		System.out.print("Informe o cpf: ");
		cpf = sc.nextLine();
		System.out.print("Informe a senha: ");
		senha = sc.nextLine();
		System.out.print("Informe o turno: "
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
				return;
			}
			
		System.out.print("Informe o salario: ");
		salario = sc.nextDouble();
		System.out.println("Informe a taxa de venda: ");
		taxaVenda = sc.nextFloat();
		
		user = new Usuario(nome, cpf, senha, turno, salario, taxaVenda);
		if(bancoUser.adicionar(user, padrao)) {
			System.out.print("Conta criada com sucesso!");
		} else {
			System.out.print("Erro ao criar a conta.");
		 }
	}
}
