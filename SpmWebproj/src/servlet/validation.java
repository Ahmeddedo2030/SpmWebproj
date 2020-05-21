package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Entity.SpeicherHandler;

/**
 * Servlet implementation class validatation
 */
@WebServlet("/validation")
public class validation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(validation.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String submit = request.getParameter("submit");
		
		
		if(submit.equals("login")) {
		
		if(username.equals("admin") && password.equals("admin")) {
			log.info("Erfolgreicher LogIn");
			request.setAttribute("message","Erfolgreich angemeldet");
			request.setAttribute("signal",0);
			request.getRequestDispatcher("startseite.jsp").forward(request, response);
			
		} else {
			log.info("LogIn nicht erfolgreich");
			request.setAttribute("message","Benutzername und/oder Passwort sind falsch.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		
		}
		
	}

}