package bd;

import bd.core.*;
import bd.daos.*;

public class BDMySQL
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
    	MeuPreparedStatement comando = null;

    	try
        {
            comando =
            new MeuPreparedStatement (
            "org.gjt.mm.mysql.Driver",
            "jdbc:mysql://localhost:3306/biblioteca?useTimezone=true&serverTimezone=UTC&useSSL=false",
            "root","password");
        }
        catch (Exception erro)
        {
            System.err.println (erro.getMessage());
            System.exit(0); // aborta o programa
        }
        
        COMANDO = comando;
    }
}