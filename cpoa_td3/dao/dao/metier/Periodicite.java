package dao.metier;

public class Periodicite {
	 int id_periodicite;
	 String libelle;
	 
	public Periodicite(int id_periodicite, String libelle) {
		super();
		this.id_periodicite = id_periodicite;
		this.libelle = libelle;
	}

	public int getId_periodicite() {
		return id_periodicite;
	}

	public void setId_periodicite(int id_periodicite) {
		this.id_periodicite = id_periodicite;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Periodicite [id_periodicite=" + id_periodicite + ", libelle=" + libelle + "]";
	}

}
