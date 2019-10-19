package dao.metier;

public class Periodicite {
	 int id_periodicite;
	 String libelle;
	 
	public Periodicite(int id_periodicite, String libelle) {
		super();
		this.id_periodicite = id_periodicite;
		this.libelle = libelle;
	}

	public Periodicite() {}

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
		return libelle ;
	}
	
	@Override
	public boolean equals(Object periodicite) {
		if (this == periodicite)
			return true;
		
		if (periodicite == null)
			return false;
		
		if (this.getClass() != periodicite.getClass())
			return false;
		
		Periodicite other = (Periodicite) periodicite;
		
		if (libelle == null) {
			if (other.libelle != null)
				return false;		
		} else if (!libelle.equals(other.libelle))
			return false;
		
		if (id_periodicite != other.id_periodicite)
			return false;
		
		return true;
	}

}
