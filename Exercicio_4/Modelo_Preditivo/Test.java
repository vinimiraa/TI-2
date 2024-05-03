import java.net.http.*;
import java.util.*;
import java.net.*;

public class Test
{
	public static void main(String[] Args)throws Exception
	{
		String body = "{\"Inputs\": {\"input1\": [{\"senha\": \"kzde5577\",\"special character\": 0,\"uppercase\": 0,\">=8\": 1,\"number\": 1,\"strength\": 1}]},\"GlobalParameters\": {}}";

		String url = "https://ussouthcentral.services.azureml.net/workspaces/7f2ec8556b8f453681ccc1fca01369a8/services/756e95b5ed3d46478656395e2092dd85/execute?api-version=2.0&format=swagger";
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
						.uri(URI.create(url)).header("Content-Type","application/json").header("Authorization","Bearer eU42yooWtos3e8fVHnFoSIEGb762tSzj9D9c7d9rp2tQyFGfiTJ35QfFaWFNE9ld8ikAk7FPOb2d+AMC7Z7MJg==").header("Accept","application/json")
						.POST(HttpRequest.BodyPublishers.ofString(body))
						.build();
		HttpResponse<String> resp = client.send(req,HttpResponse.BodyHandlers.ofString());
		System.out.println(resp.body());
	} // end main ( )
} // end class Test