package main;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import dao.metier.Abonnement;
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
	private Button recherche;
	@FXML
	private Button retour;
	@FXML
	private CheckBox en_cours;
	@FXML
	private Window vue;
	@FXML
	private ComboBox<Revue> recherche_revue;
	
	@SuppressWarnings("unchecked")
	public TableView<Abonnement> tblAbo() throws Exception {

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
		if(abonnements!=null)
			tblAbo.getItems().addAll(abonnements);
		return tblAbo;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			recherche_revue.setItems(FXCollections.observableArrayList(CtrlAccueil.daorev.findAll()));
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
	public void en_cours() throws Exception{
		try {
			List<Abonnement> abo = new ArrayList<Abonnement>();
			
			if(en_cours.isSelected()) {
				int i=0;
				Date date = new Date();
								
				while(i<tblAbo.getItems().size()) {
					Date date_d = new SimpleDateFormat("yyyy-MM-dd").parse(tblAbo.getItems().get(i).getDate_debut());
					Date date_f = new SimpleDateFormat("yyyy-MM-dd").parse(tblAbo.getItems().get(i).getDate_fin());
					if(date_d.compareTo(date)<=0 && date_f.compareTo(date)>=0) {
						abo.add(tblAbo.getItems().get(i));
					}
					i++;
				}
				tblAbo.getItems().clear();
				tblAbo.getItems().addAll(abo);
			}
			else {
				List<Abonnement> abonnements = CtrlAccueil.daoabo.getById_client(CtrlClient.id_cli);

				tblAbo.getItems().clear();
				tblAbo.getItems().addAll(abonnements);
			}
		}
		catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Probleme au triage");
			alert.setHeaderText("Un probleme est survenue lors du tri de vos date");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	}
	
	@FXML
	public void recherche() throws Exception {
		tblAbo.getItems().clear();
		List<Abonnement> abonnement = CtrlAccueil.daoabo.getById_client(CtrlClient.id_cli);
		tblAbo.getItems().addAll(abonnement);
		
		if(recherche_revue.getValue()!=null){
			List<Abonnement> abo = new ArrayList<Abonnement>();
			
			int i=0;
			int rev = recherche_revue.getValue().getId_revue();
			
			while(i<tblAbo.getItems().size()) {
				int rev1 = tblAbo.getItems().get(i).getId_revue();
				
				if(rev1 == rev) {
					abo.add(tblAbo.getItems().get(i));
				}
				i++;
			}
			tblAbo.getItems().clear();
			tblAbo.getItems().addAll(abo);
			recherche_revue.setValue(null);
		}
	}
	
	@FXML
	public void retour() throws IOException{
		CtrlClient.id_cli=0;
		
		Stage stage =(Stage) retour.getScene().getWindow();
		stage.close();
		Stage stage1 = new Stage();
		
		URL fxmlURL = getClass().getResource("fenetres/fenetre_ajout_client.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
		Node root = fxmlLoader.load();
		Scene scene = new Scene((VBox) root, 758.0, 636.0);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		stage1.setScene(scene);
		stage1.setTitle("Gestion des Clients");
		stage1.show();
	}
}
