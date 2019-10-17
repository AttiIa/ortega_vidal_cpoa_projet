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
	
	public void appuiBoutonOK() {
		
	}
}
