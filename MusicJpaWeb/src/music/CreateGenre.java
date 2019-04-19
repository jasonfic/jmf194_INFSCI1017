package music;


import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GenreServlet
 */
@WebServlet("/servlets/CreateGenre")
public class CreateGenre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGenre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("genreName") != null && request.getParameter("genreDescr") != null){
			String genreName = request.getParameter("genreName");
			String genreDescr = request.getParameter("genreDescr");
			
			GenreManager gm = new GenreManager();
			gm.createGenre(UUID.randomUUID().toString(), genreName, genreDescr);
			// response.getWriter().println("Genre Name: " + genreName);
			response.sendRedirect("../CreateGenreForm.jsp");
		}
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
