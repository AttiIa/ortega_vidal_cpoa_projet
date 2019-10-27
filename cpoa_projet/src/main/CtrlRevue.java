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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import javafx.scene.control.*;


public class CtrlRevue implements Initializable {
	
	private boolean b_create=false;
	private boolean b_update=false;	

	@FXML
	private TextField titre;
	@FXML
	private TextField tarif;
	@FXML
	private TextField visuel;
	@FXML
	private ComboBox<Periodicite> libelle;
	@FXML
	private TextArea description;		
	@FXML
	private Label affichage;
	@FXML
	private Button creer;
	@FXML
	private Button modifier;
	@FXML
	private Button supprimer;
	@FXML
	private Button valide;
	@FXML
	private Button retour;
	@FXML
	private GridPane form;
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

		List<Revue> revues = CtrlAccueil.daorev.findAll();
		
		//List<Revue> revues = DAOFactory.getDAOFactory(Persistance.MySQL).getRevueDAO().findAll();

		this.tblRevue.getItems().addAll(revues);
		return tblRevue;

	}

	@Override
	public String toString() {
		return "Ajout de : " + titre.getText() + " (" + tarif.getText() + ")";
	}

	public void initialize(URL location, ResourceBundle resources) {
 	
		try {
			this.libelle.setItems(FXCollections.observableArrayList(CtrlAccueil.daoper.findAll()));
			tblRevue();
		} catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.vue);
			alert.setTitle("Probleme a l'initialisation");
			alert.setHeaderText("Un probleme est survenue lors de l'initialisation de vos Revues");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	}

	@FXML
	public void valide() {

		Periodicite period = libelle.getValue();

		if ((titre.getText().isEmpty()) || (description.getText().isEmpty()) || (tarif.getText().isEmpty())
				|| (visuel.getText().isEmpty()) || (period == null)) {
			affichage.setTextFill(Color.web("red"));
			this.affichage.setText("Les champs ne sont pas tous valides");

			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.vue);
			alert.setTitle("Erreur lors de la saisie");
			alert.setHeaderText("Un ou plusieurs champs sont mal remplis.");
			alert.showAndWait();
		

		}

		else if(b_create){
			try {
				double txt_tarif = Double.parseDouble(tarif.getText());
				String txt_titre = titre.getText();
				String txt_description = description.getText();
				String txt_visuel = visuel.getText();
				affichage.setTextFill(Color.web("black"));
				affichage.setText(toString());

				CtrlAccueil.daorev.create(
						new Revue(txt_titre, txt_description, txt_tarif, txt_visuel, period.getId_periodicite()));
			} catch (Exception e) {
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.initOwner(this.vue);
				alert.setTitle("La creation a echouee");
				alert.setHeaderText("Un probleme est survenue lors de la creation de votre Revue");
				alert.setContentText(e.toString());
				alert.showAndWait();
			}
		}
		else if(b_update) {
			try {
				double txt_tarif = Double.parseDouble(tarif.getText());
				String txt_titre = titre.getText();
				String txt_description = description.getText();
				String txt_visuel = visuel.getText();
				affichage.setTextFill(Color.web("black"));
				affichage.setText(toString());

				CtrlAccueil.daorev.update(
						new Revue(tblRevue.getSelectionModel().getSelectedItem().getId_revue(), txt_titre, txt_description, txt_tarif, txt_visuel, period.getId_periodicite()));				
			}
			catch (Exception e) {
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.initOwner(this.vue);
				alert.setTitle("La modification a echouee");
				alert.setHeaderText("Un probleme est survenue lors de la modification de votre Revue");
				alert.setContentText(e.toString());
				alert.showAndWait();
			}
			
		}
		b_create=false;
		b_update=false;
		
		List<Revue> revues = DAOFactory.getDAOFactory(Persistance.MySQL).getRevueDAO().findAll();
		tblRevue.getItems().clear();
		tblRevue.getItems().addAll(revues); 
		
		form.setDisable(true);
		valide.setDisable(true);
	}
	
	@FXML
	public void create() {
		form.setDisable(false);
		valide.setDisable(false);
		
		titre.setText("");
		description.setText("");
		tarif.setText("");
		//libelle.setValue();
		visuel.setText("");
		
		b_create=true;
		b_update=false;
	}

	@FXML
	public void update() {
		try {			
			Revue revue=tblRevue.getSelectionModel().getSelectedItem();
			//Periodicite period = libelle.getValue();
						
			titre.setText(revue.getTitre());
			description.setText(revue.getDescription());
			tarif.setText(String.valueOf(revue.getTarif_numero()));
			//libelle.setValue();
			visuel.setText(revue.getVisuel());
			
			form.setDisable(false);
			valide.setDisable(false);
			
			b_create=false;
			b_update=true;
		}
		catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.vue);
			alert.setTitle("Un probleme est survenue lors de la modification de votre Revue");
			alert.setHeaderText("Aucune Revue selectionnee");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	}
	
	@FXML
	public void delete() {
		try {			
			CtrlAccueil.daorev.delete(tblRevue.getSelectionModel().getSelectedItem());  
	        List<Revue> revues = DAOFactory.getDAOFactory(Persistance.MySQL).getRevueDAO().findAll();
			tblRevue.getItems().clear();
			this.tblRevue.getItems().addAll(revues);
		} 
		catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.vue);
			alert.setTitle("Un probleme est survenue lors de la suppression de votre Revue");
			alert.setHeaderText("Aucune Revue selectionnee");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}		
	}

	@FXML
	public void back() {		
		
	}	
}
