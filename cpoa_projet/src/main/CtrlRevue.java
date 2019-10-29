package main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.metier.Periodicite;
import dao.metier.Revue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;


public class CtrlRevue implements Initializable {
	
	private boolean b_create=false;
	private boolean b_update=false;
	private boolean b_delete=false;

	@FXML
	private TextField titre;
	@FXML
	private TextField tarif;
	@FXML
	private TextField visuel;
	@FXML
	private ComboBox<Periodicite> periode;
	@FXML
	private TextArea description;		
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
	private TableView<Revue> tblRevue;
	@FXML
	private Window vue;
	@FXML
	private Window accueil;

	
	public TableView<Revue> tblRevue() {

		TableColumn<Revue, String> colIdRevue = new TableColumn<>("id_revue");
		TableColumn<Revue, String> colTitre = new TableColumn<>("Titre");
		TableColumn<Revue, String> colDescription = new TableColumn<>("Description");
		TableColumn<Revue, String> colTarif = new TableColumn<>("Tarif");
		TableColumn<Revue, String> colPeriodicite = new TableColumn<>("Periodicite");
		TableColumn<Revue, String> colVisuel = new TableColumn<>("Visuel");

		colIdRevue.setCellValueFactory(new PropertyValueFactory<Revue, String>("id_revue"));
		colTitre.setCellValueFactory(new PropertyValueFactory<Revue, String>("titre"));
		colDescription.setCellValueFactory(new PropertyValueFactory<Revue, String>("description"));
		colTarif.setCellValueFactory(new PropertyValueFactory<Revue, String>("tarif_numero"));
		colPeriodicite.setCellValueFactory(new PropertyValueFactory<Revue, String>("id_periodicite"));
		colVisuel.setCellValueFactory(new PropertyValueFactory<Revue, String>("visuel"));

		tblRevue.getColumns().setAll(colIdRevue, colTitre, colDescription, colTarif, colPeriodicite, colVisuel);

		List<Revue> revues = CtrlAccueil.daorev.findAll();
		
		tblRevue.getItems().addAll(revues);
		return tblRevue;

	}

	@Override
	public String toString() {
		if(b_create) return "Ajout de : " + titre.getText() + " (" + tarif.getText() + "€)";
		else if(b_delete) return "Suppression de : " + tblRevue.getSelectionModel().getSelectedItem().getTitre();
		else if(b_update) return "Modifiction de : " + titre.getText();
		else return "";
	}

	public void initialize(URL location, ResourceBundle resources) {
 	
		try {
			periode.setItems(FXCollections.observableArrayList(CtrlAccueil.daoper.findAll()));
			tblRevue();
		} 
		catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Probleme a l'initialisation");
			alert.setHeaderText("Un probleme est survenue lors de l'initialisation de vos Revues");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}
	}

	@FXML
	public void valider() {

		Periodicite period = periode.getValue();

		if ((titre.getText().isEmpty()) || (description.getText().isEmpty()) || (tarif.getText().isEmpty())
				|| (visuel.getText().isEmpty()) || (period == null)) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Erreur lors de la saisie");
			alert.setHeaderText("Un ou plusieurs champs sont mal remplis.");
			alert.showAndWait();
		}

		else if(b_create){
			try {
				double txt_tarif = Double.parseDouble(tarif.getText());
				String txt_titre = titre.getText();
				String txt_description = description.getText();
				String txt_visuel = visuel.getText();
				affichage.setText(toString());

				CtrlAccueil.daorev.create(
						new Revue(txt_titre, txt_description, txt_tarif, txt_visuel, period.getId_periodicite()));
			} catch (Exception e) {
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.initOwner(vue);
				alert.setTitle("La creation a echouee");
				alert.setHeaderText("Un probleme est survenue lors de la creation de votre Revue");
				alert.setContentText(e.toString());
				alert.showAndWait();
			}
		}
		else if(b_update) {
			try {
				double txt_tarif = Double.parseDouble(tarif.getText());
				String txt_titre = titre.getText();
				String txt_description = description.getText();
				String txt_visuel = visuel.getText();
				affichage.setText(toString());

				CtrlAccueil.daorev.update(
						new Revue(tblRevue.getSelectionModel().getSelectedItem().getId_revue(), txt_titre, txt_description, txt_tarif, txt_visuel, period.getId_periodicite()));				
			}
			catch (Exception e) {
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.initOwner(vue);
				alert.setTitle("La modification a echouee");
				alert.setHeaderText("Un probleme est survenue lors de la modification de votre Revue");
				alert.setContentText(e.toString());
				alert.showAndWait();
			}
			
		}
		b_create=false;
		b_delete=false;
		b_update=false;
		
		List<Revue> revues = CtrlAccueil.daorev.findAll();
		tblRevue.getItems().clear();
		tblRevue.getItems().addAll(revues); 
		
		form.setDisable(true);
		valider.setDisable(true);
	}
	
	@FXML
	public void create() {
		form.setDisable(false);
		valider.setDisable(false);
		
		titre.setText("");
		description.setText("");
		tarif.setText("");
		periode.setValue(null);
		visuel.setText("");
		
		b_create=true;
		b_delete=false;
		b_update=false;
	}	
	
	@FXML
	public void delete() {
		try {
			b_delete=true;
			CtrlAccueil.daorev.delete(tblRevue.getSelectionModel().getSelectedItem()); 
			affichage.setText(toString());;
	        List<Revue> revues = CtrlAccueil.daorev.findAll();
			tblRevue.getItems().clear();
			tblRevue.getItems().addAll(revues);
		} 
		catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Un probleme est survenue lors de la suppression de votre Revue");
			alert.setHeaderText("Aucune Revue selectionnee");
			alert.setContentText(e.toString());
			alert.showAndWait();
		}		
	}
	
	@FXML
	public void update() {
		try {			
			Revue revue=tblRevue.getSelectionModel().getSelectedItem();
						
			titre.setText(revue.getTitre());
			description.setText(revue.getDescription());
			tarif.setText(String.valueOf(revue.getTarif_numero()));
			periode.setValue(CtrlAccueil.daoper.getById(revue.getId_periodicite()));
			visuel.setText(revue.getVisuel());
			
			form.setDisable(false);
			valider.setDisable(false);
			
			b_create=false;
			b_delete=false;
			b_update=true;
		}
		catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(vue);
			alert.setTitle("Un probleme est survenue lors de la modification de votre Revue");
			alert.setHeaderText("Aucune Revue selectionnee");
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
