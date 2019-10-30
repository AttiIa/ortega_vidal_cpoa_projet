package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.metier.Abonnement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CtrlAbo implements Initializable{

	@FXML
	private TableView<Abonnement> tblAbo;
	@FXML
	private Button retour;
	@FXML
	private Window vue;
	
	public TableView<Abonnement> tblAbo() {

		TableColumn<Abonnement, String> colIdClient = new TableColumn<>("id_client");
		TableColumn<Abonnement, String> colIdRevue = new TableColumn<>("id_revue");
		TableColumn<Abonnement, String> colDate_Deb = new TableColumn<>("date_debut");
		TableColumn<Abonnement, String> colDate_Fin = new TableColumn<>("date_fin");
		
		colIdClient.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("id_client"));
		colIdRevue.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("id_revue"));
		colDate_Deb.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("date_debut"));
		colDate_Fin.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("date_fin"));		

		tblAbo.getColumns().setAll(colIdClient, colIdRevue, colDate_Deb, colDate_Fin);

		List<Abonnement> abonnements = CtrlAccueil.daoabo.getById_client(CtrlClient.id_cli);
		
		tblAbo.getItems().addAll(abonnements);
		return tblAbo;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			tblAbo();
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
	public void retour() throws IOException{
		Stage stage =(Stage) retour.getScene().getWindow();
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
