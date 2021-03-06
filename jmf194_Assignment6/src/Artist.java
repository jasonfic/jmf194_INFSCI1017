/**
 * this class deals with the artist table in the music2019 database
 * it includes constructors, getters, setters, and methods to delete records with their relevant queries
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 5
 * 2/12/2019
 */

import java.sql.*;
import java.util.*;

public class Artist {
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;
	//private DbUtilities db;
	private Connection con;
	private PreparedStatement stmt;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	/**
	 * constructor that allows user to add a new row to the artist table from scratch using the INSERT query
	 * @param firstName
	 * @param lastName
	 * @param bandName
	 */
	public Artist(String firstName, String lastName, String bandName, Connection con) {
		//db = new DbUtilities();
		//generates a random UUID and casts it to a String
		UUID uArtistID = UUID.randomUUID();
		artistID = uArtistID.toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		this.con = con;
		String sql = "INSERT INTO artist (artist_id, first_name, last_name, band_name) VALUES (?, ?, ?, ?);";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, artistID);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, bandName);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * allows user to retrieve a record from the artist table by calling a SELECT query based on the primary key
	 * stores data from each relevant column in local variables
	 * @param artistID
	 */
	public Artist(String artistID, Connection con) {
		this.artistID = artistID;
		this.con = con;
		//db = new DbUtilities();
		String sql = "SELECT first_name, last_name, band_name, bio FROM artist WHERE artist_id = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, artistID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) { //stores values from columns with the quoted names into their respective variables
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
				this.bio = rs.getString("bio");
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
		//db = new DbUtilities();
		String sql = "DELETE FROM artist WHERE artist_id = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		String sql = "UPDATE artist SET first_name = ? WHERE artist_id = ?;";
		this.firstName = firstName;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, this.artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		String sql = "UPDATE artist SET last_name = ? WHERE artist_id = ?;";
		this.lastName = lastName;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, lastName);
			stmt.setString(2, this.artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		String sql = "UPDATE artist SET band_name = ? WHERE artist_id = ?;";
		this.bandName = bandName;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bandName);
			stmt.setString(2, this.artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		String sql = "UPDATE artist SET bio = ? WHERE artist_id = ?;";
		this.bio = bio;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bio);
			stmt.setString(2, this.artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
