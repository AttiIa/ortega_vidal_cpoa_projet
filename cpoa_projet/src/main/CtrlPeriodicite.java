package main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.metier.Periodicite;
import dao.metier.Revue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class CtrlPeriodicite implements Initializable{
	
	private boolean b_create;
	private boolean b_delete;
	private boolean b_update;

	@FXML
	private TextField libelle;
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
	private TableView<Periodicite> tblPeriodicite;
	@FXML
	private Window vue;
	@FXML
	private Window accueil;
	
	public TableView<Periodicite> tblPeriodicite() {
		
		TableColumn<Periodicite, String> colIdPeriodicite = new TableColumn<>("id_periodicite");
		TableColumn<Periodicite, String> colLibelle = new TableColumn<>("libelle");
		
		colIdPeriodicite.setCellValueFactory(new PropertyValueFactory<Periodicite, String>("id_periodicite"));
		colLibelle.setCellValueFactory(new PropertyValueFactory<Periodicite, String>("libelle"));
		
		tblPeriodicite.getColumns().setAll(colIdPeriodicite, colLibelle);
		
		List<Periodicite> periodes = CtrlAccueil.daoper.findAll();	
		
		tblPeriodicite.getItems().addAll(periodes);
		return tblPeriodicite;
	}
	
	@Override
	public String toString() {
		if(b_create) return "Ajout de : " + libelle.getText();
		else if(b_delete) return "Suppression de : " + tblPeriodicite.getSelectionModel().getSelectedItem().getLibelle();
		else if(b_update) return "Modifiction de : " + libelle.getText();
		else return "";
	}

	public void initialize(URL location, ResourceBundle resources) {
		try {
			tblPeriodicite();
		} catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Probleme a l'initialisation");
			alert.setHeaderText("Un probleme est survenue lors de l'initialisation de vos Periodes");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	}
	
	@FXML
	public void valider(){
		if (libelle.getText().isEmpty()) {			
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
				String txt_libelle = libelle.getText();
				affichage.setTextFill(Color.web("black"));
				affichage.setText(toString());

				CtrlAccueil.daoper.create(new Periodicite(txt_libelle));
			} catch (Exception e) {
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
				String txt_libelle = libelle.getText();
				affichage.setTextFill(Color.web("black"));
				affichage.setText(toString());

				CtrlAccueil.daoper.update(new Periodicite(tblPeriodicite.getSelectionModel().getSelectedItem().getId_periodicite(), txt_libelle));
			} catch (Exception e) {
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.initOwner(vue);
				alert.setTitle("La modification a echouee");
				alert.setHeaderText("Un probleme est survenue lors de la modification de votre Periode");
				alert.setContentText(e.toString());
				alert.showAndWait();
			}
		}
		b_create=false;
		b_update=false;
		
		List<Periodicite> period = CtrlAccueil.daoper.findAll();
		tblPeriodicite.getItems().clear();
		tblPeriodicite.getItems().addAll(period); 
		
		form.setDisable(true);
		valider.setDisable(true);		
	}
	
	@FXML
	public void create() {
		form.setDisable(false);
		valider.setDisable(false);
		
		libelle.setText("");
		
		b_create=true;
		b_update=false;
	}
	
	@FXML
	public void delete(){
		try {
			b_delete=true;
			CtrlAccueil.daoper.delete(tblPeriodicite.getSelectionModel().getSelectedItem());  
	        List<Periodicite> period = CtrlAccueil.daoper.findAll();
	        tblPeriodicite.getItems().clear();
	        tblPeriodicite.getItems().addAll(period);			
		}
		catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Un probleme est survenue lors de la suppression de votre Periode");
			alert.setHeaderText("Aucune Periode selectionnee");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
		
	}
	
	@FXML
	public void update(){
		try {
			Periodicite period = tblPeriodicite.getSelectionModel().getSelectedItem();
			
			libelle.setText(period.getLibelle());
			
			form.setDisable(false);
			valider.setDisable(false);
			
			b_create=false;
			b_update=true;
			
		}
		catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Un probleme est survenue lors de la modification de votre Periode");
			alert.setHeaderText("Aucune Periode selectionnee");
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
