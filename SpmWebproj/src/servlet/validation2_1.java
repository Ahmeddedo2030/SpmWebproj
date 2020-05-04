package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Entity.FindBestDayandTime;
import Entity.OrdnungNachUmsatz;
import Entity.TFDeckung;



/**
 * Servlet implementation class validation2_1
 */
@WebServlet("/validation2_1")
public class validation2_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ArrayList<String> pathList=new ArrayList<>();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public validation2_1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {//turn to result of data analyse
		// TODO Auto-generated method stub
		//int change = Integer.parseInt(request.getParameter("signal"));
		
		
		for(int i = 0;i<pathList.size();i++) {
			
		 ArrayList<String> TF_Artikel_Umsatz;
		try {
			ArrayList<String> All_TF_Artikel_Umsatz = new ArrayList<String>(new OrdnungNachUmsatz().OrdnungNachUmsatz(pathList.get(i)));
			
			ArrayList<String> TF_Artikel_Umsatz1 = new ArrayList<>();
			TF_Artikel_Umsatz1.add(All_TF_Artikel_Umsatz.get(0));
			TF_Artikel_Umsatz1.add(All_TF_Artikel_Umsatz.get(All_TF_Artikel_Umsatz.size()-1));
			ArrayList<String> TF_Artikel_DBeitrag =new ArrayList<>(new TFDeckung().TFDeckung(pathList.get(i)));
		    ArrayList<ArrayList<String>> Best_Z_T =new ArrayList<>(new FindBestDayandTime(). FindBesttagUndUhrzeit(pathList.get(i)));
			request.setAttribute("TF_Artikel_Umsatz", TF_Artikel_Umsatz1 );
			request.setAttribute("TF_Artikel_DBeitrag",TF_Artikel_DBeitrag);
			request.setAttribute("Best_Z",Best_Z_T.get(0));
			request.setAttribute("Best_T", Best_Z_T.get(1));
			
			
			//request.setAttribute("signal", change);
			request.getRequestDispatcher("Display.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new Exception("data read fail"); 
		}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		//int signal = Integer.parseInt(request.getParameter("signal"));
		// 1.判断是否为文件上传表单
		
			if (ServletFileUpload.isMultipartContent(request)) {
			// 2.创建个文件工厂
			FileItemFactory factory = new DiskFileItemFactory();
			// 3.创建文件上传对象
			ServletFileUpload fileUpload = new ServletFileUpload(factory);

			// 4.解析请求
			try {
				List<FileItem> files = fileUpload.parseRequest(request);

				for (FileItem file : files) {
					String[] extension = file.getName().split("\\.", 2);
					if (file.isFormField()) {// 普通表单字段
						String fieldName = file.getFieldName();
						String fieldValue = file.getString();
						System.out.println(fieldName + ":" + fieldValue);

					} else {
						if (extension[1].equals("csv")) {// 文件字段
							processFile(file);
						    break;}
						else {
							request.setAttribute("file", "You can only upload CSV Files");
							request.setAttribute("signal", 0);
							request.getRequestDispatcher("startseite.jsp").forward(request, response);

							return;
						}

					}
				}
			}
		catch (FileUploadException fue) {
				fue.printStackTrace();
			} 

			}
		request.setAttribute("file", "The File has successfully uploaded");
		request.setAttribute("signal", 1);
		request.getRequestDispatcher("startseite.jsp").forward(request, response);
	}

	private void processFile(FileItem item) {
		String fieldName = item.getFieldName();
		String fieldValue = item.getString();
		// 1.获取文件扩展名
		String[] suffix = item.getName().split("\\.");
		//String saveFileName = UUID.randomUUID().toString() + "." + suffix;//randomName
		String saveFileName = suffix[0].split("\\\\")[suffix[0].split("\\\\").length-1] + "." + suffix[1];
		System.out.println(saveFileName + ":" + item.getContentType());

		// 2.创建文件保存的文件夹
		String folderPath = "C:/Users/小彭子hhh/AppData/Roaming/SPB_Data/git/SpmWebproj/SpmWebproj/WebContent/WEB-INF/upload";
		System.out.println("保存的路径:" + folderPath);
		File file = new File(folderPath);
		if (!file.exists()) {
			file.mkdirs();
		}

		// 3.保存文件
		// 文件完整保存路径
		String filePath = folderPath + "/" + saveFileName;
		pathList.add(filePath);
		try {
			item.write(new File(filePath));

			item.delete();// 删除临时文件
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
