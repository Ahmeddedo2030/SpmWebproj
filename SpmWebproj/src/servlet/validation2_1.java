package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;
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

import Entity.SpeicherHandler;
import main.AnalysisTool;
import main.PathResultFolder;
import Entity.Kunden_Basic_Info;
import Entity.Speicher;

/**
 * Servlet implementation class validation2_1
 */
@WebServlet("/Ergebnisse")
public class validation2_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log	= Logger.getLogger(validation2_1.class.getName());
	
	
    ArrayList<String> pathList = new ArrayList<>();
    LinkedHashMap<String, Integer> bestU = null;
    LinkedHashMap<String, Integer> bestD = null;
    LinkedHashMap<String, Integer> bestZ = null;
    LinkedHashMap<String, Integer> bestT = null;
    ArrayList<HashMap<String,Integer>> Kunden_Basic_Info = null;
    
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public validation2_1() {
		super();
	}

	/**
	 * Lädt das im Parameter "change" angegebene Ergebnis. Falls eine neuer Upload bemerkt wird, wird dieser in den Speicher geschrieben.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {//turn to result of data analyse
		Speicher.readFile();
		String changeParam = request.getParameter("change");
		
		SpeicherHandler ff = new SpeicherHandler();
		
		
		Kunden_Basic_Info = new ArrayList<>();
		if(pathList.size()>0) {
			log.info("Neue Datei gefunden. Starte Analyse.");
			
			long startTime = System.nanoTime();
			AnalysisTool analysis = new AnalysisTool(pathList.get(pathList.size()-1));
			pathList.remove(pathList.size()-1);
			bestU = analysis.umsatzProArtikel();
			bestD = analysis.deckungProArtikel();
			bestZ = analysis.umsatzProZeit();
			bestT = analysis.umsatzProTag();
			try {
				Kunden_Basic_Info = new ArrayList<>(analysis.kundenBasicInfo());
			} catch (Exception e) {
				log.info("Kundendaten konnten nicht geladen werden");
				e.printStackTrace();
			}
			long endTime = System.nanoTime();
			
			log.info("Analyse in " + (endTime-startTime)/1000000 + " ms");
			ff.put(bestU, bestD ,bestZ, bestT);
		}
		
		
		int change;
		int speicherZahl = Speicher.getNewestIndex();
		if(changeParam != null) {
			change = Integer.parseInt(changeParam) - 1;
			if (change < 0 || change > 4) {
				log.info("Ungültiger Parameter. Lade neustes Ergebnis");
				change = speicherZahl;
			} else if (change > speicherZahl){
				log.info("Ergebnis existiert nicht. Bitte Datei hochladen.");
				response.sendRedirect("startseite.jsp");
				return;
			}
		}else {
			log.info("Lade neustes Ergebnis");
			change = speicherZahl;
		}
			
		bestU = ff.get_all_umsatz(change);
		bestD = ff.get_AB(change);
		bestZ = ff.get_Z(change);
		bestT = ff.get_T(change);
		
		
		request.setAttribute("TF_Artikel_Umsatz", bestU );
		request.setAttribute("TF_Artikel_DBeitrag",bestD);
		request.setAttribute("Best_Z",bestZ);
		request.setAttribute("Best_T", bestT);
		
		if(Kunden_Basic_Info.size() > 0) {
			request.setAttribute("Sex", Kunden_Basic_Info.get(0));
			request.setAttribute("Kinder", Kunden_Basic_Info.get(1));
			request.setAttribute("Beruf", Kunden_Basic_Info.get(2));
			request.setAttribute("Family", Kunden_Basic_Info.get(3));
			request.setAttribute("Haus", Kunden_Basic_Info.get(4));
			request.setAttribute("Stammkunde", Kunden_Basic_Info.get(5));
			request.setAttribute("Age", Kunden_Basic_Info.get(6));
			request.setAttribute("Wohnort", Kunden_Basic_Info.get(7));
		}
		//request.setAttribute("signal", change);
		request.getRequestDispatcher("Display.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
						if(extension.length < 2) {
							log.info("Keine Datei gewählt");
							request.setAttribute("file", "Bitte eine gültige CSV Datei hochladen");
							request.setAttribute("signal", 0);
							request.getRequestDispatcher("startseite.jsp").forward(request, response);

							return;
						}
						else if (extension[1].equals("csv")) {// 文件字段
							processFile(file);
						    break;}
						else {
							log.info("Ungültige Datei");
							request.setAttribute("file", "Bitte eine gültige CSV Datei hochladen");
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
		// 1.获取文件扩展名
		String[] suffix = item.getName().split("\\.");
		String saveFileName = suffix[0].split("\\\\")[suffix[0].split("\\\\").length-1] + "." + suffix[1];
		log.info("Erhalten: " + saveFileName + ":" + item.getContentType());

		// 2.创建文件保存的文件夹
		String folderPath = PathResultFolder.getResultFolder();
		log.info("Speichern in: " + folderPath);
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
