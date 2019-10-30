package main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.metier.Abonnement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CtrlAbo implements Initializable{

	@FXML
	private TableView<Abonnement> tblAbo;
	@FXML
	private Button retour;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
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

		List<Abonnement> abonnements = CtrlAccueil.daoabo.findAll();
		
		tblAbo.getItems().addAll(abonnements);
		return tblAbo;
	}
}
