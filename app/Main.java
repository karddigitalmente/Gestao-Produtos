package app;

import dao.AdministradorDAO;
import model.Administrador;

public class Main {

	public static void main(String[] args) {
		// Cria o objeto Admininastro
		Administrador adm = new Administrador("Pedro Augusto");
		
		//Adiciono os atributos pelo método setter pois
		// os métodos setters possuem alguns tratamentos
		adm.setCpf("12345678900");
		adm.setSenha("12344321");
		
		// Uso a classe Data Access Object para salvar o ademe
		AdministradorDAO.salvar(adm);
	}

}
