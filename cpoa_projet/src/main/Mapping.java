package main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.factory.DAOFactory;
import dao.factory.Persistance;
import dao.metier.Periodicite;
import dao.metier.Revue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
<<<<<<< HEAD
=======
import javafx.stage.Window;
import javafx.scene.control.*;
>>>>>>> branch 'master' of https://github.com/AttiIa/ortega_vidal_cpoa_projet.git

public class Mapping implements Initializable {

	@FXML
	private TextField titre;
	@FXML
	private TextField tarif;
	@FXML
	private ComboBox<Periodicite> libelle;
	@FXML
	private TextArea description;
	@FXML
	private TextField visuel;
	@FXML
	private Button creer;
	@FXML
	private Label affichage;
	@FXML
	private RadioButton mysql;
	@FXML
	private RadioButton list;
	@FXML
	private Button modifier;
	@FXML
	private Button supprimer;
	@FXML
	private Button retour;
	@FXML
	private TableView<Revue> tblRevue;
	
	private Window vue;

	public void tblRevue() {
		this.tblRevue = new TableView<>();

		TableColumn<Revue, String> colIdRevue = new TableColumn<>("id_revue");
		TableColumn<Revue, String> colTitre = new TableColumn<>("colTitre");
		TableColumn<Revue, String> colDescription = new TableColumn<>("colDescription");
		TableColumn<Revue, String> colTarif = new TableColumn<>("colTarif");
		TableColumn<Revue, String> colPeriodicite = new TableColumn<>("colPeriodicite");
		TableColumn<Revue, String> colVisuel = new TableColumn<>("colVisuel");

		colIdRevue.setCellValueFactory(new PropertyValueFactory<Revue, String>("id_revue"));
		colTitre.setCellValueFactory(new PropertyValueFactory<Revue, String>("titre"));
		colDescription.setCellValueFactory(new PropertyValueFactory<Revue, String>("description"));
		colTarif.setCellValueFactory(new PropertyValueFactory<Revue, String>("tarif_numero"));
		colPeriodicite.setCellValueFactory(new PropertyValueFactory<Revue, String>("id_periodicite"));
		colVisuel.setCellValueFactory(new PropertyValueFactory<Revue, String>("visuel"));

		this.tblRevue.getColumns().setAll(colIdRevue, colTitre, colDescription, colTarif, colPeriodicite, colVisuel);

		List<Revue> revues = DAOFactory.getDAOFactory(Persistance.MySQL).getRevueDAO().findAll();

		this.tblRevue.getItems().addAll(revues);
		System.out.println(this.tblRevue.getItems().size());
		
		this.tblRevue.refresh();

	}

	@Override
	public String toString() {
		return "Ajout de : " + titre.getText() + " (" + tarif.getText() + ")";
	}

	public void initialize(URL location, ResourceBundle resources) {

<<<<<<< HEAD
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
=======
	        try {
				this.libelle.setItems(FXCollections.observableArrayList(dao.getPeriodiciteDAO().findAll()));
			} catch (Exception e) {
				System.out.println("Probleme a l'initialisation ");
				e.printStackTrace();
			}
	    }   
>>>>>>> branch 'master' of https://github.com/AttiIa/ortega_vidal_cpoa_projet.git
	
		try {
			this.libelle.setItems(FXCollections.observableArrayList(dao.getPeriodiciteDAO().findAll()));
			tblRevue();
		} catch (Exception e) {
			System.out.println("Probl�me � l'initialisation ");
			e.printStackTrace();
		}
	}

	@FXML
	public void create() {

		Periodicite period = libelle.getValue();
<<<<<<< HEAD

		if ((titre.getText().isEmpty()) || (description.getText().isEmpty()) || (tarif.getText().isEmpty())
				|| (visuel.getText().isEmpty()) || (period == null) || ((mysql == null) && (list == null))) {
			affichage.setTextFill(Color.web("red"));
			this.affichage.setText("Les champs ne sont pas tous valides");
=======
		
		if ((titre.getText().isEmpty()) || (description.getText().isEmpty()) || (tarif.getText().isEmpty()) 
				|| (visuel.getText().isEmpty()) || (period == null) || ((mysql==null) && (list==null))){
		
			//String erreur="";
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.vue);
			alert.setTitle("Erreur lors de la saisie");
			alert.setHeaderText("Un ou plusieurs champs sont mal remplis.");
			//alert.setContentText(erreur);
			alert.showAndWait();

>>>>>>> branch 'master' of https://github.com/AttiIa/ortega_vidal_cpoa_projet.git
		}

		else {
			DAOFactory daos = null;
			if (mysql.isSelected()) {
				daos = DAOFactory.getDAOFactory(Persistance.MySQL);
			} else if (list.isSelected()) {
				daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
			}
			try {
				double txt_tarif = Double.parseDouble(tarif.getText());
				affichage.setTextFill(Color.web("black"));
				affichage.setText(toString());
				String txt_titre = titre.getText();
				String txt_description = description.getText();
				String txt_visuel = visuel.getText();

				daos.getRevueDAO().create(
						new Revue(txt_titre, txt_description, txt_tarif, txt_visuel, period.getId_periodicite()));
			} catch (Exception e) {
				affichage.setTextFill(Color.web("red"));
				this.affichage.setText("Erreur lors de la creation");
			}
		}
	}

	@FXML
	public void update() {
<<<<<<< HEAD
	}

=======
		
	}
>>>>>>> branch 'master' of https://github.com/AttiIa/ortega_vidal_cpoa_projet.git
	@FXML
	public void delete() {
<<<<<<< HEAD
	}

=======
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
		try {
			
	        daos.getRevueDAO().delete(tblRevue.getSelectionModel().getSelectedItem());  
		} 
		catch (Exception e) {
			affichage.setTextFill(Color.web("red"));
			this.affichage.setText("Erreur lors de la suppression");
		} 
		
	}
>>>>>>> branch 'master' of https://github.com/AttiIa/ortega_vidal_cpoa_projet.git
	@FXML
	public void back() {
<<<<<<< HEAD
=======
		
>>>>>>> branch 'master' of https://github.com/AttiIa/ortega_vidal_cpoa_projet.git
	}

}
