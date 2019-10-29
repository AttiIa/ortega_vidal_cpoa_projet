package main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.metier.Client;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CtrlClient implements Initializable{

	private boolean b_create;
	private boolean b_delete;
	private boolean b_update;
	
	
	@FXML
	private TextField nom;
	@FXML
	private TextField prenom;
	@FXML
	private TextField no_rue;
	@FXML
	private TextField voie;
	@FXML
	private TextField cde;
	@FXML
	private TextField ville;
	@FXML
	private TextField pays;
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
	private TableView<Client> tblClient;
	@FXML
	private Window vue;
	@FXML
	private Window accueil;
	
	public TableView<Client> tblClient() {

		TableColumn<Client, String> colIdClient = new TableColumn<>("id_Client");
		TableColumn<Client, String> colNom = new TableColumn<>("Nom");
		TableColumn<Client, String> colPrenom = new TableColumn<>("Prénom");
		TableColumn<Client, String> colNoRue = new TableColumn<>("No de rue");
		TableColumn<Client, String> colVoie = new TableColumn<>("Voie");
		TableColumn<Client, String> colCdePostal = new TableColumn<>("Code Postal");
		TableColumn<Client, String> colVille = new TableColumn<>("Code Ville");
		TableColumn<Client, String> colPays = new TableColumn<>("Code Pays");

		colIdClient.setCellValueFactory(new PropertyValueFactory<Client, String>("id_client"));
		colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
		colPrenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
		colNoRue.setCellValueFactory(new PropertyValueFactory<Client, String>("no_rue"));
		colVoie.setCellValueFactory(new PropertyValueFactory<Client, String>("voie"));
		colCdePostal.setCellValueFactory(new PropertyValueFactory<Client, String>("code_postal"));
		colVille.setCellValueFactory(new PropertyValueFactory<Client, String>("ville"));
		colPays.setCellValueFactory(new PropertyValueFactory<Client, String>("pays"));

		tblClient.getColumns().setAll(colIdClient, colNom, colPrenom, colNoRue, colVoie, colCdePostal, colVille, colPays );

		List<Client> clients = CtrlAccueil.daocli.findAll();
		
		tblClient.getItems().addAll(clients);
		return tblClient;

	}
	
	@Override
	public String toString() {
		if(b_create) return "Ajout de : " + nom.getText() +" "+ prenom.getText();
		else if(b_delete) return "Suppression de : " + nom.getText() +" "+ prenom.getText();
		else if(b_update) return "Modifiction de : "+ nom.getText() +" "+ prenom.getText();
		else return "";
	}	

	public void initialize(URL location, ResourceBundle resources) {
		try {
			tblClient();
		} 
		catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Probleme a l'initialisation");
			alert.setHeaderText("Un probleme est survenue lors de l'initialisation de vos Clients");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	}
	
	@FXML
	public void valider(){
		if ((nom.getText().isEmpty()) || (prenom.getText().isEmpty())
				|| (no_rue.getText().isEmpty()) || (voie.getText().isEmpty()) || (cde.getText().isEmpty()) || (ville.getText().isEmpty()) || (pays.getText().isEmpty())) {			
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
				String txt_nom = nom.getText();
				String txt_prenom = prenom.getText();
				String txt_no_rue = no_rue.getText();
				String txt_voie = voie.getText();
				String txt_cde = cde.getText();
				String txt_ville = ville.getText();
				String txt_pays = pays.getText();

				CtrlAccueil.daocli.create(new Client(txt_nom, txt_prenom, txt_no_rue, txt_voie, txt_cde, txt_ville, txt_pays ));
				affichage.setText(toString());

			} 
			catch (Exception e) {
				affichage.setText("");
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.initOwner(vue);
				alert.setTitle("La creation a echouee");
				alert.setHeaderText("Un probleme est survenue lors de la creation de votre Periode");
				alert.setContentText(e.toString());
				alert.showAndWait();
			}			
		}
		
		else if(b_update) {
			try {
				String txt_nom = nom.getText();
				String txt_prenom = prenom.getText();
				String txt_no_rue = no_rue.getText();
				String txt_voie = voie.getText();
				String txt_cde = cde.getText();
				String txt_ville = ville.getText();
				String txt_pays = pays.getText();

				CtrlAccueil.daocli.update(new Client(tblClient.getSelectionModel().getSelectedItem().getId_client(),txt_nom, txt_prenom, txt_no_rue, txt_voie, txt_cde, txt_ville, txt_pays ));
				affichage.setText(toString());
			} 
			catch (Exception e) {
				affichage.setText("");
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.initOwner(vue);
				alert.setTitle("La modification a echouee");
				alert.setHeaderText("Un probleme est survenue lors de la modification de votre Client");
				alert.setContentText(e.toString());
				alert.showAndWait();
			}
		}
		b_create=false;
		b_update=false;
		List<Client> client = CtrlAccueil.daocli.findAll();
		tblClient.getItems().clear();
		tblClient.getItems().addAll(client); 
		
		form.setDisable(true);
		valider.setDisable(true);		
	}
	
	@FXML
	public void create() {
		form.setDisable(false);
		valider.setDisable(false);
		
		nom.setText("");
		prenom.setText("");
		no_rue.setText("");
		voie.setText("");
		cde.setText("");
		ville.setText("");
		pays.setText("");
		
		b_create=true;
		b_delete=false;
		b_update=false;
	}
	
	@FXML
	public void delete() {
		try {
			b_delete=true;
			CtrlAccueil.daocli.delete(tblClient.getSelectionModel().getSelectedItem()); 
			affichage.setText(toString());;
	        List<Client> clients = CtrlAccueil.daocli.findAll();
	        tblClient.getItems().clear();
	        tblClient.getItems().addAll(clients);
		} 
		catch (Exception e) {
			affichage.setText("");
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Un probleme est survenue lors de la suppression de votre client");
			alert.setHeaderText("Aucun Client selectionne");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}		
	}
	
	@FXML
	public void update() {
		try {			
			Client client=tblClient.getSelectionModel().getSelectedItem();
						
			nom.setText(client.getNom());
			prenom.setText(client.getPrenom());
			no_rue.setText(client.getNo_rue());
			voie.setText(client.getVoie());
			cde.setText(client.getCode_postal());
			ville.setText(client.getVille());
			pays.setText(client.getPays());
			
			form.setDisable(false);
			valider.setDisable(false);
			
			b_create=false;
			b_delete=false;
			b_update=true;
		}
		catch (Exception e) {
			affichage.setText("");
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Un probleme est survenue lors de la modification de votre Client");
			alert.setHeaderText("Aucun Client selectionne");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	}
	
	@FXML
	public void retour() throws IOException{
		Stage stage =(Stage) retour.getScene().getWindow();
		stage.close();
		Stage stage1 = new Stage();
		
		URL fxmlURL = getClass().getResource("fenetres/fenetre.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
		Node root = fxmlLoader.load();
		Scene scene = new Scene((VBox) root, 498.0, 112.0);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		stage1.setScene(scene);
		stage1.setTitle("Accueil");
		stage1.show();		
	}	
}
