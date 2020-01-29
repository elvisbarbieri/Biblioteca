package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Relatorios {

	public static MeuResultSet relatorioMateria(int codMateria) throws Exception {
		MeuResultSet resultado = null;

		try {
			String sql;

			sql = "SELECT A.Nome AS 'Nome Aluno', H.Nota AS 'Nota', M.Nome AS 'Nome Materia'"
					+ "FROM Alunos A, Historico H, Materias M"
					+ "WHERE M.Codigo = H.Codigo AND A.RA = H.RA AND H.Codigo = ?" + "order by M.Nome, H.Nota";

			BDMySQL.COMANDO.prepareStatement(sql);

			BDMySQL.COMANDO.setInt(1, codMateria);

			resultado = (MeuResultSet) BDMySQL.COMANDO.executeQuery();

		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar Relat�rio");
		}

		return resultado;
	}

	public static MeuResultSet relatorioAluno(int ra) throws Exception {
		MeuResultSet resultado = null;

		try {
			String sql;

			sql = "SELECT A.Nome AS 'Nome Aluno', H.Nota AS 'Nota', M.Nome AS 'Nome Materia'"
					+ "FROM Alunos A, Historico H, Materias M"
					+ "WHERE M.Codigo = H.Codigo AND A.RA = H.RA AND A.RA = ?" + "order by A.Nome, H.Nota";

			BDMySQL.COMANDO.prepareStatement(sql);

			BDMySQL.COMANDO.setInt(1,ra);

			resultado = (MeuResultSet) BDMySQL.COMANDO.executeQuery();

		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar Relat�rio");
		}

		return resultado;
	}

}
