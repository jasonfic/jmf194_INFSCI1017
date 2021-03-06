/**
 * this class is meant to test all the various methods from the Song, Artist, and Album classes
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 5
 * 2/12/2019
 */

import java.util.*;
import java.sql.*;
public class MusicTester {

	public static void main(String[] args) {
		//code taken from ConnectTest example from class
		String server = "sis-teach-01.sis.pitt.edu:3306";
		String userName = "infsci1017_2019";
		String password = "infsci1017_2019!";
		String dbName = "music2019";
		String mySqlConn = "jdbc:mysql://" + server + "/" + dbName + "?user=" + userName + "&password=" + password;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(mySqlConn);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Artist a = new Artist("3c0634af-ee1f-47f0-b1aa-a622bc059bfb", conn);
			a.setConnection(conn);
			System.out.println(a.getFirstName());
			System.out.println(a.getLastName());
			System.out.println(a.getBio());
			a.deleteArtist("e098bdeb-1420-47d3-bcd6-e893047cd7a5"); //deletes an artist from the table
		//this block creates a new artist record for Playboi Carti, then displays his stage name
		Artist a1 = new Artist("Jeffery", "Williams", "Young Thug", conn);
			System.out.println(a1.getBandName());
			a1.setBio("Uses his voice as an instrument"); //adds a biography since that isn't included in the constructor
		//this block should retrieve the record for Madvillainy from the album table, then displays all of its variables
		Album lp = new Album("19fcda1d-0b90-4d16-850d-f22e0d5f961b", conn);
			System.out.println(lp.getTitle());
			System.out.println(lp.getReleaseDate());
			System.out.println(lp.getNumberOfTracks());
			System.out.println(lp.getLength());
			System.out.println(lp.getRecordingCompany());
			System.out.println(lp.getPmrcRating());
			System.out.println(lp.getCoverImagePath());
			lp.deleteAlbum("1c5ca9e3-b074-4d7b-8505-8d0456ad4938"); //deletes an album from the table
			lp.deleteSong("be1bb4ef-8b9b-4fa0-bc50-bb3e896bf9d4"); //severs a connection between a song and album
		Album lp1 = new Album("JEFFERY", "2016-08-26 00:00:00", "300/Atlantic", 10, "Explicit", 42, conn);
			lp1.setCoverImagePath("pictures/albums/JEFFERY.jpg"); //adds an album cover path since that isn't included in the constructor
		Song s = new Song("1cdbf690-d8fc-4379-84a9-72b17727d170", conn);
			System.out.println(s.getTitle());
			System.out.println(s.getLength());
			System.out.println(s.getFilePath());
			s.deleteSong("2034a33a-dfaa-427a-a5cf-7068cfe420b9"); //this deletes a song from the database
			s.deleteArtist(a); //severs a connection between a song and artist
			s.deleteArtist("9056070f-3245-4425-888a-8641c9a66157");
			lp.deleteSong(s);
		Song s1 = new Song("Wyclef Jean", 3, "2016-08-26 00:00:00", "2016-05-04 00:00:00", conn);
			System.out.println(s1.getSongID());
			System.out.println(s1.getReleaseDate());
			System.out.println(s1.getRecordDate());
			s1.setFilePath("music/artists/Young_Thug/albums/JEFFERY/Wyclef_Jean.mp3");
			s1.addArtist(a1);
			Map <String, Artist> map = new HashMap<>();
			map.put(a1.getArtistID(), a1);
			s1.setSongArtists(map);
			System.out.println(s1.getSongArtists());
			lp1.addSong(s1);
			Map <String, Song> map1 = new HashMap<>();
			map1.put(s1.getSongID(), s1);
			lp1.setAlbumSongs(map1);
			System.out.println(lp1.getAlbumSongs());
			//changes record to another song from Die Lit
			s1.setTitle("Pick Up the Phone");
			s1.setLength(4);
			s1.setRecordDate("2016-06-03 00:00:00");
			s1.setReleaseDate("2016-03-01 00:00:00");
			s1.setFilePath("music/artists/Young_ Thug/albums/JEFFERY/PUTP.mp3");
		a1.setFirstName("Ghostface");
		a1.setLastName("Killah");
		a1.setBandName("Wu-Tang Clan");
		a1.setBio("Best member of the Wu-Tang Clan");
		lp.setTitle("Enter the Wu-Tang (36 Chambers)");
		lp.setReleaseDate("1993-11-09 00:00:00");
		lp.setNumberOfTracks(13);
		lp.setLength(62);
		lp.setRecordingCompany("Loud");
		lp.setPmrcRating("Clean");
		lp.setCoverImagePath("pictures/album_covers/36_Chambers");
	}

}
