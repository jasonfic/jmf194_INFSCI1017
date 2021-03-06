/**
 * this class is meant to test all the various methods from the Song, Artist, and Album classes, plus their respective Managers
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 7
 * 3/5/2019
 */

public class MusicTester {

	public static void main(String[] args) {
		ManageSongs ms = new ManageSongs();
		ms.createSong("Trust Issues", 3, "2018-06-15 00:00:00", "2018-04-02 00:00:00", "music/Rico_Nasty/Nasty/Trust_Issues.mp3");
		ms.updateSong("049c56a3-631c-406a-a162-96a5aa0e87bf", "Blue Lights", 4, "2016-02-26 00:00:00", "2015-12-05 00:00:00", "music/Jorja_Smith/Lost&Found/Blue_Lights.mp3");
		ms.deleteSong("336e3b5d-126a-481b-9c96-6e1f90510b91");
		Song s = ms.findSong("159dc7df-6027-4718-9bd1-344755f289fb");
			System.out.println(s.getSongID());
			System.out.println(s.getTitle());
			System.out.println(s.getLength());
			System.out.println(s.getReleaseDate());
			System.out.println(s.getRecordDate());
			System.out.println(s.getFilePath());
			
		ManageArtists ma = new ManageArtists();
		ma.createArtist("Maria-Cecilia", "Kelly", "Rico Nasty", "RICO!! KENNYYYYYYYYY");
		ma.updateArtist("2b2d5f1d-563d-4ae7-af0b-60c27850da89", "Jorja", "Smith", "Jorja Smith", "The love of my life");
		ma.deleteArtist("38688ad4-f779-47a6-8687-b6d8b83bbfc1");
		Artist a = ma.findArtist("42aa1d00-41d2-4144-81ef-968e70c285fe");
			System.out.println(a.getArtistID());
			System.out.println(a.getFirstName());
			System.out.println(a.getLastName());
			System.out.println(a.getBandName());
			System.out.println(a.getBio());
		
		ManageAlbums mal = new ManageAlbums();
		mal.createAlbum("Nasty", "2018-06-15 00:00:00", "images/album_covers/Nasty.jpg", "Sugar Trap/Atlantic", 14, "Explicit", 37);
		mal.updateAlbum("0635d6d4-1056-4179-acd6-93fe3f0ff0a0", "Lost & Found", "2018-06-08 00:00:00", "images/album_covers/Lost&Found.jpg", "FAMM", 12, "Clean", 46);
		mal.deleteAlbum("207c0538-518e-4a74-8540-295560f483cb");
		Album al = mal.findAlbum("18816e36-c76c-40ea-ac45-f73dc0580671");
			System.out.println(al.getAlbumID());
			System.out.println(al.getTitle());
			System.out.println(al.getReleaseDate());
			System.out.println(al.getRecordingCompany());
			System.out.println(al.getNumberOfTracks());
			System.out.println(al.getLength());
			System.out.println(al.getPmrcRating());
			al.setCoverImagePath("images/album_covers/Die_Lit.jpg");
			System.out.println(al.getCoverImagePath());
	}

}
