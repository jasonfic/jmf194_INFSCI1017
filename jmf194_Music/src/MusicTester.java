/**
 * this class is meant to test all the various methods from the Song, Artist, and Album classes
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 5
 * 2/12/2019
 */

import java.util.*;
public class MusicTester {

	public static void main(String[] args) {
		//this block should retrieve Kendrick Lamar's record from the artist table, then display his first name, last name, and bio
		Artist a = new Artist("206a3497-06c0-4fb4-bbd6-746d29d77850");
			System.out.println(a.getFirstName());
			System.out.println(a.getLastName());
			System.out.println(a.getBio());
			a.deleteArtist("9ded1cfc-181e-46cc-ba9c-47f75a8dcfdc"); //deletes an artist from the table
		//this block creates a new artist record for Playboi Carti, then displays his stage name
		Artist a1 = new Artist("Jordan", "Carter", "Playboi Carti");
			System.out.println(a1.getBandName());
			a1.setBio("Rumored to have invented music"); //adds a biography since that isn't included in the constructor
		//this block should retrieve the record for Madvillainy from the album table, then displays all of its variables
		Album lp = new Album("13e7661c-9a1c-48d0-a3eb-d64677ec6dfa");
			System.out.println(lp.getTitle());
			System.out.println(lp.getReleaseDate());
			System.out.println(lp.getNumberOfTracks());
			System.out.println(lp.getLength());
			System.out.println(lp.getRecordingCompany());
			System.out.println(lp.getPmrcRating());
			System.out.println(lp.getCoverImagePath());
			lp.deleteAlbum("26a0d77e-4dd2-4fdb-b13f-40ae2967f254"); //deletes an album from the table
			lp.deleteSong("be1bb4ef-8b9b-4fa0-bc50-bb3e896bf9d4"); //severs a connection between a song and album
		//this block generates a new album record for Die Lit in the album table
		Album lp1 = new Album("Die Lit", "2018-05-11 00:00:00", "AWGE/Interscope", 19, "Explicit", 58);
			lp1.setCoverImagePath("pictures/album_covers/Die_Lit"); //adds an album cover path since that isn't included in the constructor
		//this block pulls Takyon from the song table, then displays its title, length, and file path
		Song s = new Song("07be5b01-d1a8-456b-89d0-d5d2918b71fb");
			System.out.println(s.getTitle());
			System.out.println(s.getLength());
			System.out.println(s.getFilePath());
			s.deleteSong("01e60e92-a623-4f78-ae89-60c1b4399768"); //this deletes a song from the database
			s.deleteArtist(a); //severs a connection between a song and artist
			s.deleteArtist("9056070f-3245-4425-888a-8641c9a66157");
			lp.deleteSong(s);
		//creates a record for FlatBed Freestyle in the songs table, then adds Playboi Carti as the recording artist and Die Lit as its album
		Song s1 = new Song("FlatBed Freestyle", 3, "2018-05-11 00:00:00", "2017-12-30 00:00:00");
			System.out.println(s1.getSongID());
			System.out.println(s1.getReleaseDate());
			System.out.println(s1.getRecordDate());
			s1.setFilePath("music/artists/Playboi_Carti/albums/Die_Lit/FlatBed_Freestyle.mp3");
			s1.addArtist(a1);
			Map <String, Artist> map = new HashMap<>();
			map.put(s1.getSongID(), a1); //adds the songID for FlatBed Freestyle to the Album record of Die Lit
			s1.setSongArtists(map);
			System.out.println(s1.getSongArtists());
			lp1.addSong(s1);
			Map <String, Song> map1 = new HashMap<>();
			map1.put(lp1.getAlbumID(), s1);
			lp1.setAlbumSongs(map1);
			System.out.println(lp1.getAlbumSongs());
			//changes record to another song from Die Lit
			s1.setTitle("Shoota");
			s1.setLength(2);
			s1.setRecordDate("2018-02-16 00:00:00");
			s1.setReleaseDate("2018-05-10 23:00:00");
			s1.setFilePath("music/artists/Playboi_Carti/albums/Die_Lit/Shoota.mp3");
		//modifies earlier records for Artist and Album to apply to Lil Uzi Vert
		a1.setFirstName("Symere");
		a1.setLastName("Woods");
		a1.setBandName("Lil Uzi Vert");
		a1.setBio("Very small and cute rapper");
		lp.setTitle("Luv Is Rage 2");
		lp.setReleaseDate("2015-10-30 00:00:00");
		lp.setNumberOfTracks(16);
		lp.setLength(57);
		lp.setRecordingCompany("Atlantic");
		lp.setPmrcRating("Clean");
		lp.setCoverImagePath("pictures/album_covers/Luv_Is_Rage");
	}

}
