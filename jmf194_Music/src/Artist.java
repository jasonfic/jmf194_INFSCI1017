import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Artist {
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;
	private DbUtilities db;
	
	public Artist(String firstName, String lastName, String bandName) {
		db = new DbUtilities();
		UUID uArtistID = UUID.randomUUID();
		artistID = uArtistID.toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		
		String sql = "INSERT INTO artist (first_name, last_name, band_name) VALUES ('" + firstName + "', '" + lastName + "', '" + bandName + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public Artist(String artistID) {
		this.artistID = artistID;
		db = new DbUtilities();
		String sql = "SELECT first_name, last_name, band_name FROM song WHERE artist_id = '" + this.artistID + "';";
		ResultSet rs = null;
		try {
			rs = db.getResultSet(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs.next()) {
				this.firstName = rs.getString(1);
				this.lastName = rs.getString(2);
				this.bandName = rs.getString(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteArtist(String artistID) {
		this.artistID = artistID;
		db = new DbUtilities();
		String sql = "DELETE FROM artist WHERE artist_id = '" + this.artistID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getArtistID() {
		return artistID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		String sql = "UPDATE artist SET first_name = '" + firstName + "' WHERE artist_id = '" + this.artistID + "';";
		this.firstName = firstName;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		String sql = "UPDATE artist SET last_name = '" + lastName + "' WHERE artist_id = '" + this.artistID + "';";
		this.lastName = lastName;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getBandName() {
		return bandName;
	}
	public void setBandName(String bandName) {
		String sql = "UPDATE artist SET band_name = '" + bandName + "' WHERE artist_id = '" + this.artistID + "';";
		this.bandName = bandName;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		String sql = "UPDATE artist SET bio = '" + bio + "' WHERE artist_id = '" + this.artistID + "';";
		this.bio = bio;
		System.out.println(sql);
		db.executeQuery(sql);
	}
}
