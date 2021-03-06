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

public class Song {
	private String songID;
	private String title;
	private int length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	private Map<String, Artist> songArtists;
	private DbUtilities db;
	
	/**
	 * Constructor that creates a new Song instance in the database with the following parameters and a UUID
	 * Based on Dr. Babichenko's example code for Genre class (same goes for all constructors and query-generating methods)
	 * @param title
	 * @param length
	 * @param releaseDate
	 * @param recordDate
	 */
	public Song(String title, int length, String releaseDate, String recordDate) {
		db = new DbUtilities();
		//generates a random UUID and casts it to a String
		UUID uSongID = UUID.randomUUID();
		songID = uSongID.toString();
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		
		String sql = "INSERT INTO song (song_id, title, length, release_date, record_date) VALUES ('" + songID + "', '" + title + "', '" + length + "', '" + releaseDate + "', '" + recordDate + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * Constructor that retrieves a Song from the database with the given songID
	 * @param songID
	 */
	public Song(String songID) {
		this.songID = songID;
		db = new DbUtilities();
		String sql = "SELECT title, length, release_date, record_date FROM song WHERE song_id = '" + this.songID + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			if (rs.next()) { //stores values from columns with the quoted names into their respective variables
				this.title = rs.getString("title");
				this.length = rs.getInt("length");
				this.releaseDate = rs.getString("release_date");
				this.recordDate = rs.getString("record_date");
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
		db = new DbUtilities();
		String sql = "DELETE FROM song WHERE song_id = '" + songID + "';";
		//rectified earlier mistake by changing "this.songID" above to simply "songID."
		//"this.songID" deleted the song object the method was called on instead of
		//the song with the songID passed as an argument
		System.out.println(sql);
		db.executeQuery(sql);
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
		String sql = "UPDATE song SET title = '" + title + "' WHERE song_id = '" + this.songID + "';";
		this.title = title;
		System.out.println(sql);
		db.executeQuery(sql);
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
		String sql = "UPDATE song SET length = " + length + " WHERE song_id = '" + this.songID + "';";
		this.length = length;
		System.out.println(sql);
		db.executeQuery(sql);
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
		String sql = "UPDATE song SET file_path = '" + filePath + "' WHERE song_id = '" + this.songID + "';";
		this.filePath = filePath;
		System.out.println(sql);
		db.executeQuery(sql);
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
		String sql = "UPDATE song SET release_date = '" + releaseDate + "' WHERE song_id = '" + this.songID + "';";
		this.releaseDate = releaseDate;
		System.out.println(sql);
		db.executeQuery(sql);
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
		String sql = "UPDATE song SET record_date = '" + recordDate + "' WHERE song_id = '" + this.songID + "';";
		this.recordDate = recordDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * 
	 * @return a HashMap object that places the song ID of a song next to a reference to the Artist object that recorded the song
	 */
	public Map<String, Artist> getSongArtists() {
		return songArtists;
	}
	/**
	 * allows user to give a songID key to connect with an Artist object
	 * @param songArtists
	 */
	public void setSongArtists(Map<String, Artist> songArtists) {
		this.songArtists = songArtists;
	}
	/**
	 * runs a query to insert a row into the junction table between song and artist that connects them on their primary keys
	 * @param artist
	 */
	public void addArtist(Artist artist) {
		String sql = "INSERT INTO song_artist (fk_song_id, fk_artist_id) VALUES ('" + this.songID + "', '" + artist.getArtistID() + "');";
		//calls getArtistID() method from Artist class by passing it on the object that was used as an argument
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * runs a query that deletes a connection between a song and an artist from their primary keys in a junction table
	 * doesn't delete artist from the database, just severs its connection to the song
	 * @param artistID, meaning all the user needs to know is the UUID of the artist in question
	 */
	public void deleteArtist(String artistID) {
		String sql = "DELETE FROM song_artist WHERE fk_artist_id = '" + artistID + "' AND fk_song_id = '" + this.songID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * does same as above method, except the user passes in a whole Artist object instead of just its ID
	 * @param artist
	 */
	public void deleteArtist(Artist artist) {
		String sql = "DELETE FROM song_artist WHERE fk_artist_id = '" + artist.getArtistID() + "' AND fk_song_id = '" + this.songID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
	}
}
