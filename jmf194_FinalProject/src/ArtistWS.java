import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import music.Genre;
//import music.GenreManager;

/**
 * Servlet implementation class ArtistWS
 */
@WebServlet("/ArtistWS")
public class ArtistWS extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistWS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.setContentType("application/json");
		ManageArtists ma = new ManageArtists();
		String artistID = request.getParameter("artistID");
		if(artistID != null){
			if(!artistID.equals("")){
				Artist a = ma.findArtist(artistID);
				response.getWriter().print(a.toJSON().toString());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
