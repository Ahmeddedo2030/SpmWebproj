package Entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CommentHandler {
	
	private static final String prefComment = "Kom";
	private static final String prefSeparator = "-";
	
	public static ArrayList<String> getComments(int zeile) {
		ArrayList<String> teilErgebnis = new ArrayList<String>();
		String line = Speicher.getResult(zeile); 
		if(line != null) {
			String []s = line.split("/"); //Aufteilen in Unterergebnisse
			
			for(int i = 0; i < s.length; i++) {
				String []teil = s[i].split(prefSeparator,2);
				if(teil[0].equals(prefComment)) {	//Unterergebnisse nach entsprechender Markierung durchsuchen
					String []m1 = teil[1].split(";com;");
						for(String tmp: m1) {
							teilErgebnis.add(tmp);
						}
					return teilErgebnis;
				}
			}
		}
		return null;
	}
	
	public static String replaceComments(int zeile, String newComments) {
		String line = Speicher.getResult(zeile); 
		if(line != null) {
			String []s = line.split("/"); //Aufteilen in Unterergebnisse
			
			for(int i = 0; i < s.length; i++) {
				String []teil = s[i].split(prefSeparator,2);
				if(teil[0].equals(prefComment)) {	//Unterergebnisse nach entsprechender Markierung durchsuchen
					s[i]= prefComment + prefSeparator + newComments;
				}
			}
			line = "";
			for(String tmp: s) {
				line += tmp + "/";
			}
		}
		return line;
	}
}
