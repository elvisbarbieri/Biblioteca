package bd.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd.BDMySQL;
import bd.core.MeuResultSet;
import bd.dbos.Cliente;

public class Clientes {

	public static void inserir(Cliente cliente) throws Exception {
		if (cliente.equals(null))
			throw new Exception("Cliente nao fornecido");

		try {
			String sql;
			sql = String.format(
					"INSERT INTO cliente (nome,cidade,estado,endereco,telefone,email,cpf) VALUES ('%s','%s','%s','%s','%s','%s','%s')",
					cliente.getNome(), cliente.getCidade(), cliente.getEstado(), cliente.getEndereco(),
					cliente.getTelefone(), cliente.getEmail(), cliente.getCpf());
			BDMySQL.COMANDO.prepareStatement(sql);
			BDMySQL.COMANDO.executeUpdate();
			BDMySQL.COMANDO.commit();
		} catch (SQLException erro) {
			throw new Exception(erro.getMessage());
		}
	}

	public static void excluir(Integer id) throws Exception {
		if (!cadastrado(id))
			throw new Exception("Cliente não cadastrado");

		try {
			String sql;
			sql = String.format("DELETE FROM cliente WHERE ID= %d", id);
			BDMySQL.COMANDO.prepareStatement(sql);
			BDMySQL.COMANDO.executeUpdate();
			BDMySQL.COMANDO.commit();
		} catch (SQLException erro) {
			throw new Exception(erro.getMessage());
		}
	}

	public static void alterar(Cliente cliente) throws Exception {

		if (!cadastrado(cliente.getId()))
			throw new Exception("Cliente não cadastrado");

		try {
			String sql;
			sql = String.format(
					"UPDATE cliente set nome= '%s',cidade='%s',estado ='%s',endereco='%s',telefone='%s',email='%s',cpf='%s' WHERE id =%d",
					cliente.getNome(), cliente.getCidade(), cliente.getEstado(), cliente.getEndereco(),
					cliente.getTelefone(), cliente.getEmail(), cliente.getCpf(), cliente.getId());
			BDMySQL.COMANDO.prepareStatement(sql);
			BDMySQL.COMANDO.executeUpdate();
			BDMySQL.COMANDO.commit();
		} catch (SQLException erro) {
			throw new Exception("Erro ao atualizar dados de Aluno");
		}
	}

	public static Cliente buscar(Integer id) throws Exception {
		Cliente cliente = null;

		try {
			if (!cadastrado(id))
				throw new Exception("Cliente não cadastrado");
			
			String sql;
			sql = String.format("SELECT * FROM cliente WHERE id = %d", id);
			BDMySQL.COMANDO.prepareStatement(sql);
			MeuResultSet resultado = (MeuResultSet) BDMySQL.COMANDO.executeQuery();

			cliente = ConvertToDBO(resultado);
			resultado.close();

		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar Aluno");
		}

		return cliente;
	}

	public static List<Cliente> buscarTodos() throws Exception {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Cliente cliente = null;
		try {
			String sql;
			sql = String.format("SELECT * FROM cliente");
			BDMySQL.COMANDO.prepareStatement(sql);
			MeuResultSet resultado = (MeuResultSet) BDMySQL.COMANDO.executeQuery();

			if (!resultado.first())
				throw new Exception("Nao cadastrado");

			do {
				cliente = ConvertToDBO(resultado);
				if (!cliente.equals(null))
					clientes.add(cliente);
			} while (resultado.next());

			resultado.close();

		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar Aluno");
		}

		return clientes;
	}

	private static Cliente ConvertToDBO(MeuResultSet resultado) {

		Cliente cliente = new Cliente();
		try {
			cliente.setId(resultado.getInt("id"));
			cliente.setNome(resultado.getString("nome"));
			cliente.setEmail(resultado.getString("email"));
			cliente.setCpf(resultado.getString("cpf"));
			cliente.setCidade(resultado.getString("estado"));
			cliente.setEndereco(resultado.getString("endereco"));
			cliente.setTelefone(resultado.getString("telefone"));
			cliente.setCidade(resultado.getString("cidade"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cliente;
	}

	public static Boolean cadastrado(Integer id) {

		String sql;
		sql = String.format("SELECT * FROM cliente WHERE id = %d", id);
		try {

			BDMySQL.COMANDO.prepareStatement(sql);
			MeuResultSet resultado = (MeuResultSet) BDMySQL.COMANDO.executeQuery();

			if (!resultado.first())
				return false;

		} catch (SQLException erro) {
			System.out.println(erro.getMessage());
		}

		return true;
	}

}
