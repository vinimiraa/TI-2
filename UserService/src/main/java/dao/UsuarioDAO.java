package dao;

// dependencias
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAO extends DAO 
{	
	public UsuarioDAO( ) 
	{
		super( );
		conectar( );
	} // end usuarioDAO ( )

	public void finalize( ) 
	{
		close( );
	} // end finalize
	
	public boolean insert( Usuario usuario ) 
	{
		boolean status = false;
		try 
		{  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO usuario (login, senha, sexo) "
				       + "VALUES ('" + usuario.getLogin() + "', '" + usuario.getSenha() + "', '" + usuario.getSexo() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch ( SQLException u ) 
		{  
			throw new RuntimeException(u);
		} // end try catch
		return ( status );
	} // end insert ( )

	public Usuario get( int codigo ) 
	{
		Usuario usuario = null;
		
		try 
		{
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE codigo=" + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 usuario = new Usuario(rs.getInt("codigo"), rs.getString("login"), rs.getString("senha"), rs.getString("sexo").charAt(0));
	        }
	        st.close();
		} catch (Exception e) 
		{
			System.err.println(e.getMessage());
		} // end try catch 
		return ( usuario );
	} // end get ( )
	
	public List<Usuario> get( ) 
	{
		return get("");
	} // end get ( )

	public List<Usuario> getOrderByCodigo( ) 
	{
		return get("codigo");		
	} // end getOrderByCodigo ( )
	
	public List<Usuario> getOrderByLogin( ) 
	{
		return get("login");		
	} // end getOrderByLogin ( )
	
	public List<Usuario> getOrderBySexo( ) 
	{
		return get("sexo");		
	} // end getOrderBySexo ( )
	
	private List<Usuario> get( String orderBy ) 
	{	
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try 
		{
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while( rs.next( ) ) 
			{	            	
	        	Usuario u = new Usuario(rs.getInt("codigo"), rs.getString("login"), rs.getString("senha"), rs.getString("sexo").charAt(0));
	            usuarios.add(u);
	        } // end whiile
	        st.close();
		} catch ( Exception e ) 
		{
			System.err.println(e.getMessage());
		} // end try catch
		return ( usuarios );
	} // end get ( )

	public List<Usuario> getSexoMasculino( ) 
	{
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try 
		{
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE usuario.sexo LIKE 'M'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while( rs.next( ) ) 
			{	            	
	        	Usuario u = new Usuario( rs.getInt("codigo"), rs.getString("login"), 
							rs.getString("senha"), rs.getString("sexo").charAt(0) );
	            usuarios.add(u);
	        } // end while
	        st.close();
		} catch ( Exception e ) 
		{
			System.err.println(e.getMessage());
		} // end try catch
		return ( usuarios );
	} // end getSexoMasculino ( )
	
	public boolean update( Usuario usuario ) 
	{
		boolean status = false;
		try 
		{  
			Statement st = conexao.createStatement();
			String sql = "UPDATE usuario SET login = '" + usuario.getLogin() + "', senha = '"  
				       + usuario.getSenha() + "', sexo = '" + usuario.getSexo() + "'"
					   + " WHERE codigo = " + usuario.getCodigo();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch ( SQLException u ) 
		{  
			throw new RuntimeException(u);
		} // end try catch
		return status;
	} // end update ( )
	
	public boolean delete( int codigo ) 
	{
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM usuario WHERE codigo = " + codigo;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) 
		{  
			throw new RuntimeException(u);
		} // end try catch 
		return ( status );
	} // end delete ( )
	
	public boolean autenticar( String login, String senha ) 
	{
		boolean resp = false;
		
		try 
		{
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE login LIKE '" + login + "' AND senha LIKE '" + senha  + "'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			resp = rs.next();
	        st.close();
		} catch ( Exception e ) 
		{
			System.err.println(e.getMessage());
		} // end try catch 
		return resp;
	} // end autenticar ( )

} // end class