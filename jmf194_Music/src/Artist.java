/**
 * this class deals with the artist table in the music2019 database
 * it includes constructors, getters, setters, and methods to delete records with their relevant queries
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 5
 * 2/12/2019
 */

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
	
	/**
	 * constructor that allows user to add a new row to the artist table from scratch using the INSERT query
	 * @param firstName
	 * @param lastName
	 * @param bandName
	 */
	public Artist(String firstName, String lastName, String bandName) {
		db = new DbUtilities();
		//generates a random UUID and casts it to a String
		UUID uArtistID = UUID.randomUUID();
		artistID = uArtistID.toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		
		String sql = "INSERT INTO artist (artist_id, first_name, last_name, band_name) VALUES ('" + artistID + "', '" + firstName + "', '" + lastName + "', '" + bandName + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * allows user to retrieve a record from the artist table by calling a SELECT query based on the primary key
	 * stores data from each relevant column in local variables
	 * @param artistID
	 */
	public Artist(String artistID) {
		this.artistID = artistID;
		db = new DbUtilities();
		String sql = "SELECT first_name, last_name, band_name FROM artist WHERE artist_id = '" + this.artistID + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			if (rs.next()) { //stores values from columns with the quoted names into their respective variables
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * deletes an artist record of the given ID from the database through a DELETE FROM query
	 * @param artistID
	 */
	public void deleteArtist(String artistID) {
		db = new DbUtilities();
		String sql = "DELETE FROM artist WHERE artist_id = '" + artistID + "';";
		//solved earlier issue of query deleting wrong artist record by changing "this.artistID" to simply "artistID" above
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * used in addArtist(Artist artist) method from Song class
	 * @return artist UUID so it can be accessed outside of class
	 */
	public String getArtistID() {
		return artistID;
	}
	/**
	 * 
	 * @return first name of given artist
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * runs an UPDATE query to change the first name value of a given artist
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		String sql = "UPDATE artist SET first_name = '" + firstName + "' WHERE artist_id = '" + this.artistID + "';";
		this.firstName = firstName;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * 
	 * @return last name of given artist
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * runs an UPDATE query to change the last name value of a given artist
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		String sql = "UPDATE artist SET last_name = '" + lastName + "' WHERE artist_id = '" + this.artistID + "';";
		this.lastName = lastName;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * 
	 * @return name of the band that the given artist is part of (or in some cases, their stage name)
	 */
	public String getBandName() {
		return bandName;
	}
	/**
	 * runs an UPDATE query to change the band name of a given artist
	 * @param bandName
	 */
	public void setBandName(String bandName) {
		String sql = "UPDATE artist SET band_name = '" + bandName + "' WHERE artist_id = '" + this.artistID + "';";
		this.bandName = bandName;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * 
	 * @return biography entry describing a given artist
	 */
	public String getBio() {
		return bio;
	}
	/**
	 * runs an UPDATE query to change the biography of a given artist
	 * used in my tester code since bio isn't included in constructor
	 * @param bio
	 */
	public void setBio(String bio) {
		String sql = "UPDATE artist SET bio = '" + bio + "' WHERE artist_id = '" + this.artistID + "';";
		this.bio = bio;
		System.out.println(sql);
		db.executeQuery(sql);
	}
}
