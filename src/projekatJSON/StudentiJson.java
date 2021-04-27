package projekatJSON;

import com.google.gson.annotations.SerializedName;

public class StudentiJson  {

	public String Име;
	@SerializedName("Prezime")

	public String Prezime;
	@SerializedName("Indeks")

	public String Indeks;
	@SerializedName("ID")

	public String ID;
	@SerializedName("Bodovi")

	public String Bodovi;
	
	public StudentiJson() {
		super();
		
	}
	
	public StudentiJson(String Име, String Prezime, String Indeks, String ID, String Bodovi)
	{
		this.ID = ID;
		this.Indeks = Indeks;
		this.Име = Име;
		this.Prezime = Prezime;
		this.Bodovi = Bodovi;
	}
	

	public String getИме() {
		return Име;
	}

	public void setIme(String име) {
		Име = име;
	}

	public String getPrezime() {
		return Prezime;
	}

	public void setPrezime(String prezime) {
		Prezime = prezime;
	}

	public String getIndeks() {
		return Indeks;
	}

	public void setIndeks(String indeks) {
		Indeks = indeks;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getBodovi() {
		return Bodovi;
	}

	public void setBodovi(String bodovi) {
		Bodovi = bodovi;
	}
	
	public String toString() {
		return getID() + ". " + getIndeks() + " " + getИме() + " " + getPrezime() + " " + getBodovi();
	}

}
