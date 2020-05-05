package main;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class PathResultFolder {
	/*private static String resultFolderName = ;*/
	/*Es w�re gut, wenn wir eine Kongiurationsdatei irgenwo h�tten, 
	 * in welche wir den Pfad zum Ordner der Analysedateien geschrieben wird, 
	 * sodass dieser zentral ausgelesen und/oder ver�ndert werden kann. - Finn
	 */
	
	public static String getResultFolder() {
		URL url = PathResultFolder.class.getResource(PathResultFolder.class.getSimpleName() + ".class");	//Takes path of THIS class file
		File folderFile = null;
		try {
			folderFile = new File(url.toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!folderFile.getName().contentEquals("WEB-INF")) {		//Searches Ancestors until it finds directory WEB-INF
			folderFile=folderFile.getParentFile();
		}
		String folderPath = folderFile.getAbsolutePath()+"\\analysisResults\\";
		return folderPath;
	}
}
