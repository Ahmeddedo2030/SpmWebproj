package servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entity.CommentHandler;
import Entity.Speicher;

/**
 * Servlet implementation class commentInputServlet
 */
@WebServlet("/commentHandler")
public class commentInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log	= Logger.getLogger(commentInputServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public commentInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentString = request.getParameter("commentList");
		String changeParam = request.getParameter("resultID");
		log.info(commentString);
		
		int change;
		int speicherZahl = Speicher.getNewestIndex();
		if(changeParam != null) {
			try {
			change = Integer.parseInt(changeParam) - 1;
			} catch (NumberFormatException e) {
				log.info("Kein Parameter. Neuestem Ergebnis zugeordnet");
				change = speicherZahl;
				changeParam = "" + (change+1);
			}
			if (change < 0 || change > 4) {
				log.info("Ung¨¹ltiger Parameter. Neuestem Ergebnis zugeordnet");
				change = speicherZahl;
			} else if (change > speicherZahl){
				log.info("Ergebnis existiert nicht. Bitte Datei hochladen.");
				response.sendRedirect("startseite.jsp");
				return;
			}
		}else {
			log.info("Kein Parameter. Neuestem Ergebnis zugeordnet");
			change = speicherZahl;
		}
		
		if(commentString != null) {
			Speicher.replaceResult(change, CommentHandler.replaceComments(change, commentString));
		}
		response.sendRedirect("Ergebnisse?change=" + changeParam);
		
	}

}
