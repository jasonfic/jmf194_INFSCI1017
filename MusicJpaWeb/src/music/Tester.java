package music;
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
		GenreManager gm = new GenreManager();
		System.out.println(gm.getGenreList("jazz", "equals"));
		System.out.println(gm.getGenreList("j", "begins"));
		System.out.println(gm.getGenreList("zz", "ends"));
		
		

		
		
		
		
		
		
	}

}
