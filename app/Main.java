package app;

import dao.AdministradorDAO;
import model.Administrador;

public class Main {

	public static void main(String[] args) {
		Administrador adm = new Administrador("Pedro Augusto");
		adm.setCpf("12345678900");
		adm.setSenha("12344321");
		
		AdministradorDAO.salvar(adm);
	}

}
