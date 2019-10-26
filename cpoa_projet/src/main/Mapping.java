package main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.metier.Periodicite;
import dao.metier.Revue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private Button modifier;
	@FXML
	private Button supprimer;
	@FXML
	private Button retour;
	@FXML
	private TableView<Revue> tblRevue;
	@FXML
	private Window vue;
	@FXML
	private Window accueil;

	public TableView<Revue> tblRevue() {

		TableColumn<Revue, String> colIdRevue = new TableColumn<>("id_revue");
		TableColumn<Revue, String> colTitre = new TableColumn<>("Titre");
		TableColumn<Revue, String> colDescription = new TableColumn<>("Description");
		TableColumn<Revue, String> colTarif = new TableColumn<>("Tarif");
		TableColumn<Revue, String> colPeriodicite = new TableColumn<>("Periodicite");
		TableColumn<Revue, String> colVisuel = new TableColumn<>("Visuel");

		colIdRevue.setCellValueFactory(new PropertyValueFactory<Revue, String>("id_revue"));
		colTitre.setCellValueFactory(new PropertyValueFactory<Revue, String>("titre"));
		colDescription.setCellValueFactory(new PropertyValueFactory<Revue, String>("description"));
		colTarif.setCellValueFactory(new PropertyValueFactory<Revue, String>("tarif_numero"));
		colPeriodicite.setCellValueFactory(new PropertyValueFactory<Revue, String>("id_periodicite"));
		colVisuel.setCellValueFactory(new PropertyValueFactory<Revue, String>("visuel"));

		this.tblRevue.getColumns().setAll(colIdRevue, colTitre, colDescription, colTarif, colPeriodicite, colVisuel);

		List<Revue> revues = DAOFactory.getDAOFactory(Persistance.MySQL).getRevueDAO().findAll();

		this.tblRevue.getItems().addAll(revues);
		return tblRevue;

	}

	@Override
	public String toString() {
		return "Ajout de : " + titre.getText() + " (" + tarif.getText() + ")";
	}

	public void initialize(URL location, ResourceBundle resources) {

		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
 	
		try {
			this.libelle.setItems(FXCollections.observableArrayList(dao.getPeriodiciteDAO().findAll()));
			tblRevue();
		} catch (Exception e) {
			System.out.println("Probleme a l'initialisation ");
			e.printStackTrace();
		}
	}

	@FXML
	public void create() {

		Periodicite period = libelle.getValue();


		if ((titre.getText().isEmpty()) || (description.getText().isEmpty()) || (tarif.getText().isEmpty())
				|| (visuel.getText().isEmpty()) || (period == null) || ((mysql == null) && (list == null))) {
			affichage.setTextFill(Color.web("red"));
			this.affichage.setText("Les champs ne sont pas tous valides");

			
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
			if (mysql.isSelected()) {
				daos = DAOFactory.getDAOFactory(Persistance.MySQL);
			} 
			else if (list.isSelected()) {
				daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
			}
			else {
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.initOwner(this.vue);
				alert.setTitle("Erreur : aucune persistance selectionnée");
				//alert.setContentText(erreur);
				alert.showAndWait();
			}
			
			try {
				double txt_tarif = Double.parseDouble(tarif.getText());
				String txt_titre = titre.getText();
				String txt_description = description.getText();
				String txt_visuel = visuel.getText();
				affichage.setTextFill(Color.web("black"));
				affichage.setText(toString());

				daos.getRevueDAO().create(
						new Revue(txt_titre, txt_description, txt_tarif, txt_visuel, period.getId_periodicite()));
			} catch (Exception e) {
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.initOwner(this.vue);
				alert.setTitle("La creation a echouee");
				alert.setHeaderText("Un probleme est survenue lors de la creation de votre Revue");
				//alert.setContentText(erreur);
				alert.showAndWait();
			}
		}
		
		List<Revue> revues = DAOFactory.getDAOFactory(Persistance.MySQL).getRevueDAO().findAll();
		tblRevue.getItems().clear();
		this.tblRevue.getItems().addAll(revues);
		
	
		
	}

	@FXML
	public void update() {

	}


	
	@FXML
	public void delete() {


		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
		try {
			
	        daos.getRevueDAO().delete(tblRevue.getSelectionModel().getSelectedItem());  
	        List<Revue> revues = DAOFactory.getDAOFactory(Persistance.MySQL).getRevueDAO().findAll();
			tblRevue.getItems().clear();
			this.tblRevue.getItems().addAll(revues);
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
