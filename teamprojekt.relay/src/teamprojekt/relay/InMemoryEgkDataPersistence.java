package teamprojekt.relay;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;

import de.gecko.egkfeuer.model.EgkPatient;

public class InMemoryEgkDataPersistence implements EgkDataPersistence {

	private static final InMemoryEgkDataPersistence PERSISTENCE = new InMemoryEgkDataPersistence();

	private HashMap<String, EgkPatient> egkDataMap;
	private SecureRandom random;

	private InMemoryEgkDataPersistence() {
		egkDataMap = new HashMap<>();
		random = new SecureRandom();
	}

	public static InMemoryEgkDataPersistence getInstance() {
		return PERSISTENCE;
	}

	@Override
	public String createEgkData(EgkPatient egkPatient) {
		String ressourceID = getRessourceId();
		egkDataMap.put(ressourceID, egkPatient);
		System.out.println("stored in memory persistence. object count = "+ egkDataMap.size());
		return ressourceID;
	}

	@Override
	public EgkPatient readEgkData(String ressourceID) {
		return egkDataMap.get(ressourceID);
	}

	private String getRessourceId() {
		String sessionID = new BigInteger(130, random).toString(32);
		System.out.println("generated ressource id: " + sessionID);
		return sessionID;
	}

}
