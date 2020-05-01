package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
@WebServlet("/validation2")
public class validation2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validation2() {
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
				
				String[] extension = file.getName().split("\\.",2);
				
				if(extension[1].equals("csv")) {
					
					file.write(new File("C:\\Users\\Ahmed\\eclipse-workspaceEE\\SpmWebproj\\WebContent\\csvfiles\\"+file.getName()));
					
				} else {
				
					request.setAttribute("file","You can only upload CSV Files");
					request.getRequestDispatcher("startseite.jsp").forward(request, response);
					
					return;
				
				}
				
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e ) {
			// TODO Auto-generated catch block
			System.out.println("wordArray is holding null. NullPointerException is handled.");
		}
		
		request.setAttribute("file","The File has successfully uploaded");
		request.getRequestDispatcher("startseite.jsp").forward(request, response);
		
	}

}
