package music_jpa;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class CreateGenre extends HttpServlet {
	
	public CreateGenre() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String genreName = request.getParameter("genre_name");
		String genreDescr = request.getParameter("description");
		
		GenreManager gm = new GenreManager();
		gm.createGenre(UUID.randomUUID().toString(), genreName, genreDescr);
		response.getWriter().println("Genre Name: " + genreName);
	}
	//response.getWriter().append("Served at: ").append(request.getContextPat);
}
