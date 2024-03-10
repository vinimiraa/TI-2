package service;

// dependencias
import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;

public class UsuarioService 
{
	private UsuarioDAO usuarioDAO = new UsuarioDAO( );
	private String form;
	private final int FORM_INSERT          = 1;
	private final int FORM_DETAIL          = 2;
	private final int FORM_UPDATE          = 3;
	private final int FORM_ORDERBY_CODIGO  = 1;
	private final int FORM_ORDERBY_LOGIN   = 2;
	private final int FORM_ORDERBY_SEXO    = 3;
	
	public UsuarioService( ) 
	{
		makeForm();
	} // end PordutoService ( )

	public void makeForm( ) 
	{
		makeForm(FORM_INSERT, new Usuario( ), FORM_ORDERBY_LOGIN);
	} // end makeForm ( )

	public void makeForm( int orderBy ) 
	{
		makeForm(FORM_INSERT, new Usuario(), orderBy);
	} // end makeForm ( )

	public void makeForm( int tipo, Usuario usuario, int orderBy ) 
	{
		String nomeArquivo = "form.html";
		form = "";
		try
		{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext())
		    {
		    	form += (entrada.nextLine() + "\n");
		    } // end while
		    entrada.close();
		} catch (Exception e) { System.out.println(e.getMessage()); } // end try catch
		
		String umUsuario = "";
		
