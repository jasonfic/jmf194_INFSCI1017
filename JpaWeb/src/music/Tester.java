package music;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.*;

public class Tester {

	public static void main(String[] args) {
		GenreManager gm = new GenreManager();
		gm.createGenre(UUID.randomUUID().toString(), "Genre with generated ID", "No description");
		// gm.updateGenre(20, "I just updated this", "");
		
		// gm.deleteGenre(20);
		
	}

}
