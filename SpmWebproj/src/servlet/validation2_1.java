package servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import Entity.bekommen;
import Entity.Kunden_Basic_Info;

/**
 * Servlet implementation class validation2_1
 */
@WebServlet("/validation2_1")
public class validation2_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ArrayList<String> pathList=new ArrayList<>();
    LinkedHashMap<String,Integer> All_TF_Artikel_Umsatz = null;
    LinkedHashMap <String, Float> TF_Artikel_DBeitrag =null;
    ArrayList<LinkedHashMap<String,Integer>> Best_Z_T =null;
    ArrayList<HashMap<String,Integer>> Kunden_Basic_Info =null;
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
		int change ;
		if(request.getParameter("change")==null)
			change=5;
		
		else
		    change = Integer.parseInt(request.getParameter("change"))-1;
		//for(int i = 0;i<pathList.size();i++) 
		//System.out.print(change);
		if(pathList.size()>0) {
		try {
			All_TF_Artikel_Umsatz = new LinkedHashMap<>(new OrdnungNachUmsatz().OrdnungNachUmsatz(pathList.get(pathList.size()-1)));
			TF_Artikel_DBeitrag =new LinkedHashMap<>(new TFDeckung().TFDeckung(pathList.get(pathList.size()-1)));
			Best_Z_T =new ArrayList<>(new FindBestDayandTime(). FindBesttagUndUhrzeit(pathList.get(pathList.size()-1)));
			Kunden_Basic_Info =new ArrayList<>(new Kunden_Basic_Info(). Basic_Info(pathList.get(pathList.size()-1)));
			//把前面的这三个分析数据存到文件中
			bekommen ff= new bekommen();
			
			//找出用户想要看的第change行数据
			
			if(change==5) {//表示有新文件加载，这个时候将数据保存
				request.setAttribute("TF_Artikel_Umsatz", All_TF_Artikel_Umsatz );
				request.setAttribute("TF_Artikel_DBeitrag",TF_Artikel_DBeitrag);
				request.setAttribute("Best_Z",Best_Z_T.get(0));
				request.setAttribute("Best_T", Best_Z_T.get(1));
				ff.put(All_TF_Artikel_Umsatz,TF_Artikel_DBeitrag,Best_Z_T);
			}else if(change<5&&change>=0) {
				//System.out.print(change);
				request.setAttribute("TF_Artikel_Umsatz", ff.get_all_umsatz(change));
				request.setAttribute("TF_Artikel_DBeitrag", ff.get_AB(change));
				request.setAttribute("Best_Z", ff.get_ZT(change).get(0));
				request.setAttribute("Best_T", ff.get_ZT(change).get(1));
				//System.out.print(ff.get_all_umsatz(change));
				
			}
			request.setAttribute("Sex", Kunden_Basic_Info.get(0));
			request.setAttribute("Kinder", Kunden_Basic_Info.get(1));
			request.setAttribute("Beruf", Kunden_Basic_Info.get(2));
			request.setAttribute("Family", Kunden_Basic_Info.get(3));
			request.setAttribute("Haus", Kunden_Basic_Info.get(4));
			request.setAttribute("Stammkunde", Kunden_Basic_Info.get(5));
			request.setAttribute("Age", Kunden_Basic_Info.get(6));
			request.setAttribute("Wohnort", Kunden_Basic_Info.get(7));
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
					if(file.getName()==null||file.getName().isEmpty()) {
						request.setAttribute("message","Please upload a file");
						request.getRequestDispatcher("startseite.jsp").forward(request, response);
					}
					else{
						
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
		String folderPath = "WEB-INF/upload";
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
