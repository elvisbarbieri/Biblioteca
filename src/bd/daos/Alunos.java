package bd.daos;

import java.sql.SQLException;
import java.util.List;

import bd.BDMySQL;
import bd.core.MeuResultSet;
import bd.dbos.Aluno;

public class Alunos {
	public static boolean cadastrado(int RA) throws Exception {
		boolean retorno = false;

		try {
			String sql;

			sql = "SELECT * " + "FROM Alunos " + "WHERE RA = ?";

			BDMySQL.COMANDO.prepareStatement(sql);

			BDMySQL.COMANDO.setInt(1, RA);

			MeuResultSet resultado = (MeuResultSet) BDMySQL.COMANDO.executeQuery();

			retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou
											// resultado.absotule(numeroDaLinha)

			/*
			 * // ou, se preferirmos,
			 * 
			 * String sql;
			 * 
			 * sql = "SELECT COUNT(*) AS QUANTOS " + "FROM Alunos " + "WHERE RA = ?";
			 * 
			 * BDSQLServer.COMANDO.prepareStatement (sql);
			 * 
			 * BDSQLServer.COMANDO.setInt (1, RA);
			 * 
			 * MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
			 * 
			 * resultado.first();
			 * 
			 * retorno = resultado.getInt("QUANTOS") != 0;
			 * 
			 */
		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar Aluno");
		}

		return retorno;
	}

	public static void inserir(Aluno Aluno) throws Exception {
		if (Aluno == null)
			throw new Exception("Aluno nao fornecido");

		try {
			String sql;

			sql = "INSERT INTO ALUNO " + "(RA,Nome,email) " + "VALUES " + "(?,?,?)";

			BDMySQL.COMANDO.prepareStatement(sql);

			BDMySQL.COMANDO.setString(1, Aluno.getRA());
			BDMySQL.COMANDO.setString(2, Aluno.getNome());
			BDMySQL.COMANDO.setString(3, Aluno.getEmail());

			BDMySQL.COMANDO.executeUpdate();
			BDMySQL.COMANDO.commit();
		} catch (SQLException erro) {
			throw new Exception(erro.getMessage());
		}
	}

	public static void excluir(int RA) throws Exception {
		if (!cadastrado(RA))
			throw new Exception("Nao cadastrado");

		try {
			String sql;

			sql = "DELETE FROM Alunos " + "WHERE RA=?";

			BDMySQL.COMANDO.prepareStatement(sql);

			BDMySQL.COMANDO.setInt(1, RA);

			BDMySQL.COMANDO.executeUpdate();
			BDMySQL.COMANDO.commit();
		} catch (SQLException erro) {
			throw new Exception("Erro ao excluir Aluno");
		}
	}

	
	public static int getQtd(String nome) throws Exception {
		if (nome == null || nome.equals(""))
			throw new Exception("Nome nao fornecido");

		int ret = 0;

		// jeito bom
		try {
			String sql;

			sql = "SELECT COUNT(*) " + "FROM Alunos " + "WHERE NOME = ?";

			BDMySQL.COMANDO.prepareStatement(sql);

			BDMySQL.COMANDO.setString(1, nome);

			MeuResultSet resultado = (MeuResultSet) BDMySQL.COMANDO.executeQuery();

			resultado.first(); // � a unica linha
			ret = resultado.getInt(1); // � a unica coluna

			resultado.close();
		} catch (SQLException erro) {
		}

		/*
		 * // jeito tambem bom try { String sql;
		 * 
		 * sql = "SELECT COUNT(*) AS CONT" + "FROM Alunos " + "WHERE NOME = ?";
		 * 
		 * BDSQLServer.COMANDO.prepareStatement (sql);
		 * 
		 * BDSQLServer.COMANDO.setString (1, nome);
		 * 
		 * MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
		 * 
		 * resultado.first(); // � a unica linha ret = resultado.getInt (CONT); // � a
		 * unica coluna
		 * 
		 * resultado.close(); } catch (SQLException erro) {}
		 */

		/*
		 * // jeito m�dio try { String sql;
		 * 
		 * sql = "SELECT * " + "FROM Alunos " + "WHERE NOME = ?";
		 * 
		 * BDSQLServer.COMANDO.prepareStatement (sql);
		 * 
		 * BDSQLServer.COMANDO.setString (1, nome);
		 * 
		 * MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
		 * 
		 * while (resultado.next()) ret++;
		 * 
		 * resultado.close(); } catch (SQLException erro) {}
		 */

		/*
		 * // jeito ruim try { String sql;
		 * 
		 * sql = "SELECT * " + "FROM Alunos ";
		 * 
		 * BDSQLServer.COMANDO.prepareStatement (sql);
		 * 
		 * BDSQLServer.COMANDO.setString (1, nome);
		 * 
		 * MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
		 * 
		 * while (resultado.next()) if (nome.equals.resultado.getString ("Nome")))
		 * ret++;
		 * 
		 * resultado.close(); } catch (SQLException erro) {}
		 */

		return ret;
	}

	public static Aluno getAluno(int RA) throws Exception {
		Aluno Aluno = null;

		try {
			String sql;

			sql = "SELECT * " + "FROM Alunos " + "WHERE RA = ?";

			BDMySQL.COMANDO.prepareStatement(sql);

			BDMySQL.COMANDO.setInt(1, RA);

			MeuResultSet resultado = (MeuResultSet) BDMySQL.COMANDO.executeQuery();

			if (!resultado.first())
				throw new Exception("Nao cadastrado");

			// Aluno = new Aluno(resultado.getInt("RA"), resultado.getString("NOME"),
			// resultado.getString("EMAIL"));

			resultado.close();
		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar Aluno");
		}

		return Aluno;
	}

	public static List<Alunos> getAlunos() throws Exception {
		MeuResultSet resultado = null;

		try {
			String sql;

			sql = "SELECT * " + "FROM ALUNO";

			BDMySQL.COMANDO.prepareStatement(sql);

			resultado = (MeuResultSet) BDMySQL.COMANDO.executeQuery();

			while (resultado.next()) {

				System.out.println(resultado.getString("Nome"));
			}

		} catch (SQLException erro) {
			throw new Exception(erro.getMessage());
		}
	
		return null;
	}
}