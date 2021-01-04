package application;

//171805053 Soner Cengiz
//181805027 Ceren Aktaþ

public class PersonObject {
	private int id;
	private String name;
	private String gender;
	private String street;
	private String city;
	private String zip;
	
	public PersonObject(int id, String name, String gender, String street, String city, String zip) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.street = street;
		this.city = city;
		this.zip = zip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Override
	public String toString()
	{
		return getId()+ " "+ getName()+ " "+ getStreet()+ " "+ getCity()+" "+ getGender()+ " "+ getZip();
		
	}
}

//171805053 Soner Cengiz
//181805027 Ceren Aktaþ
