/**
 * this class deals with the artist table in the music2019 database
 * it includes a default constructor, getters, and setters
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 7
 * 3/5/2019
 */

import javax.persistence.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
import java.util.*;

@Entity
@Table (name="artist")
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="artist_id")
	private String artistID;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="band_name")
	private String bandName;
	@Column(name="bio")
	private String bio;
	@Transient
	private Connection con;
	@Transient
	private PreparedStatement stmt;
	
	public Artist() {
		UUID uArtistID = UUID.randomUUID();
		artistID = uArtistID.toString();
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
		this.firstName = firstName;
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
		this.lastName = lastName;
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
		this.bandName = bandName;
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
		this.bio = bio;
	}
	public JSONObject toJSON(){
		JSONObject songJson = new JSONObject();
		try {
			songJson.put("artist_id", this.artistID);
			songJson.put("first_name", this.firstName);
			songJson.put("last_name", this.lastName);
			songJson.put("band_name", this.bandName);
			songJson.put("bio", this.bio);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return songJson;
	}
}
