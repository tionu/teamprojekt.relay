package teamprojekt.relay;

import de.gecko.egkfeuer.model.EgkPatient;

public interface EgkDataPersistence {
	
	public String createEgkData(EgkPatient egkPatient);
	
	public EgkPatient readEgkData(String ressourceId);
		
}
