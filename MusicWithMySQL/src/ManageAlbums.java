/**
 * this class manages CRUD operations for the Album class
 * based on Dr. Babichenko's MusicJPA example code from the GenreManager class
 * @author Jason Ficorilli
 * INFSCI 1017
 * Homework 7
 * 3/5/2019
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManageAlbums {
	
	/**
	 * uses an EntityManager to add an Album record to the database
	 * @param title
	 * @param releaseDate
	 * @param coverImagePath
	 * @param recordingCompany
	 * @param numberOfTracks
	 * @param pmrcRating
	 * @param length
	 */
	public void createAlbum(String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jmf194_Assignment7");
		EntityManager em = emFactory.createEntityManager();
		
		em.getTransaction().begin();
		Album a = new Album();
		
		a.setTitle(title);
		a.setReleaseDate(releaseDate);
		a.setCoverImagePath(coverImagePath);
		a.setRecordingCompany(recordingCompany);
		a.setNumberOfTracks(numberOfTracks);
		a.setPmrcRating(pmrcRating);
		a.setLength(length);
		
		em.persist(a);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
	}
	/**
	 * uses the albumID to find a record within the database and update its attributes as the user defines them
	 * @param albumID
	 * @param title
	 * @param releaseDate
	 * @param coverImagePath
	 * @param recordingCompany
	 * @param numberOfTracks
	 * @param pmrcRating
	 * @param length
	 */
	public void updateAlbum(String albumID, String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jmf194_Assignment7");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);
		
		if (!title.equals("")) {
			a.setTitle(title);
		}
		
		if (!releaseDate.equals("")) {
			a.setReleaseDate(releaseDate);
		}
		
		if (!coverImagePath.equals("")) {
			a.setCoverImagePath(coverImagePath);
		}
		
		if (!recordingCompany.equals("")) {
			a.setRecordingCompany(recordingCompany);
		}
		
		if (numberOfTracks != 0) {
			a.setNumberOfTracks(numberOfTracks);
		}
		
		if (!pmrcRating.equals("")) {
			a.setPmrcRating(pmrcRating);
		}
		
		if(length != 0) {
			a.setLength(length);
		}
		
		em.persist(a);
		em.getTransaction().commit();		
		em.close();
		emFactory.close();
	}
	/**
	 * uses an EntityManager to delete an Album record with the given ID
	 * @param albumID
	 */
	public void deleteAlbum(String albumID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jmf194_Assignment7");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);
		em.remove(a);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
	}
	/**
	 * uses an EntityManager to find an Album record with the given ID and allows the user to access it (similar to a getter)
	 * @param albumID
	 * @return Album object
	 */
	public Album findAlbum(String albumID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jmf194_Assignment7");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
		return a;
	}
		
}
