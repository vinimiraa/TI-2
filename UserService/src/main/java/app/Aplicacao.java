package app;

import static spark.Spark.*;
import service.UsuarioService;

public class Aplicacao 
{
	private static UsuarioService usuarioService = new UsuarioService( );
	
    public static void main(String[] args) 
    {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/usuario/insert", (request, response) -> usuarioService.insert(request, response));

        get("/usuario/:codigo", (request, response) -> usuarioService.get(request, response));
        
        get("/usuario/list/:orderby", (request, response) -> usuarioService.getAll(request, response));

        get("/usuario/update/:codigo", (request, response) -> usuarioService.getToUpdate(request, response));
        
        post("/usuario/update/:codigo", (request, response) -> usuarioService.update(request, response));
           
        get("/usuario/delete/:codigo", (request, response) -> usuarioService.delete(request, response));
            
    } // end main ( )
} // end class ( )