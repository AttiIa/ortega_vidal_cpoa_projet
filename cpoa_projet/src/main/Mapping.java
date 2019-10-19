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
import javafx.stage.Window;
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
	private Button modifier;
	@FXML
	private Button supprimer;
	@FXML
	private Button retour;
	@FXML
	private TableView<Revue> tblRevue;
	
	private Window vue;

	
	@Override
	public String toString() {
		return "Ajout de : " + titre.getText() + " (" + tarif.getText() + ")";
	}
	
	 public void initialize(URL location, ResourceBundle resources) {

	        DAOFactory dao = DAOFactory.getDAOFactory(Persistance.ListeMemoire);

	        try {
				this.libelle.setItems(FXCollections.observableArrayList(dao.getPeriodiciteDAO().findAll()));
			} catch (Exception e) {
				System.out.println("Probleme a l'initialisation ");
				e.printStackTrace();
			}
	    }   
	
	@FXML
	public void create() {
		
		Periodicite period = libelle.getValue();
		
		if ((titre.getText().isEmpty()) || (description.getText().isEmpty()) || (tarif.getText().isEmpty()) 
				|| (visuel.getText().isEmpty()) || (period == null) || ((mysql==null) && (list==null))){
		
			//String erreur="";
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.vue);
			alert.setTitle("Erreur lors de la saisie");
			alert.setHeaderText("Un ou plusieurs champs sont mal remplis.");
			//alert.setContentText(erreur);
			alert.showAndWait();

		}
		
		else {	
			DAOFactory daos = null;
			if(mysql.isSelected()) {
				daos = DAOFactory.getDAOFactory(Persistance.MySQL);
			}
			else if(list.isSelected()){
				daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
			}
			try {
				double txt_tarif = Double.parseDouble(tarif.getText());
				affichage.setTextFill(Color.web("black"));
				affichage.setText(toString());
				String txt_titre = titre.getText();
				String txt_description = description.getText();
				String txt_visuel = visuel.getText();
				
		        daos.getRevueDAO().create(new Revue(txt_titre, txt_description, txt_tarif, txt_visuel, period.getId_periodicite()));  
			} 
			catch (Exception e) {
				affichage.setTextFill(Color.web("red"));
				this.affichage.setText("Erreur lors de la creation");
			} 
		}	
	}
	@FXML
	public void update() {
		
	}
	@FXML
	public void delete() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
		try {
			
	        daos.getRevueDAO().delete(tblRevue.getSelectionModel().getSelectedItem());  
		} 
		catch (Exception e) {
			affichage.setTextFill(Color.web("red"));
			this.affichage.setText("Erreur lors de la suppression");
		} 
		
	}
	@FXML
	public void back() {
		
	}

}
