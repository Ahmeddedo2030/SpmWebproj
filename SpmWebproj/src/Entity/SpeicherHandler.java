package Entity;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpeicherHandler {
	
	private static final Logger log = Logger.getLogger(SpeicherHandler.class.getName());
	
	private static final String prefUmsArt = "UpA";
	private static final String prefDeckArt = "DpA";
	private static final String prefUmsZeit = "UpZ";
	private static final String prefUmsTag = "UpT";
	
	private static final String prefSeparator = "-";
	
	
	/**
	 * 
	 * @param umsatzProArtikel Eine Map, die als Key-Value-Paare "Warengruppe-Umsatz" hat
	 * @return	Ein String, der den Inhalt der Map representiert. KV-Paare sind durch ";" getrennt, und Key von Value durch ":"
	 */
	public static String put_AU(LinkedHashMap<String,Integer>  umsatzProArtikel){
		String ergebnis= prefUmsArt + prefSeparator;
		for(Map.Entry<String,Integer> entry : umsatzProArtikel.entrySet()) {
			ergebnis+=entry.getKey()+":"+entry.getValue()+";";
		}	
		ergebnis += "/";
		log.info("Map zu String: Umsatz pro Artikel");
		return ergebnis;
	}
	
	/**
	 * 
	 * @param deckungProArtikel Eine Map, die als Key-Value-Paare "Warengruppe-Deckungsspanne" hat
	 * @return Ein String, der den Inhalt der Map representiert. KV-Paare sind durch ";" getrennt, und Key von Value durch ":"
	 */
	public static String put_AD(LinkedHashMap<String,Integer> deckungProArtikel){
		String ergebnis = prefDeckArt + prefSeparator;
		for(Entry<String, Integer> entry : deckungProArtikel.entrySet()) {
			ergebnis+=entry.getKey()+":"+entry.getValue()+";";
		}		
		ergebnis+="/";
		log.info("Map zu String: Deckungsspanne pro Artikel");
		return ergebnis;
	}
	
	/**
	 * 
	 * @param umsatzProZeit Eine Map, die als Key-Value-Paare "Uhrzeit-Umsatz" hat
	 * @return Ein String, der den Inhalt der Map representiert. KV-Paare sind durch ";" getrennt, und Key von Value durch ":"
	 */
	public static String put_Z(LinkedHashMap<String,Integer> umsatzProZeit){
		String ergebnis = prefUmsZeit + prefSeparator;
		for(Entry<String, Integer> entry : umsatzProZeit.entrySet()) {
			ergebnis+=entry.getKey()+":"+entry.getValue()+";";
		}	
		ergebnis+="/";
		log.info("Map zu String: Umsatz pro Uhrzeit");
		return ergebnis;
	}
	/**
	 * 
	 * @param umsatzProTag Eine Map, die als Key-Value-Paare "Wochentag-Umsatz" hat
	 * @return Ein String, der den Inhalt der Map representiert. KV-Paare sind durch ";" getrennt, und Key von Value durch ":"
	 */
	public static String put_T(LinkedHashMap<String,Integer> umsatzProTag){
		String ergebnis = prefUmsTag + prefSeparator;
		for(Entry<String, Integer> entry : umsatzProTag.entrySet()) {
			ergebnis+=entry.getKey()+":"+entry.getValue()+";";
		}	
		ergebnis+="/";
		log.info("Map zu String: Umsatz pro Wochentag");
		return ergebnis;
	}
	
	/**
	 * Verwaltet die String-konvertierung und das darauf folgende Speichern der uebergebenen Analyseergebnisse.
	 * @param umsatzProArtikel Eine Map, die als Key-Value-Paare "Warengruppe-Umsatz" hat
	 * @param deckungProArtikel Eine Map, die als Key-Value-Paare "Warengruppe-Deckungsspanne" hat
	 * @param umsatzProZeit Eine Map, die als Key-Value-Paare "Uhrzeit-Umsatz" hat
	 * @param umsatzProTag Eine Map, die als Key-Value-Paare "Wochentag-Umsatz" hat
	 * @throws IOException
	 */
	public void put(LinkedHashMap<String,Integer> umsatzProArtikel 
						,LinkedHashMap<String, Integer> deckungProArtikel 
						,LinkedHashMap<String,Integer> umsatzProZeit 
						,LinkedHashMap<String,Integer> umsatzProTag) throws IOException {
		
		String ergebnis="";
		ergebnis=put_AU(umsatzProArtikel)+put_AD(deckungProArtikel)+put_Z(umsatzProZeit)+put_T(umsatzProTag);
		
		Speicher.readFile();
		Speicher.writeFile(ergebnis);
	}
	
	public LinkedHashMap<String,Integer> get_all_umsatz(int zeile) {
		LinkedHashMap<String,Integer> teilErgebnis = new LinkedHashMap<>();
		String line = Speicher.getResult(zeile); 
		if(line != null) {
			String []s = line.split("/"); //Aufteilen in Unterergebnisse
			
			for(int i = 0; i < s.length; i++) {
				String []teil = s[i].split(prefSeparator,2);
				if(teil[0].equals(prefUmsArt)) {	//Unterergebnisse nach entsprechender Markierung durchsuchen
					String []m1 = teil[1].split(";");
					for(int j = 0; j < m1.length; j++) {
						String []mm = m1[j].split(":");
						
						if(isInt(mm[1]));
						teilErgebnis.put(mm[0], Integer.parseInt(mm[1]));
					}
					log.info("Erfolgreich");
					return teilErgebnis;
				}
			}
		}
		log.info("Nicht erfolgreich");
		return null;
	}
	
	public LinkedHashMap<String, Integer> get_AB(int zeile) {
		LinkedHashMap<String,Integer> teilErgebnis = new LinkedHashMap<>();
		String line = Speicher.getResult(zeile); 
		if(line != null) {
			String []s = line.split("/"); //Aufteilen in Unterergebnisse
			for(int i = 0; i < s.length; i++) {
				String []teil = s[i].split(prefSeparator,2);
				if(teil[0].equals(prefDeckArt)) {	//Unterergebnisse nach entsprechender Markierung durchsuchen
					String []m1 = teil[1].split(";");
					for(int j = 0; j < m1.length; j++) {
						String []mm = m1[j].split(":");
						
						if(isInt(mm[1]));
						teilErgebnis.put(mm[0], Integer.parseInt(mm[1]));
					}
					log.info("Erfolgreich");
					return teilErgebnis;
				}
			}
		}
		log.info("Nicht erfolgreich");
		return null;
	}
	
	public LinkedHashMap<String,Integer> get_Z(int zeile) {
		LinkedHashMap<String,Integer> teilErgebnis = new LinkedHashMap<>();
		String line = Speicher.getResult(zeile);
		if(line != null) {
			String []s = line.split("/"); //Aufteilen in Unterergebnisse
			
			for(int i = 0; i < s.length; i++) {
				String []teil = s[i].split(prefSeparator,2);
				if(teil[0].equals(prefUmsZeit)) {	//Unterergebnisse nach entsprechender Markierung durchsuchen
					String []m1 = teil[1].split(";");
					for(int j = 0; j < m1.length; j++) {
						String []mm = m1[j].split(":");
						
						if(isInt(mm[1]));
						teilErgebnis.put(mm[0], Integer.parseInt(mm[1]));
					}
					log.info("Erfolgreich");
					return teilErgebnis;
				}
			}
		}
		log.info("Nicht erfolgreich");
		return null;
	}
	
	public LinkedHashMap<String, Integer> get_T(int zeile) {
		LinkedHashMap<String,Integer> teilErgebnis = new LinkedHashMap<>();
		String line = Speicher.getResult(zeile); 
		if(line != null) {
			String []s = line.split("/"); //Aufteilen in Unterergebnisse
			
			for(int i = 0; i < s.length; i++) {
				String []teil = s[i].split(prefSeparator,2);
				if(teil[0].equals(prefUmsArt)) {	//Unterergebnisse nach entsprechender Markierung durchsuchen
					String []m1 = teil[1].split(";");
					for(int j = 0; j < m1.length; j++) {
						String []mm = m1[j].split(":");
						
						if(isInt(mm[1]));
						teilErgebnis.put(mm[0], Integer.parseInt(mm[1]));
					}
					log.info("Erfolgreich");
					return teilErgebnis;
				}
			}
		}
		log.info("Nicht erfolgreich");
		return null;
	}
	
	
	
	
	public static boolean isInt(String s) {
		if(s=="")
			return true;
		String reg = "[\\-|\\+]?\\d+";
		Pattern p;
		Matcher m;
		p = Pattern.compile(reg);
		m = p.matcher(s);
		if(m.matches())
			return true;
		else
			return false;
	}
	
}
