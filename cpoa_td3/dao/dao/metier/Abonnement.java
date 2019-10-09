package dao.metier;

public class Abonnement {
	
		 private int id_client;
		 private int  id_revue;
		 private String date_debut;
		 private String date_fin;
		 
		 
		public Abonnement(int id_client, int id_revue, String date_debut, String date_fin) {
			super();
			this.id_client = id_client;
			this.id_revue = id_revue;
			this.date_debut = date_debut;
			this.date_fin = date_fin;
		}


		public Abonnement() {}


		public int getId_client() {
			return id_client;
		}


		public void setId_client(int id_client) {
			this.id_client = id_client;
		}


		public int getId_revue() {
			return id_revue;
		}


		public void setId_revue(int id_revue) {
			this.id_revue = id_revue;
		}


		public String getDate_debut() {
			return date_debut;
		}


		public void setDate_debut(String date_debut) {
			this.date_debut = date_debut;
		}


		public String getDate_fin() {
			return date_fin;
		}


		public void setDate_fin(String date_fin) {
			this.date_fin = date_fin;
		}


		@Override
		public String toString() {
			return "Abonnement [id_client=" + id_client + ", id_revue=" + id_revue + ", date_debut=" + date_debut
					+ ", date_fin=" + date_fin + "]";
		}
		
		
		@Override
		public boolean equals(Object abonnement) {
			if (this == abonnement)
				return true;
			
			if (abonnement == null)
				return false;
			
			if (this.getClass() != abonnement.getClass())
				return false;
			
			Abonnement other = (Abonnement) abonnement;
			
			if (date_debut == null) {
				if (other.date_debut != null)
					return false;		
			} else if (!date_debut.equals(other.date_debut))
				return false;
			
			if (date_fin == null) {
				if (other.date_fin != null)
					return false;
			} else if (!date_fin.equals(other.date_fin))
				return false;
			
			if (id_client != other.id_client) {
				return false;
			}
			
			if (id_revue != other.id_revue) {
				return false;
			}
			
			return true;
		}
}