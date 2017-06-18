package de.gecko.egkfeuer.model;

import java.util.Date;
import java.util.Objects;

public class EgkPatient {
	private String title;
	private String givenName;
	private String surname;
	private Date birthday;
	private Sex sex;
	private String zip;
	private String city;
	private String streetname;
	private String housenumber;
	private String healthInsuranceProviderNumber;
	private String healthInsuranceNumber;
	private String vorsatzwort;
	private String namenszusatz;
	private String healthInsuranceProviderName;

	public EgkPatient() {

	}

	/**
	 * @param title
	 * @param givenName
	 *            not <code>null</code>
	 * @param surname
	 *            not <code>null</code>
	 * @param vorsatzwort
	 * @param namenszusatz
	 * @param birthday
	 *            not <code>null</code>
	 * @param sex
	 *            not <code>null</code>
	 * @param zip
	 * @param city
	 * @param streetname
	 * @param healthInsuranceProviderNumber
	 * @param healthInsuranceProviderName
	 * @param healthInsuranceNumber
	 * 
	 */
	public EgkPatient(String title, String givenName, String surname, String vorsatzwort, String namenszusatz,
			Date birthday, Sex sex, String zip, String city, String streetname, String housenumber,
			String healthInsuranceProviderNumber, String healthInsuranceProviderName, String healthInsuranceNumber) {
		this.title = title;
		this.givenName = Objects.requireNonNull(givenName, "givenName");
		this.surname = Objects.requireNonNull(surname, "surname");
		this.vorsatzwort = vorsatzwort;
		this.namenszusatz = namenszusatz;
		this.birthday = Objects.requireNonNull(birthday, "birthday");
		this.sex = Objects.requireNonNull(sex, "sex");
		this.zip = zip;
		this.city = city;
		this.streetname = streetname;
		this.housenumber = housenumber;
		this.healthInsuranceProviderNumber = healthInsuranceProviderNumber;
		this.healthInsuranceProviderName = healthInsuranceProviderName;
		this.healthInsuranceNumber = healthInsuranceNumber;

	}

	public String getTitle() {
		return title;
	}

	public String getGivenName() {
		return givenName;
	}

	public String getSurname() {
		return surname;
	}

	public String getVorsatzwort() {
		return vorsatzwort;
	}

	public String getNamenszusatz() {
		return namenszusatz;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Sex getSex() {
		return sex;
	}

	public String getZip() {
		return zip;
	}

	public String getCity() {
		return city;
	}

	public String getStreetname() {
		return streetname;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public String getHealthInsuranceProviderNumber() {
		return healthInsuranceProviderNumber;
	}

	public String getHealthInsuranceProviderName() {
		return healthInsuranceProviderName;
	}

	public String getHealthInsuranceNumber() {
		return healthInsuranceNumber;
	}

}
