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
	 * @param title
	 * @param length
	 * @param releaseDate
	 * @param recordDate
	 */
	public Song(String title, int length, String releaseDate, String recordDate) {
		db = new DbUtilities();
		UUID uSongID = UUID.randomUUID();
		songID = uSongID.toString();
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		
		String sql = "INSERT INTO song (title, length, release_date, record_date) VALUES ('" + title + "', '" + length + "', '" + releaseDate + "', '" + recordDate + "');";
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
			if (rs.next()) {
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
		this.songID = songID;
		db = new DbUtilities();
		String sql = "DELETE FROM song WHERE song_id = '" + this.songID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getSongID() {
		return songID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		String sql = "UPDATE song SET title = '" + title + "' WHERE song_id = '" + this.songID + "';";
		this.title = title;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		String sql = "UPDATE song SET length = " + length + " WHERE song_id = '" + this.songID + "';";
		this.length = length;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getFilePath() {
		return filePath;
	}
	public String setFilePath(String filePath) {
		return filePath;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		String sql = "UPDATE song SET release_date = '" + releaseDate + "' WHERE song_id = '" + this.songID + "';";
		this.releaseDate = releaseDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		String sql = "UPDATE song SET record_date = '" + recordDate + "' WHERE song_id = '" + this.songID + "';";
		this.recordDate = recordDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public Map<String, Artist> getSongArtists() {
		return songArtists;
	}
	public void setSongArtists(Map<String, Artist> songArtists) {
		this.songArtists = songArtists;
	}
	public void addArtist(Artist artist) {
		String sql = "INSERT INTO song_artist (fk_song_id, fk_artist_id) VALUES ('" + this.songID + "', '" + artist.getArtistID() + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public void deleteArtist(String artistID) {
		String sql = "DELETE FROM song_artist WHERE fk_artist_id = '" + artistID + "AND fk_song_id = '" + this.songID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public void deleteArtist(Artist artist) {
		String sql = "DELETE FROM song_artist WHERE fk_artist_id = '" + artist.getArtistID() + "' AND fk_song_id = '" + this.songID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
	}
}
