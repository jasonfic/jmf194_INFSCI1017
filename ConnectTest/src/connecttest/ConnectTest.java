package connecttest;

import java.sql.*;
import java.net.URL;

public class ConnectTest {

	public static void main(String[] args) {
		String server = "sis-teach-01.sis.pitt.edu";
		String port = "3306";
		String username = "infsci1017_2019";
		String password = "infsci1017_2019!";
		String dbName = "music2019";
		
		// Step 1: Define connection string
		
		String mySqlConn = "jdbc:mysql://" + server + "/" + dbName + "?user=" + username + "&password=" + password;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(mySqlConn);
			
			String sql = "INSERT INTO genre (genre_name, description) VALUES ('jazz', 'very smooth music')";
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
