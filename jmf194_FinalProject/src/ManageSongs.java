/**
 * this class manages CRUD operations for the Song class
 * based on Dr. Babichenko's MusicJPA example code from the GenreManager class
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 7
 * 3/5/2019
 */
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ManageSongs {
	
	/**
	 * uses an EntityManager to create a new song record in the database
	 * @param title
	 * @param length
	 * @param releaseDate
	 * @param recordDate
	 * @param filePath
	 */
	public void createSong(String songID, String title, int length, String releaseDate, String recordDate, String filePath) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jmf194_FinalProject");
		EntityManager em = emFactory.createEntityManager();
		
		em.getTransaction().begin();
		Song s = new Song();
		
		s.setSongID(songID);
		s.setTitle(title);
		s.setLength(length);
		s.setReleaseDate(releaseDate);
		s.setRecordDate(recordDate);
		s.setFilePath(filePath);
		
		em.persist(s);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
	}
	public JSONArray getSongList(String searchTerm, String searchType) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jmf194_FinalProject");
		EntityManager em = emFactory.createEntityManager();
		
		// Note that you are querying the object grid, not the database!
		String qry = "SELECT s.songID FROM Song s ";
		
		if(!searchTerm.equals("")){
			if(searchType.equalsIgnoreCase("equals")){
				qry += "WHERE s.title = '" + searchTerm + "'";
			}
			else if(searchType.equalsIgnoreCase("begin")){
				qry += "WHERE s.title LIKE '" + searchTerm + "%'";
			}
			else if(searchType.equalsIgnoreCase("ends")){
				qry += "WHERE s.title LIKE '%" + searchTerm + "'";
			}
			else if(searchType.equalsIgnoreCase("all")){
				qry += "GROUP BY title";
			}
			else{
				qry += "WHERE s.title LIKE '%" + searchTerm + "%'";
			}
		}
		
		List<String> songIDs = em.createQuery(qry).getResultList();
		JSONArray songListJSON = new JSONArray();
		for(String songID : songIDs) {
			Song s = em.find(Song.class, songID);
			songListJSON.put(s.toJSON());
		}
		em.close();
		emFactory.close();
		
		return songListJSON;
	}
	
	/*public JSONArray getSongListByArtistID(String genreID) {
		JSONArray songListJSON = new JSONArray();
		try {
			for (int i=0; i<) {
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return songListJSON;
	}*/
	/**
	 * uses an EntityManager to find a song record with a given ID and change its attributes as needed
	 * @param songID
	 * @param title
	 * @param length
	 * @param releaseDate
	 * @param recordDate
	 * @param filePath
	 */
	public void updateSong(String songID, String title, int length, String releaseDate, String recordDate, String filePath) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jmf194_FinalProject");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		
		if (!title.equals("")) {
			s.setTitle(title);
		}
		
		if (length != 0) {
			s.setLength(length);
		}
		
		if (!releaseDate.equals("")) {
			s.setReleaseDate(releaseDate);
		}
		
		if (!recordDate.equals("")) {
			s.setRecordDate(recordDate);
		}
		
		if (!filePath.equals("")) {
			s.setFilePath(filePath);
		}
		
		em.persist(s);
		em.getTransaction().commit();		
		em.close();
		emFactory.close();
	}
	/**
	 * uses an EntityManager to find a song record within the database and delete it
	 * @param songID
	 */
	public void deleteSong(String songID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jmf194_FinalProject");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		em.remove(s);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
	}
	/**
	 * finder method not shown in GenreManager example, but simple to guess what it should be
	 * same thing as deleteSong method but instead of deleting, returns a song
	 * @param songID
	 * @return Song object
	 */
	public Song findSong(String songID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jmf194_FinalProject");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
		return s;
	}
	
}
