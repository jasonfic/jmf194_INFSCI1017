package music_jpa;
import java.sql.*;
import java.net.URL;

public class ConnectTest {

	public static void main(String[] args) {
		String server = "sis-teach-01.sis.pitt.edu:3306";
		String userName = "infsci1017_2019";
		String password = "infsci1017_2019!";
		String dbName = "music2019";
		
		// Step 1: Define connection string
		
		String mySqlConn = "jdbc:mysql://" + server + "/" + dbName + "?user=" + userName + "&password=" + password;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(mySqlConn);
			
			String sql = "INSERT INTO genre (genre_name, description) VALUES (?, ?);";
			// Statement statement = conn.createStatement();
            // statement.executeUpdate(sql);
			// String searchTerm = "heavy metal";
			// String sql = "SELECT genre_id, genre_name, description FROM genre WHERE genre_name = ?";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Blues");
			ps.setString(2, "Some description; DROP TABLE genre");
			/*
			Statement statement = conn.createStatement();
			*/
			ps.execute(); // Return ResultSet
            /*
			while(rs.next()){
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
			}
			*/
			
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
