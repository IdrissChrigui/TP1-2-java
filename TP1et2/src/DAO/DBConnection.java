package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBConnection {
	
	
	 private static final String URL = "jdbc:mysql://localhost:3306/TPJAVA12";
	    private static final String UTILISATEUR = "root";
	    private static final String MOT_DE_PASSE = "";
	    private static Connection connection = null;

	    public static Connection getConnection() {
	        if (connection == null) {
	            try {
	                connection = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
	            } catch (SQLException e) {
	                e.printStackTrace();
	              
	            }
	        }
	        return connection;
	    }
}
