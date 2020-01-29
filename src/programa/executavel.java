package programa;

import bd.daos.Clientes;
import bd.dbos.Cliente;

public class executavel {

	public static void main(String[] args) {

		Cliente cliente = new Cliente();
//		cliente.setId(2);
//		cliente.setNome("Ricardo de Oliveira Barbieri");
//		cliente.setEmail("elvis_willian95@hotmail.com");
//		cliente.setCidade("itu");
//		cliente.setCpf("419.599.648-10");
//		cliente.setEndereco("rOnaldo, 100, Monte Paschoal");
//		cliente.setTelefone("(11) 4055-5844");
//		cliente.setEstado("SP");
		Clientes clientes = new Clientes();

		try {

			System.out.println(clientes.cadastrado(2));
//

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
