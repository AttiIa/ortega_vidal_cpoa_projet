package main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.metier.Abonnement;
import dao.metier.Client;
import dao.metier.Revue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CtrlAbonnement implements Initializable{
	
	private boolean b_create=false;
	private boolean b_update=false;
	private boolean b_delete=false;
	
	@FXML
	private ComboBox<Client> id_client;
	@FXML
	private ComboBox<Revue> id_revue;
	@FXML
	private DatePicker date_deb;
	@FXML
	private DatePicker date_fin;
	@FXML
	private Label affichage;
	@FXML
	private Button creer;
	@FXML
	private Button modifier;
	@FXML
	private Button supprimer;
	@FXML
	private Button valider;
	@FXML
	private Button retour;
	@FXML
	private GridPane form;
	@FXML
	private TableView<Abonnement> tblAbonnement;
	@FXML
	private Window vue;
	@FXML
	private Window accueil;
	
	public TableView<Abonnement> tblAbonnement() {

		TableColumn<Abonnement, String> colIdClient = new TableColumn<>("id_client");
		TableColumn<Abonnement, String> colIdRevue = new TableColumn<>("id_revue");
		TableColumn<Abonnement, String> colDate_Deb = new TableColumn<>("date_debut");
		TableColumn<Abonnement, String> colDate_Fin = new TableColumn<>("date_fin");
		
		colIdClient.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("id_client"));
		colIdRevue.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("id_revue"));
		colDate_Deb.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("date_debut"));
		colDate_Fin.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("date_fin"));		

		tblAbonnement.getColumns().setAll(colIdClient, colIdRevue, colDate_Deb, colDate_Fin);

		List<Abonnement> abonnements = CtrlAccueil.daoabo.findAll();
		
		tblAbonnement.getItems().addAll(abonnements);
		return tblAbonnement;
	}
	
	@Override
	public String toString() {
		if(b_create) return "Ajout de : " + id_client.getSelectionModel().getSelectedItem().getNom() +" "+ id_revue.getSelectionModel().getSelectedItem().getTitre();
		else if(b_delete) return "Suppression de : " + id_client.getSelectionModel().getSelectedItem().getNom() +" "+ id_revue.getSelectionModel().getSelectedItem().getTitre();
		else if(b_update) return "Modifiction de : "+ id_client.getSelectionModel().getSelectedItem().getNom() +" "+ id_revue.getSelectionModel().getSelectedItem().getTitre();
		else return "";
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		try {
			id_revue.setItems(FXCollections.observableArrayList(CtrlAccueil.daorev.findAll()));
			id_client.setItems(FXCollections.observableArrayList(CtrlAccueil.daocli.findAll()));
			tblAbonnement();
		}
		catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Probleme a l'initialisation");
			alert.setHeaderText("Un probleme est survenue lors de l'initialisation de vos Abonnements");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	}
	
	@FXML
	public void valider(){
		Client idcli = id_client.getValue();
		Revue idrev = id_revue.getValue();
		
		if ((idcli == null) || (idrev == null) || (date_deb.getPromptText().isEmpty()) || (date_fin.getPromptText().isEmpty())) {			
			affichage.setTextFill(Color.web("red"));
			affichage.setText("Les champs ne sont pas tous valides");

			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Erreur lors de la saisie");
			alert.setHeaderText("Un ou plusieurs champs sont mal remplis.");
			alert.showAndWait();
		}
		else if(b_create) {
			
			try {				
				String txt_date_deb = date_deb.getPromptText();
				String txt_date_fin = date_fin.getPromptText();
				affichage.setText(toString());

				CtrlAccueil.daoabo.create(new Abonnement(idcli.getId_client(),idrev.getId_revue(), txt_date_deb, txt_date_fin));
			} 
			catch (Exception e) {
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.initOwner(vue);
				alert.setTitle("La creation a echouee");
				alert.setHeaderText("Un probleme est survenue lors de la creation de votre Abonnement");
				alert.setContentText(e.toString());
				alert.showAndWait();
			}			
		}
		
		else if(b_update) {
			try {
				String txt_date_deb = date_deb.getPromptText();
				String txt_date_fin = date_fin.getPromptText();
				affichage.setText(toString());

				CtrlAccueil.daoabo.update(
						new Abonnement(tblAbonnement.getSelectionModel().getSelectedItem().getId_client(), idrev.getId_revue(), txt_date_deb, txt_date_fin));
			} 
			catch (Exception e) {
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.initOwner(vue);
				alert.setTitle("La modification a echouee");
				alert.setHeaderText("Un probleme est survenue lors de la modification de votre Abonnement");
				alert.setContentText(e.toString());
				alert.showAndWait();
			}
		}
		b_create=false;
		b_update=false;
		List<Abonnement> abo = CtrlAccueil.daoabo.findAll();
		tblAbonnement.getItems().clear();
		tblAbonnement.getItems().addAll(abo); 
		
		form.setDisable(true);
		valider.setDisable(true);		
	}
	
	@FXML
	public void create() {
		form.setDisable(false);
		valider.setDisable(false);
		
		date_deb.setPromptText("");
		date_fin.setPromptText("");
	
		//libelle.setValue();

		
		b_create=true;
		b_delete=false;
		b_update=false;
	}
	
	@FXML
	public void delete() {
		try {
			b_delete=true;
			CtrlAccueil.daoabo.delete(tblAbonnement.getSelectionModel().getSelectedItem()); 
			affichage.setText(toString());;
	        List<Abonnement> abos = CtrlAccueil.daoabo.findAll();
	        tblAbonnement.getItems().clear();
	        tblAbonnement.getItems().addAll(abos);
		} 
		catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Un probleme est survenue lors de la suppression de votre Abonnement");
			alert.setHeaderText("Aucun Abonnement selectionne");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}		
	}
	
	@FXML
	public void update() {
		try {			
			Abonnement abo=tblAbonnement.getSelectionModel().getSelectedItem();
			//Periodicite period = libelle.getValue();
						
			date_deb.setPromptText(abo.getDate_debut());
			date_deb.setPromptText(abo.getDate_fin());
			
			
			form.setDisable(false);
			valider.setDisable(false);
			
			b_create=false;
			b_delete=false;
			b_update=true;
		}
		catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Un probleme est survenue lors de la modification de votre Abonnement");
			alert.setHeaderText("Aucun Abonnement selectionne");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	}
	
	@FXML
	public void retour() throws IOException{
		Stage stage =(Stage) retour.getScene().getWindow();
		stage.close();
		Stage stage1 = new Stage();
		
		URL fxmlURL = getClass().getResource("fenetre.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
		Node root = fxmlLoader.load();
		Scene scene = new Scene((VBox) root, 498.0, 112.0);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		stage1.setScene(scene);
		stage1.setTitle("Accueil");
		stage1.show();		
	}	
}
