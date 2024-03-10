package model;

public class Usuario 
{
	// atributos
	private int    codigo;
	private String login ;
	private String senha ;
	private char   sexo  ;
	
	// Construtor
	public Usuario( ) 
	{
		this.codigo = -1 ;
		this.login  = "" ;
		this.senha  = "" ;
		this.sexo   = '*';
	} // end Usuario ( )
	
	// Construtor Alternativo
	public Usuario( int codigo, String login, String senha, char sexo ) 
	{
		setCodigo( codigo );
		setLogin ( login  );
		setSenha ( senha  );
		setSexo  ( sexo   );
	} // end Usuario ( )

	// Retorna o codigo do usuario 
	public int getCodigo( ) 
	{
		return codigo;
	} // end getCodigo ( )

	// Atribui um codigo ao usuario
	public void setCodigo( int codigo ) 
	{
		this.codigo = codigo;
	} // end setCodigo ( )

	// Retorna o login do usuario 
	public String getLogin( ) 
	{
		return login;
	} // end getLogin ( )

	// Atribui um login ao usuario
	public void setLogin( String login ) 
	{
		this.login = login;
	} // end setLogin ( )

	// Retorna a senha do usuario 
	public String getSenha( ) 
	{
		return senha;
	} // end getSenha ( )

	// Atribui uma senha ao usuario
	public void setSenha( String senha ) 
	{
		this.senha = senha;
	} // end setSenha ( )

	// Retorna o sexo do usuario 
	public char getSexo()
	{
		return sexo;
	} // end getSexo ( )

	// Atribui um sexo ao usuario
	public void setSexo( char sexo ) 
	{
		this.sexo = sexo;
	} // end setSexo ( )

	// 
	@Override
	public String toString( ) 
	{
		return "Usuario [codigo=" + codigo + ", login=" + login + ", senha=" + senha + ", sexo=" + sexo + "]";
	} // end toString ( )
	
	@Override
	public boolean equals( Object obj ) 
	{
		return (this.getCodigo() == ((Usuario) obj).getCodigo());
	} // end equals ( )	
} // end class
