package app;

import java.util.List;
import java.util.Scanner;

// Dependencias
import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;

public class Aplicacao 
{
	public static Scanner sc = new Scanner(System.in);

	public static void main( String[] args ) throws Exception 
	{
		// variaveis
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
 
		int opcao = 0;

		do
		{
			// mostrar o menu 
			menu( );
			
			// leitura de dados
			System.out.print( "Digite uma opcao: " );
			opcao = sc.nextInt( );
			
			switch( opcao )
			{
			case 1:
				listarUsuarios( usuarioDAO );
				break;
			case 2:
				inserirUsuario( usuarioDAO, usuario );
				break;
			case 3:
				excluirUsuario( usuarioDAO, usuario );
				break;
			case 4:
				atualizarUsuario(usuarioDAO, usuario  );
				break;
			case 5:
				System.out.printf( "\n%s\n" , "Programa encerrado." );
				break;
				
			default:
				System.out.printf( "\n%s\n" , "ERRO: Opcao Invalida" );
				break;
			} // end switch case
		}while ( opcao != 5 ); // end do while
		
		sc.close();
	} // end main ( )
	
	// menu
	public static void menu( ) 
	{
		System.out.println("\n*****MENU***** " );
		System.out.println(" 1 - Listar    " );
		System.out.println(" 2 - Inserir   " );
		System.out.println(" 3 - Excluir   " );
		System.out.println(" 4 - Atualizar " );
		System.out.println(" 5 - Sair      " );
	} // end menu ( )
	
	// funcao para listar todos os usuarios
	public static void listarUsuarios( UsuarioDAO usuarioDAO ) 
	{
		int opcao = 0;
		//List<Usuario> usuarios = usuarioDAO.get();
		List<Usuario> usuarios;
		
		System.out.println( "\n*****LISTA*****" );
		
		System.out.println( "1 - Listar." );
		System.out.println( "2 - Listar usuario ordenado por codigo." );
		System.out.println( "3 - Listar usuario ordenado por login." );
		opcao = sc.nextInt( );
		sc.nextLine( );
		
		if( opcao == 1 )
		{
			usuarios = usuarioDAO.get();
			for (Usuario u : usuarios) 
			{
				System.out.println(u.toString());
			} // end for
		}
		else if( opcao == 2 )
		{
			
			usuarios = usuarioDAO.getOrderByCodigo();
			for (Usuario u: usuarios) 
			{
				System.out.println(u.toString());
			} // end for
		}
		else if( opcao == 3 )
		{
			usuarios = usuarioDAO.getOrderByLogin();
			for (Usuario u: usuarios) 
			{
				System.out.println(u.toString());
			} // end for
		}
		else
		{
			System.out.println( "ERRO: Opcao Invalida" );
		} // end if
    } // end listarUsuarios ( )
    
	// funcao para criar um novo usuario
    private static void inserirUsuario( UsuarioDAO usuarioDAO, Usuario usuario ) 
    {
    	int codigo = 0;
    	char sexo = '0';
    	Usuario usuarioExist;
    	boolean codigoExist = false;
    	
        System.out.println("\n*****Inserir usuário*****");
        
        do 
        {
        	System.out.print("Digite o código: ");
        	codigo = sc.nextInt();
        	sc.nextLine();
        	
        	usuarioExist = usuarioDAO.get( codigo );
        	if( usuarioExist != null )
        	{
        		System.out.println( "ERRO: Codigo ja existe. Digite outro codigo" );
        	}
        	else
        	{
        		codigoExist = true;
        	} // end if
        } while( !codigoExist ); // end while
        
        System.out.print("Digite  o login: ");
        String login = sc.nextLine();
        
        System.out.print("Digite a senha: ");
        String senha = sc.nextLine();
        
        System.out.print("Digite o sexo (M/F): ");
        sexo = sc.next().charAt(0);
        
        usuario = new Usuario( codigo, login, senha, sexo );
        
		if ( usuarioDAO.insert(usuario) ) 
        {
            System.out.println( "Inserção com sucesso -> " + usuario.toString() );
        } else {
            System.out.println( "Falha ao inserir usuário." );
        } // end if
    } // end inserirUsuario ( )  
    
    // funcao para atualizar um usuario
    private static void atualizarUsuario( UsuarioDAO usuarioDAO, Usuario usuario ) 
    {
    	int codigo = 0;
    	
        System.out.println("\n*****Atualizar usuário*****");
        
        System.out.print("Informe o código do usuário a ser atualizado: ");
        codigo = sc.nextInt();
        sc.nextLine();
        
        usuario = usuarioDAO.get(codigo);
        if (usuario != null) 
        {
            System.out.print("Informe o novo login: ");
            usuario.setLogin(sc.nextLine());
            
            System.out.print("Informe a nova senha: ");
            usuario.setSenha(sc.nextLine());
            
            System.out.print("Informe o novo sexo (M/F): ");
            usuario.setSexo(sc.next().charAt(0));
            
            if (usuarioDAO.update(usuario)) 
            {
                System.out.println("Usuário atualizado com sucesso.");
            } else {
                System.out.println("Falha ao atualizar usuário.");
            } // end if
        } else 
        {
            System.out.println("Usuário não encontrado.");
        } // end if
    } // end atualizarUsuario ( )
    
    // funcao para atualizar um usuario
    private static void excluirUsuario( UsuarioDAO usuarioDAO, Usuario usuario ) 
    {
        int codigo = 0;
    	char confirmar = '0';
    	
    	System.out.println("\n*****Excluir usuário*****");
        
        System.out.print("Informe o código do usuário a ser excluído: ");
        codigo = sc.nextInt();
        
        System.out.println( "Tem certeza que deseja excluir o usuário? (S/N): " );
        confirmar = sc.next().charAt( 0 );
        
        if( confirmar == 'S' || confirmar == 's' )
        {
        	if ( usuarioDAO.delete(codigo) ) 
        	{
        		System.out.println("Usuário excluído com sucesso.");
        	} else 
        	{
        		System.out.println("Falha ao excluir usuário.");
        	} // end if
        }
        else if( confirmar == 'N' || confirmar == 'n' )
        {
        	System.out.println( "\nExclusao cancelada." );
        }
        else
        {
        	System.out.println( "\nERRO: Opcao invalida" );
        } // end if
        
    } // end excluirUsuario ( )
	
} // end class