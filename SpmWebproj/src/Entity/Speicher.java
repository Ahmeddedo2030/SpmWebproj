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
	/**
	 * Enthält zur Darstellung verfügbare Analyseergebnisse in Stringform. Ältere Einträge befinden sich an niedrigeren Indizes.
	 */
	private static List <String> analysisResults = null; 
	
	/**
	 * Überschreibt, sofern möglich, analysisResults mit Analyseergebnissen aus der Datei speicher.txt.
	 * Dies geschieht auch, wenn speicher.txt leer ist, was in analysisResults als leere Liste resultiert.
	 * Schlägt fehl wenn speicher.txt nicht existiert.
	 * @return <b>true</b>, wenn analysisResults erfolgreich überschrieben wurde; andernfalls <b>false</b>.
	 */
	public static boolean readFile() {
		String pathname = PathResultFolder.getResultFolder() + "speicher.txt";
		analysisResults = new ArrayList<String>();
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
	
	/**
	 * Fügt den übergebenen String zur Liste analysisResults hinzu. Überprüft aber nicht, ob es sich um ein gültiges Analyseergebnis handelt. Danach löscht es solange ältere Einträge bis die Liste wieder auf 5 Einträge reduziert ist.
	 * @param ergebnis Die Stringform des Analyseergebnisses, dass zur Liste analysisResults hinzugefügt werden soll.
	 */
	public static void addToList(String ergebnis) {
		log.info("Fuege Ergebnis hinzu");
		analysisResults.add(ergebnis);
		while(analysisResults.size() > 5) {
			analysisResults.remove(0);
		}
	}
	
	/**
	 * Schreibt die Strings aus der Liste analysisResults in die Datei speicher.txt
	 * @throws IOException
	 */
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
	
	/**
	 * Ruft addToList und saveToFile auf
	 * @see addToList
	 * @see saveToFIle
	 * @param ergebnis Der Parameter, der an addToList übergeben wird
	 * @throws IOException
	 */
	public static void writeFile(String ergebnis) throws IOException {
		addToList(ergebnis);
		saveToFile();
	}
	
	/**
	 * Liefert das Analyseergebnisses aus <i>analysisResults</i> am übergebenen Index sofern vorhanden. Falls <i>analysisResults</i> zuvor noch nicht geladen wurde wird <i>readFile</i> aufgerufen 
	 * @param index Der index des gesuchten Analyseergebnisses in der Liste <i>analysisResults</i>.
	 * @return Die Stringform des gesuchten Analyseergebnisses, oder <i>null</i> wenn nicht vorhanden.
	 */
	public static String getResult(int index) {
		String result = null;
		if(analysisResults == null) {
			readFile();
		}
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
