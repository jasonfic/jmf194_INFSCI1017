/**
 * this class manages CRUD operations for the Song class
 * based on Dr. Babichenko's MusicJPA example code from the GenreManager class
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 7
 * 3/5/2019
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManageSongs {

	/**
	 * uses an EntityManager to create a new song record in the database
	 * @param title
	 * @param length
	 * @param releaseDate
	 * @param recordDate
	 * @param filePath
	 */
	public void createSong(String title, int length, String releaseDate, String recordDate, String filePath) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jmf194_Assignment7");
		EntityManager em = emFactory.createEntityManager();
		
		em.getTransaction().begin();
		Song s = new Song();
		
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
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jmf194_Assignment7");
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
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jmf194_Assignment7");
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
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jmf194_Assignment7");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
		return s;
	}
	
}
