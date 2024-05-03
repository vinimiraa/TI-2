public class App
{		
	public static boolean temMaiuscula(String senha)
	{		
		boolean resp = false;
		for(int i = 0;i < senha.length() && !resp;i++){
		
			if(senha.charAt(i)>='A' && senha.charAt(i)<='Z')
				resp = true;
		}
		return resp;
	} // end temMaiuscula ( )

	public static boolean temNumero(String senha)
	{
		boolean resp =false;
		for(int i = 0;i < senha.length() && !resp;i++){
		
			if(senha.charAt(i)>='0' && senha.charAt(i)<='9')
				resp = true;
		}	
		return resp;
	} // end temNumero ( )
	
	public static boolean temEspecial(String senha)
	{
		boolean resp =false;
		for(int i = 0;i < senha.length() && !resp;i++){	
			if((senha.charAt(i)>='!' && senha.charAt(i)<='/')||(senha.charAt(i)>=':' && senha.charAt(i)<='@')  )
				resp = true;
		}
		return resp;
	} // end temEspecial ( )

	public static void main(String[] Args)
	{
		String senha = MyIO.readString();
		for(int i = 0 ;i < 6680 ; i++)
		{
			boolean tamanho = false;		
			if(senha.length() >= 8)
				tamanho = true;

			int a,b,c,d;
			a = (temEspecial(senha)) ? 1 : 0;
			b = (temMaiuscula(senha)) ? 1 : 0;
			c = (tamanho) ? 1 : 0;
			d = (temNumero(senha)) ? 1 : 0;
			
			System.out.println( senha +","+a + "," + b + "," + c + "," +d);
			senha = MyIO.readString();
		}
	} // end main ( )
} // end class App