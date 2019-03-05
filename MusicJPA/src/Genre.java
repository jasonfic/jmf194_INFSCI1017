
import java.sql.SQLException;

import javax.persistence.*;

@Entity
@Table (name="genre")
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "genre_id")
	private int genreID;
	
	@Column(name = "genre_name")
	private String genreName;
	
	@Column(name = "description")
	private String description;
	
	
		
	
		public void setGenreID(int genreID){
			this.genreID = genreID;
		}
		
		
		
		public String getGenreName() {
			return genreName;
		}
		
		public void setGenreName(String genreName) {
			this.genreName = genreName;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
			
		}
		
		public int getGenreID() {
			return genreID;
		}
		
		

}
