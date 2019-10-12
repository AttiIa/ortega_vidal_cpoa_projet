package main;

import javax.print.DocFlavor.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public class mainInterface {


	public void start(Stage primaryStage) {
	try {
	java.net.URL fxmlURL=getClass().getResource("javafx/interface_graphique_revue.fxml");
	FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
	Node root = fxmlLoader.load();
	Scene scene = new Scene((VBox) root, 640, 400);
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.setTitle("Ma première fenêtre JavaFX");
	primaryStage.show();
	} catch (Exception e) {
	e.printStackTrace();
	}
	}    
	public static void main(String[] args) {
		launch(args);
		}
}
