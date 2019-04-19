/**
 * this class deals with the song table in the music2019 database
 * it includes a default constructor, getters, and setters
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 7
 * 3/5/2019
 */

import javax.persistence.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.sql.*;

//annotation structure based on MusicJPA code example
@Entity
@Table (name="song")
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="song_id")
	private String songID;
	@Column(name="title")
	private String title;
	@Column(name="length")
	private int length;
	@Column(name="file_path")
	private String filePath;
	@Column(name="release_date")
	private String releaseDate;
	@Column(name="record_date")
	private String recordDate;
	@Transient							//given we aren't using queries within these classes, I didn't see the maps as being necessary
	private Map<String, Artist> songArtists = new HashMap<String, Artist>(); //also, I don't know how to access the junction tables using JPA
	//these 2 variables are leftovers from the last homework and are thus marked transient
	@Transient
	private Connection con;
	@Transient
	private PreparedStatement stmt;
	/**
	 * default constructor
	 * instead of making the user come up with an ID, I kept the UUID method implementation from previous assignments
	 */
	public Song() {
		UUID uSongID = UUID.randomUUID();
		songID = uSongID.toString();
	}
	
	/**
	 * 
	 * @return song UUID so it can be accessed outside of class
	 */
	public String getSongID() {
		return songID;
	}
	
	public void setSongID(String songID) {
		this.songID = songID;
	}
	/**
	 * 
	 * @return title of given song
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
	 * @return length of given song in minutes
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
	 * @return the location of the song file in its folder
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * 
	 * @return release date of given song
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
	 * @return the date a song was recorded
	 */
	public String getRecordDate() {
		return recordDate;
	}
	/**
	 * 
	 * @param recordDate
	 */
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	
	public JSONObject toJSON(){
		JSONObject songJson = new JSONObject();
		try {
			songJson.put("song_id", this.songID);
			songJson.put("title", this.title);
			songJson.put("length", this.length);
			songJson.put("file_path", this.filePath);
			songJson.put("release_date", this.releaseDate);
			songJson.put("record_date", this.recordDate);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return songJson;
	}
}
