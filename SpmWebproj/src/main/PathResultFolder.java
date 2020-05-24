package main;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Hilfsklasse, deren einziger Zweck es ist, den Pfad des für die Analyse relevanten Ordners auf dem Gerät zu ermitteln.
 *
 */
public class PathResultFolder {
	
	/**
	 * Gibt den Pfad des Ordners "upload" zurück, in welchem alle fuer die Analyse benoetigten Dateien befindlich sein sollten.
	 * Setzt vorraus, dass "upload" ein direkter Unterordner von "WEB-INF" ist, und dass der Ordner, in welchem sich die .class-Datei von der Klasse PathResultFolder befindet, auch "WEB-INF" untergeordnet ist.
	 * @return Der Pfad des Ordners "upload"
	 */
	public static String getResultFolder() {
		URL url = PathResultFolder.class.getResource(PathResultFolder.class.getSimpleName() + ".class");	//Pfad der Datei PathResultFolder.class
		File folderFile = null;
		try {
			folderFile = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		while(!folderFile.getName().contentEquals("WEB-INF")) {		//Springt in den Ordner darüber, bis "WEB-INF" gefunden ist.
			folderFile=folderFile.getParentFile();
		}
		String folderPath = folderFile.getAbsolutePath()+"\\upload\\";
		return folderPath;
	}
}