		if(tipo != FORM_INSERT) 
		{
			umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/usuario/list/1\">Novo Usuario</a></b></font></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t</table>";
			umUsuario += "\t<br>";			
		} // end if
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) 
		{
			String action = "/usuario/";
			String name, login, senha, buttonLabel;
			if (tipo == FORM_INSERT)
			{
				action += "insert";
				name = "Inserir Usuario";
				login = "login";
				senha = "senha";
				buttonLabel = "Inserir";
			} 
			else 
			{
				action += "update/" + usuario.getCodigo();
				name = "Atualizar Usuario (Codigo " + usuario.getCodigo() + ")";
				login = usuario.getLogin();
				senha = usuario.getSenha();
				buttonLabel = "Atualizar";
			} // end if
			umUsuario += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td>&nbsp;Login: <input class=\"input--register\" type=\"text\" name=\"login\" value=\""+ login +"\"></td>";
			umUsuario += "\t\t\t<td>Senha: <input class=\"input--register\" type=\"text\" name=\"senha\" value=\""+ senha +"\"></td>";
			umUsuario += "\t\t\t<td>Sexo: <input class=\"input--register\" type=\"text\" name=\"sexo\" value=\""+ usuario.getSexo() +"\"></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t</table>";
			umUsuario += "\t</form>";		
		} 
		else if ( tipo == FORM_DETAIL )
		{
			umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhes do Usuario ( Codigo " + usuario.getCodigo() + " )</b></font></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td>&nbsp;Codigo: "+ usuario.getCodigo() +"</td>";
			umUsuario += "\t\t\t<td>Login: "+ usuario.getLogin() +"</td>";
			umUsuario += "\t\t\t<td>Senha: "+ usuario.getSenha() +"</td>";
			umUsuario += "\t\t\t<td>Sexo: "+ usuario.getSexo() +"</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td>&nbsp;</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t</table>";		
		}
		else 
		{
			System.out.println("ERRO: Tipo não identificado " + tipo);
		} // end if
		
		form = form.replaceFirst("<UM-USUARIO>", umUsuario);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Usuarios</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_CODIGO + "\"><b>Codigo</b></a></td>\n" +
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_LOGIN + "\"><b>Login</b></a></td>\n" +
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_SEXO + "\"><b>Sexo</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Usuario> usuarios;
		if (orderBy == FORM_ORDERBY_CODIGO) {                 	
			usuarios = usuarioDAO.getOrderByCodigo();
		} else if (orderBy == FORM_ORDERBY_LOGIN) {		
			usuarios = usuarioDAO.getOrderByLogin();
		} else if (orderBy == FORM_ORDERBY_SEXO) {			
			usuarios = usuarioDAO.getOrderBySexo();
		} else {											
			usuarios = usuarioDAO.get();
		} // end if
		
		int i = 0;
		String bgcolor = "";
		for (Usuario p : usuarios) 
		{
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getCodigo() + "</td>\n" +
            		  "\t<td>" + p.getLogin() + "</td>\n" +
            		  "\t<td>" + p.getSexo() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/usuario/" + p.getCodigo() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/usuario/update/" + p.getCodigo() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteUsuario('" + p.getCodigo() + "', '" + p.getLogin() + "', '" + p.getSexo() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		} // end for
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-USUARIO>", list);				
	} // makeForm ( )
	
	public Object insert( Request request, Response response ) 
	{
		String login = request.queryParams("login");
		String senha = request.queryParams("senha");
		char   sexo  = request.queryParams("sexo").charAt(0);
		String resp = "";
		try
		{
			Usuario usuario = new Usuario( 1, login, senha, sexo );
			
			if( usuarioDAO.insert( usuario ) == true  ) 
			{
				resp = "Usuario ( " + login + " ) inserido!";
				response.status(201); // 201 Created
			} 
			else 
			{
				resp = "Usuario ( " + login + " ) não inserido!";
				response.status(404); // 404 Not found
			} // end if
		}
		catch( Exception e )
		{
	        resp = "Erro ao inserir usuário. Detalhes: " + e.getMessage();
	        response.status(500); // 500 Internal Server Error
		} // end try catch
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	} // end insert ( )

	public Object get( Request request, Response response ) 
	{
		int codigo = Integer.parseInt(request.params(":codigo"));		
		Usuario usuario = (Usuario) usuarioDAO.get(codigo);
		
		if (usuario != null) 
		{
			response.status(200); // success
			makeForm(FORM_DETAIL, usuario, FORM_ORDERBY_LOGIN);
        } 
		else 
        {
            response.status(404); // 404 Not found
            String resp = "Usuario " + codigo + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        } // end if

		return ( form );
	} // end get ( )

	public Object getToUpdate( Request request, Response response ) 
	{
		int codigo = Integer.parseInt(request.params(":codigo"));		
		Usuario usuario = (Usuario) usuarioDAO.get(codigo);
		
		if (usuario != null) 
		{
			response.status(200); // success
			makeForm(FORM_UPDATE, usuario, FORM_ORDERBY_LOGIN);
        } 
		else 
        {
            response.status(404); // 404 Not found
            String resp = "Usuario " + codigo + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        } // end if

		return ( form );
	} // end getToUpdate ( )
	
	public Object getAll( Request request, Response response ) 
	{
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return ( form );
	} // end getAll ( )
	
	public Object update( Request request, Response response ) 
	{
		String resp = "";
		
		try
		{
			int codigo = Integer.parseInt(request.params(":codigo"));
			Usuario usuario = usuarioDAO.get(codigo);
			
			if (usuario != null) 
			{
				usuario.setLogin(request.queryParams("login"));
				usuario.setSenha(request.queryParams("senha"));
				usuario.setSexo(request.queryParams("sexo").charAt(0));
				usuarioDAO.update(usuario);
				response.status(200); // success
				resp = "Usuario ( Codigo " + usuario.getCodigo() + " ) atualizado!";
			} 
			else 
			{
				response.status(404); // 404 Not found
				resp = "Usuario ( Codigo \" + usuario.getCodigo() + \" ) não encontrado!";
			} // end if
		}
		catch( Exception e )
		{
			resp = "Erro ao atualizar usuário. Detalhes: " + e.getMessage();
	        response.status(500); // 500 Internal Server Error
		} // end try catch
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	} // end update ( )

	public Object delete( Request request, Response response ) 
	{
        int codigo = Integer.parseInt(request.params(":codigo"));
        Usuario usuario = usuarioDAO.get(codigo);
        String resp = "";       

        if (usuario != null) 
        {
            usuarioDAO.delete(codigo);
            response.status(200); // success
            resp = "Usuario ( " + codigo + " ) excluído!";
        } else 
        {
            response.status(404); // 404 Not found
            resp = "Usuario ( " + codigo + " ) não encontrado!";
        } // end delete ( )
		makeForm( );
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	} // end delete ( )
	
} // end class 