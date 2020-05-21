package Entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.PathResultFolder;

public class Speicher {
	private static final Logger log	= Logger.getLogger(Speicher.class.getName());
	
	private static List <String> analysisResults = new ArrayList<>(); 
	
	public static boolean readFile() {
		String pathname = PathResultFolder.getResultFolder() + "speicher.txt";
		try{
			File file = new File(pathname);
			if(file.exists()) {
			   FileReader read = new FileReader(pathname);
			   BufferedReader br = new BufferedReader(read);
			   String line;
			   int i = 0;
			   while((line = br.readLine())!=null){
				   analysisResults.add(line);
				   i++;
			   }
			   log.info("Anzahl von Speichereintraegen: "+i);
			   br.close();
			   read.close();
			   if(i<=10)
				   return true;
			   return false;
			}else {
				log.info("Speicherdatei konnte nicht gefunden werden");
				return false;
			}
		}catch(IOException e) {
			   e.printStackTrace();
			   return false;
		}
	}
	
	public static void addToList(String ergebnis) {
		log.info("Fuege Ergebnis hinzu");
		analysisResults.add(ergebnis);
		while(analysisResults.size() > 5) {
			analysisResults.remove(0);
		}
	}
	
	public static void saveToFile() throws IOException {
		String pathname = PathResultFolder.getResultFolder() + "speicher.txt";
		File saveFile = new File(PathResultFolder.getResultFolder() + "speicher.txt");
		if(saveFile.createNewFile()) {
			log.info("Lege Speicher an");
		}
		log.info("Schreibe Ergebnisse in Datei");
		FileWriter write = new FileWriter(pathname);
		BufferedWriter out =  new BufferedWriter(write);

		for(int i=0; i < analysisResults.size(); i++) {
			out.write(analysisResults.get(i));
			out.newLine();
		}
		   
		out.flush();
		out.close();
		write.close();
		log.info("Erfolgreich");
	}
	
	public static void writeFile(String ergebnis) throws IOException {
		addToList(ergebnis);
		saveToFile();
	}
	
	public static String getResult(int index) {
		String result = null;
		//log.info("Suche Ergebnis");
		if(index < analysisResults.size()) {
			result = analysisResults.get(index);
			//log.info("Erfolgreich");
		}
		return result;
	}
	
	/*public static void main(String[]args) throws IOException {
		speichern s = new speichern();
		//s.Ergebnisspeichern();
		s.readFile();
		//s.writeFile("This is a test eingabekkkkkkk");
	}
 */
}
