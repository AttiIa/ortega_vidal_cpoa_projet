package main;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class mainInterface extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {

			URL fxmlURL = getClass().getResource("fenetre.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 498.0, 112.0);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Accueil");
			primaryStage.show();

	}      
	
	public static void main(String[] args) {
		launch(args);
	}
}      
      