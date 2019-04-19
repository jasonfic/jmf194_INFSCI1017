package music;

import org.json.JSONException;
import org.json.JSONObject;

public class Artist {
	String artistID;

	public String getArtistID() {
		return artistID;
	}

	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}
	
	public JSONObject toJSON(){
		JSONObject genreJson = new JSONObject();
		try {
			genreJson.put("artist_id", this.artistID);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return genreJson;
		
	}
	
}
