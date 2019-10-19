package connexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Connexion{
	
	private static Connexion connexion = null;
	private Connection maConnexion;
	
	private Connexion() {};
	
	public static Connexion getInstance(){
		if (connexion==null) {
			connexion = new Connexion();
		}
		return connexion;
	}	
	 
	public Connection creeConnexion(){
		Properties accesBdd1 = new Properties();
        try {
            InputStream source = getClass().getResourceAsStream("properties");
            accesBdd1.loadFromXML(source);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
		try{ 
			maConnexion = DriverManager.getConnection(accesBdd1.getProperty("url"), accesBdd1.getProperty("login"),
                    accesBdd1.getProperty("pass"));
		} 
		catch (SQLException sqle){
			System.out.println("Erreur connexion" + sqle.getMessage());
		}
		return maConnexion;
	}
}