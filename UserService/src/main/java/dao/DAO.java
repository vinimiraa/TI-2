package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;

public class DAO 
{
	protected Connection conexao;
	
	public DAO( ) 
	{
		conexao = null;
	} // end DAO ( )
	
	public boolean conectar( ) 
	{
		String  driverName = "org.postgresql.Driver";                    
		String  serverName = "localhost";
		String  mydatabase = "teste";
		int     porta      =  5432;
		String  url        = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String  username   = "ti2cc";
		String  password   = "ti@cc";
		boolean status     = false;

		try 
		{
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) 
		{ 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) 
		{
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		} // end try catch

		return ( status );
	} // end conectar ( )
	
	public boolean close( ) 
	{
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return ( status );
	} // end close ( )
	
	public static String toMD5( String senha ) throws Exception 
	{
		MessageDigest m=MessageDigest.getInstance("MD5");
		m.update(senha.getBytes(),0, senha.length());
		return new BigInteger(1,m.digest()).toString(16);
	} // end toMD5 ( )
} // end class