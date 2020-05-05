package main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class validation2
 */
@WebServlet("/UploadValidation")
public class FileUploadValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger log	= Logger.getLogger(FileUploadValidation.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadValidation() {
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
		
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		
		try {
			
			List<FileItem> files = sf.parseRequest(request);
			
			for(FileItem file : files) {
				
				String[] extension = file.getName().split("\\.");
				
				if(extension[1].equals("csv")) {
					log.info("Input file accepted. Copying onto server");
					File csvFile = new File(PathResultFolder.getResultFolder()+file.getName());
					file.write(csvFile);
					log.info("Starting analysis");
					AnalysisTool analysis = new AnalysisTool();
					AnalysisResult latestUploadResult = analysis.doAnalysis(csvFile);
					
					//System.out.println(latestUploadResult.getWochentagUmsatz().get(0).getKey());	//for testing purposes
					
					request.getSession(true).setAttribute("latestUploadResult", latestUploadResult);
					response.sendRedirect("ResultDisplay.xhtml");
				} else {
					log.info("Selected file not accepted");
					request.setAttribute("file","You can only upload CSV Files");
					request.getRequestDispatcher("startseite.jsp").forward(request, response);
				}
			}
			
		} catch (FileUploadException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		} catch (Exception e ) {
			log.info(e.getMessage());
			//e.printStackTrace();
			log.info("wordArray is holding null. NullPointerException is handled.");
		}		
	}
}
