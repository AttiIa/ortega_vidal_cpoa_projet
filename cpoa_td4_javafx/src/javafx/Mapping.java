package javafx;

import dao.metier.Periodicite;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Mapping {
	
	@FXML
	private TextField titre;
	@FXML
	private TextField tarif;
	@FXML
	private ComboBox<Periodicite> libelle;
	@FXML
	private TextArea description;
	@FXML
	private TextField visuel;
	@FXML
	private Button creer;
	@FXML
	private Label affichage;
	@FXML
	private RadioButton choix;
	
	@Override
	public String toString() {
		return "Mapping [titre=" + titre + ", tarif=" + tarif + ", libelle=" + libelle + ", description=" + description
				+ ", visuel=" + visuel + ", choix=" + choix + "]";
	}
	
	public void Init() {
		titre.setText("");
		tarif.setText("");
		description.setText("");
		visuel.setText("");
	}
	
	@FXML
	public void create() {
		this.affichage.setText("appuie sur bouton");
	}
	
	
}
