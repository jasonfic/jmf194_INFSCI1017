import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.persistence.*;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class Tester {

	public static void main(String[] args) {
		/*ManageArtists ma = new ManageArtists();
		System.out.println(ma.getArtistList("Kendrick", "equals"));
		System.out.println(ma.getArtistList("K", "begins"));
		System.out.println(ma.getArtistList("ck", "ends"));
		
		ManageSongs ms = new ManageSongs();
		System.out.println(ms.getSongList("Shoota", "equals"));
		System.out.println(ms.getSongList("Blue", "begins"));
		System.out.println(ms.getSongList("Issues", "ends"));*/
		
		ManageAlbums am = new ManageAlbums();
		System.out.println(am.getAlbumList("Exmilitary", "equals"));
		System.out.println(am.getAlbumList("Die", "begins"));
		System.out.println(am.getAlbumList("Love", "ends"));
	}
}
