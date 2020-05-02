package servlet;

import java.io.File;
import java.io.IOException;
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

/**
 * Servlet implementation class validation2_1
 */
@WebServlet("/validation2_1")
public class validation2_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
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
						if (extension[1].equals("csv"))// 文件字段
							processFile(file);
						else {
							request.setAttribute("file", "You can only upload CSV Files");
							request.getRequestDispatcher("startseite.jsp").forward(request, response);

							return;
						}

					}
				}
			} catch (FileUploadException fue) {
				fue.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("wordArray is holding null. NullPointerException is handled.");
			}

		}
		request.setAttribute("file", "The File has successfully uploaded");
		request.getRequestDispatcher("startseite.jsp").forward(request, response);
	}

	private void processFile(FileItem item) {
		String fieldName = item.getFieldName();
		String fieldValue = item.getString();
		// 1.获取文件扩展名
		String suffix = item.getName().split("\\.")[1];
		String saveFileName = UUID.randomUUID().toString() + "." + suffix;//randomName
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
		try {
			item.write(new File(filePath));

			item.delete();// 删除临时文件
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
