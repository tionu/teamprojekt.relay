package teamprojekt.relay;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/")
public class EgkRelayService {
	@POST
	@Path("/terminal")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response receiveEgkData(InputStream incomingData) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		
		JSONObject jsonObject = new JSONObject(stringBuilder);
		
		System.out.println("eGK data received: " + jsonObject.toString());

		// return HTTP response 200 in case of success
		return Response.status(200).entity(stringBuilder.toString()).build();
	}

	@GET
	@Path("/client")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEgkData(InputStream incomingData) {
		String result = "{\"string\": \"client access\"}";

		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}

}