package teamprojekt.relay;

import java.io.BufferedReader;
import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.gecko.egkfeuer.model.EgkPatient;

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

		ObjectMapper mapper = new ObjectMapper();
		EgkPatient egkData = null;

		try {
			egkData = mapper.readValue(stringBuilder.toString(), EgkPatient.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("eGK data received: " + mapper.writeValueAsString(egkData));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return HTTP response 200 in case of success
		return Response.status(200).entity("1234567890").build();
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