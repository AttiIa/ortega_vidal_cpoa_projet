package dao.metier;

public class Revue {
	 int id_revue;
	 String titre;
	 String description;
	 double tarif_numero;
	 String visuel;
	 int id_periodicite;
	 
	public Revue(int id_revue, String titre, String description, double tarif_numero, String visuel,
			int id_periodicite) {
		super();
		this.id_revue = id_revue;
		this.titre = titre;
		this.description = description;
		this.tarif_numero = tarif_numero;
		this.visuel = visuel;
		this.id_periodicite = id_periodicite;
	}
	
	public Revue(String titre, String description, double tarif_numero, String visuel,
			int id_periodicite) {
		super();
		this.id_revue = -1;
		this.titre = titre;
		this.description = description;
		this.tarif_numero = tarif_numero;
		this.visuel = visuel;
		this.id_periodicite = id_periodicite;
	}
	
	public Revue() {}

	public int getId_revue() {
		return id_revue;
	}

	public void setId_revue(int id_revue) {
		this.id_revue = id_revue;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTarif_numero() {
		return tarif_numero;
	}

	public void setTarif_numero(double tarif_numero) {
		this.tarif_numero = tarif_numero;
	}

	public String getVisuel() {
		return visuel;
	}

	public void setVisuel(String visuel) {
		this.visuel = visuel;
	}

	public int getId_periodicite() {
		return id_periodicite;
	}

	public void setId_periodicite(int id_periodicite) {
		this.id_periodicite = id_periodicite;
	}

	@Override
	public String toString() {
		return "Revue [id_revue=" + id_revue + ", titre=" + titre + ", description=" + description + ", tarif_numero="
				+ tarif_numero + ", visuel=" + visuel + ", id_periodicite=" + id_periodicite + "]";
	}
	
	@Override
	public boolean equals(Object revue) {
		if (this == revue)
			return true;
		
		if (revue == null)
			return false;
		
		if (this.getClass() != revue.getClass())
			return false;
		
		Revue other = (Revue) revue;
		
		if (titre == null) {
			if (other.titre != null)
				return false;		
		} else if (!titre.equals(other.titre))
			return false;
		
		if (description == null) {
			if (other.description != null)
				return false;		
		} else if (!description.equals(other.description))
			return false;
		
		if (Double.doubleToLongBits(tarif_numero) != Double.doubleToLongBits(other.tarif_numero))
			return false;
		
		if (visuel == null) {
			if (other.visuel != null)
				return false;		
		} else if (!visuel.equals(other.visuel))
			return false;
		
		if (id_periodicite != other.id_periodicite)
			return false;
		
		if (id_revue != other.id_revue) {
			return false;
		}
		
		return true;
	}	 
}
