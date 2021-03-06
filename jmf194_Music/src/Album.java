/**
 * this class deals with the album table in the music2019 database
 * it includes constructors, getters, setters, and methods to add and delete records with their relevant queries
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 5
 * 2/12/2019
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Album {
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private int length;
	private Map<String, Song> albumSongs;
	private DbUtilities db;
	
	/**
	 * runs an INSERT query to create a new album record with the below parameters:
	 * @param title
	 * @param releaseDate
	 * @param recordingCompany
	 * @param numberOfTracks
	 * @param pmrcRating
	 * @param length
	 */
	public Album(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		db = new DbUtilities();
		//generates a random UUID and casts it to a String
		UUID uAlbumID = UUID.randomUUID();
		albumID = uAlbumID.toString();
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		
		String sql = "INSERT INTO album (album_id, title, release_date, recording_company_name, number_of_tracks, pmrc_rating, length) VALUES ('" + albumID + "', '" + title + "', '" + releaseDate + "', '" + recordingCompany + "', '" + numberOfTracks + "', '" + pmrcRating + "', '" + length + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * constructor that uses the primary key of an album record to pull it from the database and store its relevant columns in local variables
	 * @param albumID
	 */
	public Album(String albumID) {
		this.albumID = albumID;
		db = new DbUtilities();
		String sql = "SELECT title, release_date, recording_company_name, number_of_tracks, pmrc_rating, length FROM album WHERE album_id = '" + this.albumID + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			if (rs.next()) { //stores values from columns with the quoted names into their respective variables
				this.title = rs.getString("title");
				this.releaseDate = rs.getString("release_date");
				this.recordingCompany = rs.getString("recording_company_name"); //fixed error where I assumed the column name was "recording_company"
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.pmrcRating = rs.getString("PMRC_rating");
				this.length = rs.getInt("length");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * deletes a row from the album table that has the given primary key
	 * @param albumID
	 */
	public void deleteAlbum(String albumID) {
		db = new DbUtilities();
		String sql = "DELETE FROM album WHERE album_id = '" + albumID + "';";
		//fixed same problem from Song and Artist tables of using this.albumID
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * 
	 * @return albumID of a given Album record
	 */
	public String getAlbumID() {
		return albumID;
	}
	/**
	 * 
	 * @return title of the album
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * runs an UPDATE query that changes the title of a given album
	 * @param title
	 */
	public void setTitle(String title) {
		String sql = "UPDATE album SET title = '" + title + "' WHERE album_id = '" + this.albumID + "';";
		this.title = title;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * 
	 * @return the date of release for a given album
	 */
	public String getReleaseDate() {
		return releaseDate;
	}
	/**
	 * runs an UPDATE query that changes the release date of a given album
	 * @param releaseDate
	 */
	public void setReleaseDate(String releaseDate) {
		String sql = "UPDATE album SET release_date = '" + releaseDate + "' WHERE album_id = '" + this.albumID + "';";
		this.releaseDate = releaseDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * 
	 * @return the location of the album cover image file
	 */
	public String getCoverImagePath() {
		return coverImagePath;
	}
	/**
	 * runs an UPDATE query that changes the file path for the album cover art
	 * @param coverImagePath
	 */
	public void setCoverImagePath(String coverImagePath) {
		String sql = "UPDATE album SET cover_image_path = '" + coverImagePath + "' WHERE album_id = '" + this.albumID + "';";
		this.coverImagePath = coverImagePath;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * 
	 * @return the name of the record label that the given album was released under
	 */
	public String getRecordingCompany() {
		return recordingCompany;
	}
	/**
	 * runs an UPDATE query that changes the name of the record label
	 * @param recordingCompany
	 */
	public void setRecordingCompany(String recordingCompany) {
		String sql = "UPDATE album SET recording_company_name = '" + recordingCompany + "' WHERE album_id = '" + this.albumID + "';";
		this.recordingCompany = recordingCompany;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * 
	 * @return the integer value for number of tracks on the given album
	 */
	public int getNumberOfTracks() {
		return numberOfTracks;
	}
	/**
	 * runs an UPDATE query that changes the number of songs on an album
	 * could be useful if a deluxe edition comes out
	 * @param numberOfTracks
	 */
	public void setNumberOfTracks(int numberOfTracks) {
		String sql = "UPDATE album SET number_of_tracks = " + numberOfTracks + " WHERE album_id = '" + this.albumID + "';";
		this.numberOfTracks = numberOfTracks;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * 
	 * @return the Parents Music Resource Center (PMRC) rating of a given album
	 */
	public String getPmrcRating() {
		return pmrcRating;
	}
	/**
	 * uses an UPDATE query to change the PMRC rating of an album so that your mom will let you listen to it
	 * @param pmrcRating
	 */
	public void setPmrcRating(String pmrcRating) {
		String sql = "UPDATE album SET PMRC_rating = '" + pmrcRating + "' WHERE album_id = '" + this.albumID + "';";
		this.pmrcRating = pmrcRating;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * 
	 * @return length of the album in minutes
	 */
	public int getLength() {
		return length;
	}
	/**
	 * runs an UPDATE query to change the length of an album, then updates its local variable value
	 * @param length
	 */
	public void setLength(int length) {
		String sql = "UPDATE album SET length = " + length + " WHERE album_id = '" + this.albumID + "';";
		this.length = length;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * 
	 * @return a HashMap object that places the album ID of an album next to a reference to a Song object that is on the album
	 */
	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}
	/**
	 * allows user to give an albumID key to connect with a Song object
	 * @param albumSongs
	 */
	public void setAlbumSongs(Map<String, Song> albumSongs) {
		this.albumSongs = albumSongs;
	}
	/**
	 * runs an INSERT query that forms a connection between a song record and an album record through their primary keys on a junction table
	 * @param song
	 */
	public void addSong(Song song) {
		String sql = "INSERT INTO album_song (fk_album_id, fk_song_id) VALUES ('" + this.albumID + "', '" + song.getSongID() + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * runs a query that deletes a connection between a song and an album from their primary keys in a junction table
	 * doesn't delete the song from the database, just severs its connection to the given album
	 * @param songID, meaning all the user needs to know is the UUID of the song in question
	 */
	public void deleteSong(String songID) {
		String sql = "DELETE FROM album_song WHERE fk_song_id = '" + songID + "' AND fk_album_id = '" + this.albumID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	/**
	 * does same as above method, except the user passes in a whole Song object instead of just its ID
	 * @param song
	 */
	public void deleteSong(Song song) {
		String sql = "DELETE FROM album_song WHERE fk_song_id = '" + song.getSongID() + "' AND fk_album_id = '" + this.albumID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
	}
}
