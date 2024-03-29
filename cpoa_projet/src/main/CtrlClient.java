package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
	private boolean b_update;
	public static int id_cli;
	
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
	private TextField recherche_nom;
	@FXML
	private TextField recherche_prenom;
	@FXML
	private Label affichage;
	@FXML
	private Button creer;
	@FXML
	private Button modifier;
	@FXML
	private Button supprimer;
	@FXML
	private Button btn_abo;
	@FXML
	private Button valider;
	@FXML
	private Button recherche;
	@FXML
	private Button retour;
	@FXML
	private GridPane form;
	@FXML
	private TableView<Client> tblClient;
	@FXML
	private Window vue;
	
	@SuppressWarnings("unchecked")
	public TableView<Client> tblClient() throws Exception {

		TableColumn<Client, String> colIdClient = new TableColumn<>("id_Client");
		TableColumn<Client, String> colNom = new TableColumn<>("Nom");
		TableColumn<Client, String> colPrenom = new TableColumn<>("Pr�nom");
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
		if(b_create) return "Ajout de : " + nom.getText().trim() +" "+ prenom.getText().trim();
		else if(b_update) return "Modifiction de : "+ nom.getText().trim() +" "+ prenom.getText().trim();
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
	public void valider() throws Exception{
		if ((nom.getText().trim().isEmpty()) || (prenom.getText().trim().isEmpty())
				|| (no_rue.getText().trim().isEmpty()) || (voie.getText().trim().isEmpty()) || (cde.getText().trim().isEmpty()) || 
				(ville.getText().trim().isEmpty()) || (pays.getText().trim().isEmpty())) {			
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
				String txt_nom = nom.getText().trim();
				String txt_prenom = prenom.getText().trim();
				String txt_no_rue = no_rue.getText().trim();
				String txt_voie = voie.getText().trim();
				String txt_cde = cde.getText().trim();
				String txt_ville = ville.getText().trim();
				String txt_pays = pays.getText().trim();

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
				String txt_nom = nom.getText().trim();
				String txt_prenom = prenom.getText().trim();
				String txt_no_rue = no_rue.getText().trim();
				String txt_voie = voie.getText().trim();
				String txt_cde = cde.getText().trim();
				String txt_ville = ville.getText().trim();
				String txt_pays = pays.getText().trim();

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
	public void create() throws Exception{
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
		b_update=false;
	}
	
	@FXML
	public void delete() throws Exception{
		try {
			CtrlAccueil.daocli.delete(tblClient.getSelectionModel().getSelectedItem()); 
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
	public void update() throws Exception{
		try {			
			Client client=tblClient.getSelectionModel().getSelectedItem();
						
			nom.setText(client.getNom().trim());
			prenom.setText(client.getPrenom().trim());
			no_rue.setText(client.getNo_rue().trim());
			voie.setText(client.getVoie().trim());
			cde.setText(client.getCode_postal().trim());
			ville.setText(client.getVille().trim());
			pays.setText(client.getPays().trim());
			
			form.setDisable(false);
			valider.setDisable(false);
			
			b_create=false;
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
	public void abonnement() throws IOException {
		try {
			id_cli = tblClient.getSelectionModel().getSelectedItem().getId_client();
			
			Stage stage =(Stage) btn_abo.getScene().getWindow();
			stage.close();
			Stage stage1 = new Stage();
			
			URL fxmlURL = getClass().getResource("fenetres/fenetre_Abo.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 679.0, 281.0);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			stage1.setScene(scene);
			stage1.setTitle("Abonnements de " + tblClient.getSelectionModel().getSelectedItem().getNom() + " " +
					tblClient.getSelectionModel().getSelectedItem().getPrenom());
			stage1.show();
		} 
		catch (Exception e) {
			affichage.setText("");
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Un probleme est survenue lors de l'affichage des abonnements de votre client");
			alert.setHeaderText("Aucun Client selectionne");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}	
	}
	
	@FXML
	public void recherche() throws Exception {
		tblClient.getItems().clear();
		List<Client> clients = CtrlAccueil.daocli.findAll();
		tblClient.getItems().addAll(clients);
		
		if(recherche_nom.getText().trim().isEmpty() && !recherche_prenom.getText().trim().isEmpty()) {
			affichage.setText("");
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Un probleme est survenue lors de la recherche de vos Clients");
			alert.setHeaderText("Recherche par nom, ou par nom et prenom");
			alert.showAndWait();
		}
		
		else if(!recherche_nom.getText().trim().isEmpty() && recherche_prenom.getText().trim().isEmpty()) {
			List<Client> cli = new ArrayList<Client>();
				
			int i=0;
			String nom = recherche_nom.getText().trim();
								
			while(i<tblClient.getItems().size()) {
				String nom1 = tblClient.getItems().get(i).getNom().trim();
				if(nom1.compareTo(nom) == 0) {
					cli.add(tblClient.getItems().get(i));
				}
				i++;
			}
			tblClient.getItems().clear();
			tblClient.getItems().addAll(cli);
		}
		
		else if(!recherche_nom.getText().trim().isEmpty() && !recherche_prenom.getText().trim().isEmpty()) {
			List<Client> cli = new ArrayList<Client>();
			
			int i=0;
			String nom = recherche_nom.getText().trim();
			String prenom = recherche_prenom.getText().trim();
								
			while(i<tblClient.getItems().size()) {
				String nom1 = tblClient.getItems().get(i).getNom().trim();
				String prenom1 = tblClient.getItems().get(i).getPrenom().trim();
				if(nom1.compareTo(nom) == 0 && prenom1.compareTo(prenom) == 0) {
					cli.add(tblClient.getItems().get(i));
				}
				i++;
			}
			tblClient.getItems().clear();
			tblClient.getItems().addAll(cli);
		}
	}
	
	@FXML
	public void retour() throws IOException{
		CtrlAccueil.daocli=null;
		CtrlAccueil.daoabo=null;
		CtrlAccueil.daorev=null;
		
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
