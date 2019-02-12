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
	
	public Album(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		db = new DbUtilities();
		UUID uAlbumID = UUID.randomUUID();
		albumID = uAlbumID.toString();
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		
		String sql = "INSERT INTO album (title, release_date, recording_company, number_of_tracks, pmrc_rating, length) VALUES ('" + title + "', '" + releaseDate + "', '" + recordingCompany + "', '" + numberOfTracks + "', '" + pmrcRating + "', '" + length + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public Album(String albumID) {
		this.albumID = albumID;
		db = new DbUtilities();
		String sql = "SELECT title, release_date, recording_company, number_of_tracks, pmrc_rating, length FROM album WHERE album_id = '" + this.albumID + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			if (rs.next()) {
				this.title = rs.getString("title");
				this.releaseDate = rs.getString("release_date");
				this.recordingCompany = rs.getString("recording_company");
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.pmrcRating = rs.getString("PMRC_rating");
				this.length = rs.getInt("length");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteAlbum(String albumID) {
		this.albumID = albumID;
		db = new DbUtilities();
		String sql = "DELETE FROM albun WHERE album_id = '" + this.albumID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getAlbumID() {
		return albumID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		String sql = "UPDATE album SET title = '" + title + "' WHERE album_id = '" + this.albumID + "';";
		this.title = title;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		String sql = "UPDATE album SET release_date = '" + releaseDate + "' WHERE album_id = '" + this.albumID + "';";
		this.releaseDate = releaseDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getCoverImagePath() {
		return coverImagePath;
	}
	public void setCoverImagePath(String coverImagePath) {
		String sql = "UPDATE album SET cover_image_path = '" + coverImagePath + "' WHERE album_id = '" + this.albumID + "';";
		this.coverImagePath = coverImagePath;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getRecordingCompany() {
		return recordingCompany;
	}
	public void setRecordingCompany(String recordingCompany) {
		String sql = "UPDATE album SET recording_company = '" + recordingCompany + "' WHERE album_id = '" + this.albumID + "';";
		this.recordingCompany = recordingCompany;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public int getNumberOfTracks() {
		return numberOfTracks;
	}
	public void setNumberOfTracks(int numberOfTracks) {
		String sql = "UPDATE album SET number_of_tracks = " + numberOfTracks + " WHERE album_id = '" + this.albumID + "';";
		this.numberOfTracks = numberOfTracks;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getPmrcRating() {
		return pmrcRating;
	}
	public void setPmrcRating(String pmrcRating) {
		String sql = "UPDATE album SET PMRC_rating = '" + pmrcRating + "' WHERE album_id = '" + this.albumID + "';";
		this.pmrcRating = pmrcRating;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		String sql = "UPDATE album SET length = " + length + " WHERE album_id = '" + this.albumID + "';";
		this.length = length;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}
	public void setAlbumSongs(Map<String, Song> albumSongs) {
		this.albumSongs = albumSongs;
	}
	public void addSong(Song song) {
		String sql = "INSERT INTO album_song (fk_album_id, fk_song_id) VALUES ('" + this.albumID + "', '" + song.getSongID() + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public void deleteSong(String songID) {
		String sql = "DELETE FROM album_song WHERE fk_song_id = '" + songID + "' AND fk_album_id = '" + this.albumID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public void deleteSong(Song song) {
		String sql = "DELETE FROM album_song WHERE fk_song_id = '" + song.getSongID() + "' AND fk_album_id = '" + this.albumID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
	}
}
