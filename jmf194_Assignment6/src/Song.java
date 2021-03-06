/**
 * this class deals with the song table in the music2019 database
 * it includes constructors, getters, setters, and methods to add and delete records with their relevant queries
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 5
 * 2/12/2019
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.*;

public class Song {
	private String songID;
	private String title;
	private int length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	private Map<String, Artist> songArtists = new HashMap<String, Artist>();
	//private DbUtilities db;
	private Connection con;
	private PreparedStatement stmt;
	
	/**
	 * Constructor that creates a new Song instance in the database with the following parameters and a UUID
	 * Based on Dr. Babichenko's example code for Genre class (same goes for all constructors and query-generating methods)
	 * @param title
	 * @param length
	 * @param releaseDate
	 * @param recordDate
	 */
	public Song(String title, int length, String releaseDate, String recordDate, Connection con) {
		//db = new DbUtilities();
		//generates a random UUID and casts it to a String
		UUID uSongID = UUID.randomUUID();
		songID = uSongID.toString();
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.con = con;
		String sql = "INSERT INTO song (song_id, title, length, release_date, record_date) VALUES (?, ?, ?, ?, ?);";
		//prepareStatement blocks in entire program adapted from slides from class 
		//Source: https://docs.google.com/presentation/d/1IU8opmPGbHTvZlOEHJF4b8l_ka_VsPkr2MJwNPkY_Z4/edit#slide=id.g4e21c556a9_0_13
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, songID);
			stmt.setString(2, title);
			stmt.setInt(3, length);
			stmt.setString(4, releaseDate);
			stmt.setString(5, recordDate);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Constructor that retrieves a Song from the database with the given songID
	 * @param songID
	 */
	public Song(String songID, Connection con) {
		this.songID = songID;
		this.con = con;
		//db = new DbUtilities();
		String sql = "SELECT title, length, release_date, record_date, file_path FROM song WHERE song_id = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, songID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) { //stores values from columns with the quoted names into their respective variables
				this.title = rs.getString("title");
				this.length = rs.getInt("length");
				this.releaseDate = rs.getString("release_date");
				this.recordDate = rs.getString("record_date");
				this.filePath = rs.getString("file_path");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Deletes a Song with the given songID from the database
	 * @param songID
	 */
	public void deleteSong(String songID) {
		//db = new DbUtilities();
		String sql = "DELETE FROM song WHERE song_id = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, songID);;
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * used in addSong and deleteSong methods from Album class
	 * @return song UUID so it can be accessed outside of class
	 */
	public String getSongID() {
		return songID;
	}
	/**
	 * 
	 * @return title of given song
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * runs a query to change the title of a given song entry
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
		String sql = "UPDATE song SET title = ? WHERE song_id = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return length of given song in minutes
	 */
	public int getLength() {
		return length;
	}
	/**
	 * runs query to update song table to edit the length of a given song
	 * @param length
	 */
	public void setLength(int length) {
		String sql = "UPDATE song SET length = ? WHERE song_id = ?;";
		this.length = length;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, length);
			stmt.setString(2, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * used in my tester code since it isn't set in constructor
	 * @return the location of the song file in its folder
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * runs a query to change a given song's file path
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		String sql = "UPDATE song SET file_path = ? WHERE song_id = ?;";
		this.filePath = filePath;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, filePath);
			stmt.setString(2, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return release date of given song
	 */
	public String getReleaseDate() {
		return releaseDate;
	}
	/**
	 * runs a query to change the release date of a given song
	 * @param releaseDate
	 */
	public void setReleaseDate(String releaseDate) {
		String sql = "UPDATE song SET release_date = ? WHERE song_id = ?;";
		this.releaseDate = releaseDate;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, releaseDate);
			stmt.setString(2, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return the date a song was recorded
	 */
	public String getRecordDate() {
		return recordDate;
	}
	/**
	 * runs a query to allow the user to change the recording date of a song
	 * @param recordDate
	 */
	public void setRecordDate(String recordDate) {
		String sql = "UPDATE song SET record_date = ? WHERE song_id = ?;";
		this.recordDate = recordDate;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, recordDate);
			stmt.setString(2, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return a HashMap object that places the key of an artist ID next to a reference to the Artist object that recorded the song
	 */
	public Map<String, Artist> getSongArtists() {
		return songArtists;
	}
	/**
	 * uses song ID to generate a new Song object and add it to the list
	 * @param songArtists
	 */
	public void setSongArtists(Map<String, Artist> songArtists) {
		//source: https://www.careerbless.com/samplecodes/java/beginners/collections_ds/inertandretrieveMap.php
		Set keys = songArtists.keySet();
        Iterator itr = keys.iterator();
        String key;
        while(itr.hasNext())
        {
        	key = (String) itr.next();
        	Artist a = new Artist(key, con);
            songArtists.put(key, a);
        }
	}
	/**
	 * runs a query to insert a row into the junction table between song and artist that connects them on their primary keys
	 * @param artist
	 */
	public void addArtist(Artist artist) {
		String sql = "INSERT INTO song_artist (fk_song_id, fk_artist_id) VALUES (?, ?);";
		//calls getArtistID() method from Artist class by passing it on the object that was used as an argument
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, this.songID);
			stmt.setString(2, artist.getArtistID());
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
			songArtists.put(artist.getArtistID(), artist);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * runs a query that deletes a connection between a song and an artist from their primary keys in a junction table
	 * doesn't delete artist from the database, just severs its connection to the song
	 * @param artistID, meaning all the user needs to know is the UUID of the artist in question
	 */
	public void deleteArtist(String artistID) {
		String sql = "DELETE FROM song_artist WHERE fk_artist_id = ? AND fk_song_id = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, artistID);
			stmt.setString(2, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * does same as above method, except the user passes in a whole Artist object instead of just its ID
	 * @param artist
	 */
	public void deleteArtist(Artist artist) {
		String sql = "DELETE FROM song_artist WHERE fk_artist_id = ? AND fk_song_id = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, artist.getArtistID());
			stmt.setString(2, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
