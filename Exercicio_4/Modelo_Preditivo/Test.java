import java.net.http.*;
import java.util.*;
import java.net.*;

public class Test
{
	public static void main(String[] Args)throws Exception
	{
		String body = "{\"Inputs\": {\"input1\": [{\"senha\": \"kzde5577\",\"special character\": 0,\"uppercase\": 0,\">=8\": 1,\"number\": 1,\"strength\": 1}]},\"GlobalParameters\": {}}";

		String url = "MINHA URL";
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
						.uri(URI.create(url)).header("Content-Type","application/json").header("Authorization","Bearer MINHA CHAVE==").header("Accept","application/json")
						.POST(HttpRequest.BodyPublishers.ofString(body))
						.build();
		HttpResponse<String> resp = client.send(req,HttpResponse.BodyHandlers.ofString());
		System.out.println(resp.body());
	} // end main ( )
} // end class Test
