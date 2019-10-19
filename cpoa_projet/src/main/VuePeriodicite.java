package main;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VuePeriodicite extends Stage{
	
	@FXML
	private TextField titre;	

	
	public void VueProduits() throws IOException {
		final URL fxmlURL = getClass().getResource("fenetre_ajout_periodicite.fxml");
		final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
		final VBox node = (VBox)fxmlLoader.load();
		Scene scene = new Scene(node);
	}
}
