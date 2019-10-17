package main;
import dao.metier.Revue;
import dao.metier.Periodicite;
import java.net.URL;
import java.util.ResourceBundle;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.control.*;

public class Mapping implements Initializable {
	
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
	private RadioButton mysql;
	@FXML
	private RadioButton list;
	@FXML
	private TextField id_revue;

	
	@Override
	public String toString() {
		return "Mapping [titre=" + titre + ", tarif=" + tarif + ", libelle=" + libelle + ", description=" + description
				+ ", visuel=" + visuel + ", mysql=" + mysql +", list=" + list + "]";
	}
	
	 public void initialize(URL location, ResourceBundle resources) {

	        DAOFactory dao = DAOFactory.getDAOFactory(Persistance.ListeMemoire);

	        try {
				this.libelle.setItems(FXCollections.observableArrayList(dao.getPeriodiciteDAO().findAll()));
			} catch (Exception e) {
				System.out.println("Problème à l'initialisation ");
				e.printStackTrace();
			}
	    }   
	
	public void Init() {
		titre.setText("");
		tarif.setText("");
		description.setText("");
		visuel.setText("");
		affichage.setText("");
	}
	
	@FXML
	public void create() {
		Periodicite period = libelle.getValue();
		
		if ((id_revue.getText().isEmpty()) || (titre.getText().isEmpty()) || (description.getText().isEmpty()) || (tarif.getText().isEmpty()) || (visuel.getText().isEmpty()) /*|| (period == null)*/ || (id_revue.getText().isEmpty())){
			affichage.setTextFill(Color.web("red"));
			this.affichage.setText("Les champs ne sont pas tous valides");
		}
		
		else {	
			DAOFactory daos = null;
			if(mysql != null) {
				daos = DAOFactory.getDAOFactory(Persistance.MySQL);
			}
			else if(list != null){
				daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
			}
			try {
				
				double txt_tarif = Double.parseDouble(tarif.getText());
				affichage.setTextFill(Color.web("#000000"));
				affichage.setText(toString());
				int txt_revue = Integer.parseInt(id_revue.getText());
				String txt_titre = titre.getText();
				String txt_description = description.getText();
				String txt_visuel = visuel.getText();
				Revue rev = new Revue(txt_revue, txt_titre, txt_description, txt_tarif, txt_visuel, period.getId_periodicite());
		        
		        daos.getRevueDAO().create(rev);
			} catch (Exception e) {
				affichage.setTextFill(Color.web("red"));
				affichage.setText(toString());
			}
		}	
	}
	
	@FXML
	public void sql() {
		
	}
	
	@FXML
	public void listm() {
		
	}
}
