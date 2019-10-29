package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.interfaces.AbonnementDAO;
import dao.interfaces.ClientDAO;
import dao.interfaces.PeriodiciteDAO;
import dao.interfaces.RevueDAO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CtrlAccueil implements Initializable {
	
	public static ClientDAO daocli;
	public static PeriodiciteDAO daoper;
	public static RevueDAO daorev;
	public static AbonnementDAO daoabo;
	
	@FXML
	private RadioButton mysql;
	@FXML
	private RadioButton list;
	@FXML
	private Button Client;
	@FXML
	private Button Abonnement;
	@FXML
	private Button Periodicite;
	@FXML
	private Button Revue;
	@FXML
	private Window vue;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	
	}	

	@FXML
	public void Revue() throws IOException {
		if (mysql.isSelected()) {
			daorev = DAOFactory.getDAOFactory(Persistance.MySQL).getRevueDAO();
			daoper = DAOFactory.getDAOFactory(Persistance.MySQL).getPeriodiciteDAO();
		} 
		else if (list.isSelected()) {
			daorev = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getRevueDAO();
			daoper = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getPeriodiciteDAO();
		}
		else {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.vue);
			alert.setTitle("Erreur : aucune persistance selectionnee");
			alert.showAndWait();
		}		
		
		if (daorev!=null) {
			Stage stage =(Stage) Revue.getScene().getWindow();
			stage.close();
			Stage stage1 = new Stage();
			
			URL fxmlURL = getClass().getResource("fenetres/fenetre_ajout_revue.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 994.0, 623.0);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			stage1.setScene(scene);
			stage1.setTitle("Gestion des revues");
			stage1.show();
		}					
	}
	
	@FXML
	public void Periodicite() throws IOException {
		if (mysql.isSelected()) {
			daoper = DAOFactory.getDAOFactory(Persistance.MySQL).getPeriodiciteDAO();
		} 
		else if (list.isSelected()) {
			daoper = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getPeriodiciteDAO();
		}
		else {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.vue);
			alert.setTitle("Erreur : aucune persistance selectionnee");
			alert.showAndWait();
		}		
		
		if (daoper!=null) {
			Stage stage =(Stage) Periodicite.getScene().getWindow();
			stage.close();
			Stage stage1 = new Stage();
			
			URL fxmlURL = getClass().getResource("fenetres/fenetre_ajout_periodicite.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 600.0, 281.0);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			stage1.setScene(scene);
			stage1.setTitle("Gestion des Periodicite");
			stage1.show();
		}
	}
	
	@FXML
	public void Client() throws IOException {
		if (mysql.isSelected()) {
			daocli = DAOFactory.getDAOFactory(Persistance.MySQL).getClientDAO();
		} 
		else if (list.isSelected()) {
			daocli = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO();
		}
		else {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.vue);
			alert.setTitle("Erreur : aucune persistance selectionnee");
			alert.showAndWait();
		}		
		
		if (daocli!=null) {
			Stage stage =(Stage) Client.getScene().getWindow();
			stage.close();
			Stage stage1 = new Stage();
			
			URL fxmlURL = getClass().getResource("fenetres/fenetre_ajout_client.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 674.0, 600.0);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			stage1.setScene(scene);
			stage1.setTitle("Gestion des Clients");
			stage1.show();
		}
	}
	
	@FXML
	public void Abonnement() throws IOException {
		if (mysql.isSelected()) {
			daoabo = DAOFactory.getDAOFactory(Persistance.MySQL).getAbonnementDAO();
			daocli = DAOFactory.getDAOFactory(Persistance.MySQL).getClientDAO();
			daorev = DAOFactory.getDAOFactory(Persistance.MySQL).getRevueDAO();
		} 
		else if (list.isSelected()) {
			daoabo = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getAbonnementDAO();
			daocli = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO();
			daorev = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getRevueDAO();
		}
		else {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.vue);
			alert.setTitle("Erreur : aucune persistance selectionnee");
			alert.showAndWait();
		}		
		
		if (daoabo!=null) {
			Stage stage =(Stage) Abonnement.getScene().getWindow();
			stage.close();
			Stage stage1 = new Stage();
			
			URL fxmlURL = getClass().getResource("fenetres/fenetre_ajout_abonnement.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 674.0, 600.0);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			stage1.setScene(scene);
			stage1.setTitle("Gestion des Abonnements");
			stage1.show();
		}
	}
}
