/**
 * this class deals with the album table in the music2019 database
 * it includes constructors, getters, setters, and methods to add and delete records with their relevant queries
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 5
 * 2/12/2019
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	private Map<String, Song> albumSongs = new HashMap<String, Song>();
	//private DbUtilities db;
	private Connection con;
	private PreparedStatement stmt;
	
	/**
	 * runs an INSERT query to create a new album record with the below parameters:
	 * @param title
	 * @param releaseDate
	 * @param recordingCompany
	 * @param numberOfTracks
	 * @param pmrcRating
	 * @param length
	 */
	public Album(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, int length, Connection con) {
		//db = new DbUtilities();
		//generates a random UUID and casts it to a String
		UUID uAlbumID = UUID.randomUUID();
		albumID = uAlbumID.toString();
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		this.con = con;
		String sql = "INSERT INTO album (album_id, title, release_date, recording_company_name, number_of_tracks, PMRC_rating, length) VALUES (?, ?, ?, ?, ?, ?, ?);";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, albumID);
			stmt.setString(2, title);
			stmt.setString(3, releaseDate);
			stmt.setString(4, recordingCompany);
			stmt.setInt(5, numberOfTracks);
			stmt.setString(6, pmrcRating);
			stmt.setInt(7, length);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * constructor that uses the primary key of an album record to pull it from the database and store its relevant columns in local variables
	 * @param albumID
	 */
	public Album(String albumID, Connection con) {
		this.albumID = albumID;
		this.con = con;
		//db = new DbUtilities();
		String sql = "SELECT title, release_date, recording_company_name, number_of_tracks, pmrc_rating, length, cover_image_path FROM album WHERE album_id = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, albumID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) { //stores values from columns with the quoted names into their respective variables
				this.title = rs.getString("title");
				this.releaseDate = rs.getString("release_date");
				this.recordingCompany = rs.getString("recording_company_name"); //fixed error where I assumed the column name was "recording_company"
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.pmrcRating = rs.getString("PMRC_rating");
				this.length = rs.getInt("length");
				this.coverImagePath = rs.getString("cover_image_path");
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
		//db = new DbUtilities();
		String sql = "DELETE FROM album WHERE album_id = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		String sql = "UPDATE album SET title = ? WHERE album_id = ?;";
		this.title = title;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		String sql = "UPDATE album SET release_date = ? WHERE album_id = ?;";
		this.releaseDate = releaseDate;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, releaseDate);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		String sql = "UPDATE album SET cover_image_path = ? WHERE album_id = ?;";
		this.coverImagePath = coverImagePath;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, coverImagePath);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		String sql = "UPDATE album SET recording_company_name = ? WHERE album_id = ?;";
		this.recordingCompany = recordingCompany;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, recordingCompany);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		String sql = "UPDATE album SET number_of_tracks = ? WHERE album_id = ?;";
		this.numberOfTracks = numberOfTracks;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, numberOfTracks);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		String sql = "UPDATE album SET PMRC_rating = ? WHERE album_id = ?;";
		this.pmrcRating = pmrcRating;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pmrcRating);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		String sql = "UPDATE album SET length = ? WHERE album_id = ?;";
		this.length = length;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, length);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return a HashMap object that places the key of a song ID next to a reference to a Song object that is on the album
	 */
	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}
	/**
	 * uses song ID to generate a new Artist object and add it to the list
	 * @param albumSongs
	 */
	public void setAlbumSongs(Map<String, Song> albumSongs) {
		//source: https://www.careerbless.com/samplecodes/java/beginners/collections_ds/inertandretrieveMap.php
		Set keys = albumSongs.keySet();
        Iterator itr = keys.iterator();
        String key;
        while(itr.hasNext())
        {
            key = (String) itr.next();
            Song s = new Song(key, con);
            albumSongs.put(key, s);
        }
	}
	/**
	 * runs an INSERT query that forms a connection between a song record and an album record through their primary keys on a junction table
	 * @param song
	 */
	public void addSong(Song song) {
		String sql = "INSERT INTO album_song (fk_album_id, fk_song_id) VALUES (?, ?);";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, this.albumID);
			stmt.setString(2, song.getSongID());
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
			albumSongs.put(song.getSongID(), song);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * runs a query that deletes a connection between a song and an album from their primary keys in a junction table
	 * doesn't delete the song from the database, just severs its connection to the given album
	 * @param songID, meaning all the user needs to know is the UUID of the song in question
	 */
	public void deleteSong(String songID) {
		String sql = "DELETE FROM album_song WHERE fk_song_id = ? AND fk_album_id = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, songID);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * does same as above method, except the user passes in a whole Song object instead of just its ID
	 * @param song
	 */
	public void deleteSong(Song song) {
		String sql = "DELETE FROM album_song WHERE fk_song_id = ? AND fk_album_id = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, song.getSongID());
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
