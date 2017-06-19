package teamprojekt.relay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		mapper.setDateFormat(df);

		EgkPatient egkData = null;

		try {
			egkData = mapper.readValue(stringBuilder.toString(), EgkPatient.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			System.out.println("eGK data received: " + mapper.writeValueAsString(egkData));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		EgkDataPersistence persistence = InMemoryEgkDataPersistence.getInstance();

		String ressourceId = persistence.createEgkData(egkData);

		// return HTTP response 200 in case of success
		return Response.status(200).entity(ressourceId).build();
	}

	@GET
	@Path("/client")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEgkData(@QueryParam("id") String ressourceId, InputStream incomingData) {

		System.out.println("client request: id = " + ressourceId);

		EgkDataPersistence persistence = InMemoryEgkDataPersistence.getInstance();

		EgkPatient egkData = persistence.readEgkData(ressourceId);

		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		mapper.setDateFormat(df);

		String response = "";
		try {
			response = mapper.writeValueAsString(egkData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// return HTTP response 200 in case of success
		return Response.status(200).entity(response).build();
	}

}