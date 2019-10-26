package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.listememoire.ListeMemoireAbonnementDAO;
import dao.listememoire.ListeMemoireClientDAO;
import dao.listememoire.ListeMemoirePeriodiciteDAO;
import dao.listememoire.ListeMemoireRevueDAO;
import dao.mysql.MySQLAbonnementDAO;
import dao.mysql.MySQLClientDAO;
import dao.mysql.MySQLPeriodiciteDAO;
import dao.mysql.MySQLRevueDAO;
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
		ListeMemoireRevueDAO daorevli = ListeMemoireRevueDAO.getInstance();
		ListeMemoireAbonnementDAO daoaboli = ListeMemoireAbonnementDAO.getInstance();
		ListeMemoireClientDAO daoclili = ListeMemoireClientDAO.getInstance();
		ListeMemoirePeriodiciteDAO daoperioli = ListeMemoirePeriodiciteDAO.getInstance();
		
		MySQLAbonnementDAO daoabosql = MySQLAbonnementDAO.getInstance();
		MySQLRevueDAO daorevsql = MySQLRevueDAO.getInstance();
		MySQLClientDAO daoclisql = 		MySQLClientDAO.getInstance();
		MySQLPeriodiciteDAO daoperiosql = 		MySQLPeriodiciteDAO.getInstance();
	
	}
	
	
	

		@FXML
		public void Revue() throws IOException {
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
			if (daos!= null ) {
			Stage stage = new Stage();
			
			URL fxmlURL = getClass().getResource("fenetre_ajout_revue.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 1000, 812);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			stage.setScene(scene);
			stage.setTitle("Gestion des revues");
			stage.show();
			}
			
			
	}      
	
}
