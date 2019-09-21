package TD_1;

import java.sql.*;
public class connexion
	{
		public Connection creeConnexion() 
			{
				String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/vidal27u_cpoa";
				url += "?serverTimezone=Europe/Paris";
				String login = "vidal27u_appli";
				String pwd = "31803976"; 
				Connection maConnexion = null;
				try 
				{ 
					maConnexion = DriverManager.getConnection(url, login, pwd);
				} 
				catch (SQLException sqle) 
				{
					System.out.println("Erreur connexion" + sqle.getMessage());
				}
				return maConnexion;
			}
	}