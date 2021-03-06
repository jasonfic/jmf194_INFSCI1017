/**
 * this class deals with the album table in the music2019 database
 * it includes a default constructor, getters, and setters
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 7
 * 3/5/2019
 */

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

@Entity
@Table (name="album")
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="album_id")
	private String albumID;
	@Column(name="title")
	private String title;
	@Column(name="release_date")
	private String releaseDate;
	@Column(name="cover_image_path")
	private String coverImagePath;
	@Column(name="recording_company_name")
	private String recordingCompany;
	@Column(name="number_of_tracks")
	private int numberOfTracks;
	@Column(name="PMRC_rating")
	private String pmrcRating;
	@Column(name="length")
	private int length;
	@Transient
	private Map<String, Song> albumSongs = new HashMap<String, Song>();
	@Transient
	private Connection con;
	@Transient
	private PreparedStatement stmt;
	
	/**
	 * default constructor that sets a random UUID
	 */
	public Album() {
		UUID uAlbumID = UUID.randomUUID();
		albumID = uAlbumID.toString();
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
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 
	 * @return the date of release for a given album
	 */
	public String getReleaseDate() {
		return releaseDate;
	}
	/**
	 * 
	 * @param releaseDate
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	/**
	 * 
	 * @return the location of the album cover image file
	 */
	public String getCoverImagePath() {
		return coverImagePath;
	}
	/**
	 * 
	 * @param coverImagePath
	 */
	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}
	/**
	 * 
	 * @return the name of the record label that the given album was released under
	 */
	public String getRecordingCompany() {
		return recordingCompany;
	}
	/**
	 * 
	 * @param recordingCompany
	 */
	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
	}
	/**
	 * 
	 * @return the integer value for number of tracks on the given album
	 */
	public int getNumberOfTracks() {
		return numberOfTracks;
	}
	/**
	 * 
	 * @param numberOfTracks
	 */
	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}
	/**
	 * 
	 * @return the Parents Music Resource Center (PMRC) rating of a given album
	 */
	public String getPmrcRating() {
		return pmrcRating;
	}
	/**
	 * 
	 * @param pmrcRating
	 */
	public void setPmrcRating(String pmrcRating) {
		this.pmrcRating = pmrcRating;
	}
	/**
	 * 
	 * @return length of the album in minutes
	 */
	public int getLength() {
		return length;
	}
	/**
	 * 
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}
	/**
	 * 
	 * @return a HashMap object that places the key of a song ID next to a reference to a Song object that is on the album
	 */
	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}
	/**
	 * 
	 * @param albumSongs
	 */
	public void setAlbumSongs(Map<String, Song> albumSongs) {
		this.albumSongs = albumSongs;
	}
}
