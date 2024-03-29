package main;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.CheckBox;
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
	
	@FXML
	private ComboBox<Client> id_client;
	@FXML
	private ComboBox<Revue> id_revue;
	@FXML
	private ComboBox<Client> recherche_client;
	@FXML
	private ComboBox<Revue> recherche_revue;
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
	private Button recherche;
	@FXML
	private Button retour;
	@FXML
	private CheckBox en_cours;
	@FXML
	private GridPane form;
	@FXML
	private TableView<Abonnement> tblAbonnement;
	@FXML
	private Window vue;
	
	@SuppressWarnings("unchecked")
	public TableView<Abonnement> tblAbonnement() throws Exception {

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
		if(b_create) return "Ajout de : " + id_client.getSelectionModel().getSelectedItem().getNom().trim() +" "+ id_revue.getSelectionModel().getSelectedItem().getTitre().trim();
		else if(b_update) return "Modifiction de : "+ id_client.getSelectionModel().getSelectedItem().getNom().trim() +" "+ id_revue.getSelectionModel().getSelectedItem().getTitre().trim();
		else return "";
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		try {
			id_revue.setItems(FXCollections.observableArrayList(CtrlAccueil.daorev.findAll()));
			id_client.setItems(FXCollections.observableArrayList(CtrlAccueil.daocli.findAll()));
			recherche_revue.setItems(FXCollections.observableArrayList(CtrlAccueil.daorev.findAll()));
			recherche_client.setItems(FXCollections.observableArrayList(CtrlAccueil.daocli.findAll()));
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
	public void valider() throws Exception {
		Client idcli = id_client.getValue();
		Revue idrev = id_revue.getValue();
		
		if ((idcli == null) || (idrev == null) || (date_deb.getChronology()==null) || (date_fin.getChronology()==null)) {			
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
				String txt_date_deb = date_deb.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).trim();
				String txt_date_fin = date_fin.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).trim();
				
				CtrlAccueil.daoabo.create(new Abonnement(idcli.getId_client() ,idrev.getId_revue(), txt_date_deb, txt_date_fin));
				affichage.setText(toString());
			} 
			catch (Exception e) {
				affichage.setText("");
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
				String txt_date_deb = date_deb.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).trim();
				String txt_date_fin = date_fin.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).trim();

				CtrlAccueil.daoabo.update(
						new Abonnement(idcli.getId_client(), idrev.getId_revue(), txt_date_deb, txt_date_fin));
				affichage.setText(toString());

			} 
			catch (Exception e) {
				affichage.setText("");
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
	public void create() throws Exception {
		id_revue.setDisable(false);
		id_client.setDisable(false);
		form.setDisable(false);
		valider.setDisable(false);
		
		id_client.setValue(null);
		id_revue.setValue(null);
		date_deb.setValue(null);;
		date_fin.setValue(null);
	
		b_create=true;
		b_update=false;
	}
	
	@FXML
	public void delete() throws Exception {
		try {
			CtrlAccueil.daoabo.delete(tblAbonnement.getSelectionModel().getSelectedItem()); 
	        List<Abonnement> abos = CtrlAccueil.daoabo.findAll();
	        tblAbonnement.getItems().clear();
	        tblAbonnement.getItems().addAll(abos);
	        
		} 
		catch (Exception e) {
			affichage.setText("");
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
			Abonnement abo = tblAbonnement.getSelectionModel().getSelectedItem();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			LocalDate date_d = LocalDate.parse(abo.getDate_debut(), formatter);
			LocalDate date_f = LocalDate.parse(abo.getDate_fin(), formatter);

			id_client.setValue(CtrlAccueil.daocli.getById(abo.getId_client()));
			id_revue.setValue(CtrlAccueil.daorev.getById(abo.getId_revue()));
			date_deb.setValue(date_d);
			date_fin.setValue(date_f);
			
			id_revue.setDisable(true);
			id_client.setDisable(true);
			form.setDisable(false);
			valider.setDisable(false);
			
			b_create=false;
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
	public void en_cours()throws Exception {
		try {
			List<Abonnement> abo = new ArrayList<Abonnement>();
			
			if(en_cours.isSelected()) {
				int i=0;
				Date date = new Date();
								
				while(i<tblAbonnement.getItems().size()) {
					Date date_d = new SimpleDateFormat("yyyy-MM-dd").parse(tblAbonnement.getItems().get(i).getDate_debut());
					Date date_f = new SimpleDateFormat("yyyy-MM-dd").parse(tblAbonnement.getItems().get(i).getDate_fin());
					if(date_d.compareTo(date)<=0 && date_f.compareTo(date)>=0) {
						abo.add(tblAbonnement.getItems().get(i));
					}
					i++;
				}
				tblAbonnement.getItems().clear();
				tblAbonnement.getItems().addAll(abo);
			}
			else {
				List<Abonnement> abonnements = CtrlAccueil.daoabo.findAll();

				tblAbonnement.getItems().clear();
				tblAbonnement.getItems().addAll(abonnements);
			}
		}
		catch (Exception e) {
			
		}
	}
	
	@FXML
	public void recherche() throws Exception {
		tblAbonnement.getItems().clear();
		List<Abonnement> abonnement = CtrlAccueil.daoabo.findAll();
		tblAbonnement.getItems().addAll(abonnement);
		
		if(recherche_revue.getValue()!=null && recherche_client.getValue()==null){
			List<Abonnement> abo = new ArrayList<Abonnement>();
			
			int i=0;
			int rev = recherche_revue.getValue().getId_revue();
			
			while(i<tblAbonnement.getItems().size()) {
				int rev1 = tblAbonnement.getItems().get(i).getId_revue();
				
				if(rev1 == rev) {
					abo.add(tblAbonnement.getItems().get(i));
				}
				i++;
			}
			tblAbonnement.getItems().clear();
			tblAbonnement.getItems().addAll(abo);
			recherche_revue.setValue(null);
			recherche_client.setValue(null);
		}
		
		else if(recherche_revue.getValue()==null && recherche_client.getValue()!=null){
			List<Abonnement> abo = new ArrayList<Abonnement>();
			
			int i=0;
			int cli = recherche_client.getValue().getId_client();
			
			while(i<tblAbonnement.getItems().size()) {
				int cli1 = tblAbonnement.getItems().get(i).getId_client();
				
				if(cli1 == cli) {
					abo.add(tblAbonnement.getItems().get(i));
				}
				i++;
			}
			tblAbonnement.getItems().clear();
			tblAbonnement.getItems().addAll(abo);
			recherche_revue.setValue(null);
			recherche_client.setValue(null);
		}
		
		else if(recherche_revue.getValue()!=null && recherche_client.getValue()!=null){
			List<Abonnement> abo = new ArrayList<Abonnement>();
			
			int i=0;
			int cli = recherche_client.getValue().getId_client();
			int rev = recherche_revue.getValue().getId_revue();
			
			while(i<tblAbonnement.getItems().size()) {
				int cli1 = tblAbonnement.getItems().get(i).getId_client();
				int rev1 = recherche_revue.getItems().get(i).getId_revue();
				
				if(cli1==cli && rev==rev1) {
					abo.add(tblAbonnement.getItems().get(i));
				}
				i++;
			}
			tblAbonnement.getItems().clear();
			tblAbonnement.getItems().addAll(abo);
			recherche_revue.setValue(null);
			recherche_client.setValue(null);
		}
	}

	@FXML
	public void retour() throws IOException{
		CtrlAccueil.daoabo=null;
		CtrlAccueil.daocli=null;
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
