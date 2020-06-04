package Entity;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Verantwortlich für die Umwandlung von Analyseergebnis zu Strings, und umgekehrt von Strings zu Analyseergebnissen.
 * Enthält weiterhin eine Methode die besagte Strings an die Speicher-Klasse übergibt.
 *
 */
public class SpeicherHandler {
	
	private static final Logger log = Logger.getLogger(SpeicherHandler.class.getName());
	
	private static final String prefUmsArt = "UpA";
	private static final String prefDeckArt = "DpA";
	private static final String prefUmsZeit = "UpZ";
	private static final String prefUmsTag = "UpT";
	private static final String prefComment = "Kom";
	
	private static final String prefSeparator = "-";
	
	
	/**
	 * 
	 * @param umsatzProArtikel Eine Map, die als Key-Value-Paare "Warengruppe-Umsatz" hat
	 * @return Ein String, der den Inhalt der Map representiert. KV-Paare sind durch ";" getrennt, und Key von Value durch ":"
	 */
	public static String put_AU(LinkedHashMap<String,Integer>  umsatzProArtikel){
		String ergebnis= prefUmsArt + prefSeparator;
		if(umsatzProArtikel != null) {
			for(Map.Entry<String,Integer> entry : umsatzProArtikel.entrySet()) {
				ergebnis+=entry.getKey()+":"+entry.getValue()+";";
			}	
		} else {
			log.info("Kein Inhalt zu Speichern.");
		}
		ergebnis += "/";
		log.info("Map zu String: Umsatz pro Artikel");
		return ergebnis;
	}
	
	/**
	 * 
	 * @param bestD Eine Map, die als Key-Value-Paare "Warengruppe-Deckungsspanne" hat
	 * @return Ein String, der den Inhalt der Map representiert. KV-Paare sind durch ";" getrennt, und Key von Value durch ":"
	 */
	public static String put_AD(LinkedHashMap<String, Integer> bestD){
		String ergebnis = prefDeckArt + prefSeparator;
		if(bestD != null) {
			for(Entry<String, Integer> entry : bestD.entrySet()) {
				ergebnis+=entry.getKey()+":"+entry.getValue()+";";
			}		
		}else {
			log.info("Kein Inhalt zu Speichern.");
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
		if(umsatzProZeit != null) {
			for(Entry<String, Integer> entry : umsatzProZeit.entrySet()) {
				ergebnis+=entry.getKey()+":"+entry.getValue()+";";
			}
		}else {
			log.info("Kein Inhalt zu Speichern.");
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
		if(umsatzProTag != null) {
			for(Entry<String, Integer> entry : umsatzProTag.entrySet()) {
				ergebnis += entry.getKey()+":"+entry.getValue()+";";
			}
		}else {
			log.info("Kein Inhalt zu Speichern.");
		}
		ergebnis+="/";
		log.info("Map zu String: Umsatz pro Wochentag");
		return ergebnis;
	}
	
	public static String reserveComment() {
		String ergebnis = prefComment + prefSeparator;
		ergebnis+="/";
		log.info("Kommentarfeld reserviert");
		return ergebnis;
	}
	
	/**
	 * Verwaltet die String-konvertierung und das darauf folgende Speichern der uebergebenen Analyseergebnisse.
	 * @param umsatzProArtikel Eine Map, die als Key-Value-Paare "Warengruppe-Umsatz" hat
	 * @param bestD Eine Map, die als Key-Value-Paare "Warengruppe-Deckungsspanne" hat
	 * @param umsatzProZeit Eine Map, die als Key-Value-Paare "Uhrzeit-Umsatz" hat
	 * @param umsatzProTag Eine Map, die als Key-Value-Paare "Wochentag-Umsatz" hat
	 * @see Speicher#readFile()
	 * @see Speicher#writeFile(String)
	 * @throws IOException
	 */
	public void put(LinkedHashMap<String,Integer> umsatzProArtikel 
						,LinkedHashMap<String, Integer> bestD 
						,LinkedHashMap<String,Integer> umsatzProZeit 
						,LinkedHashMap<String,Integer> umsatzProTag) throws IOException {
		
		String ergebnis="";
		ergebnis=put_AU(umsatzProArtikel)+put_AD(bestD)+put_Z(umsatzProZeit)+put_T(umsatzProTag)+reserveComment();
		
		Speicher.readFile();
		Speicher.writeFile(ergebnis);
	}
	
	/**
	 * Holt sich die Stringform des Analyseergebnisses aus dem Speicher, und durchsucht diese nach den Daten über Umsatz pro Warengruppe
	 * @param zeile Der Index des Analyseergebnisses im Speicher
	 * @see Speicher#getResult(int)
	 * @return Eine Map, die als Key-Value-Paare "Warengruppe-Umsatz" hat, oder null falls die Daten nicht gefunden werden konnten oder das Ergebnis nicht existiert.
	 */
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
						
						if(mm.length > 1 && isInt(mm[1])) {
							teilErgebnis.put(mm[0], Integer.parseInt(mm[1]));
						}else {
							log.info("Fehlerhafter Eintrag. Verworfen.");
						}
					}
					log.info("Erfolgreich");
					return teilErgebnis;
				}
			}
		}
		log.info("Nicht erfolgreich");
		return null;
	}
	
	/**
	 * Holt sich die Stringform des Analyseergebnisses aus dem Speicher, und durchsucht diese nach den Daten über Deckungsbeitrag pro Warengruppe
	 * @param zeile Der Index des Analyseergebnisses im Speicher
	 * @see Speicher#getResult(int)
	 * @return Eine Map, die als Key-Value-Paare "Warengruppe-Deckungsspanne" hat, oder null falls die Daten nicht gefunden werden konnten oder das Ergebnis nicht existiert.
	 */
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
						
						if(mm.length > 1 && isInt(mm[1])) {
						teilErgebnis.put(mm[0], Integer.parseInt(mm[1]));
						}else {
							log.info("Fehlerhafter Eintrag. Verworfen.");
						}
					}
					log.info("Erfolgreich");
					return teilErgebnis;
				}
			}
		}
		log.info("Nicht erfolgreich");
		return null;
	}
	
	/**
	 * Holt sich die Stringform des Analyseergebnisses aus dem Speicher, und durchsucht diese nach den Daten über Umsatz pro Uhrzeit
	 * @param zeile Der Index des Analyseergebnisses im Speicher
	 * @see Speicher#getResult(int)
	 * @return Eine Map, die als Key-Value-Paare "Uhrzeit-Umsatz" hat, oder null falls die Daten nicht gefunden werden konnten oder das Ergebnis nicht existiert.
	 */
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
						
						if(mm.length > 1 && isInt(mm[1])) {
						teilErgebnis.put(mm[0], Integer.parseInt(mm[1]));
						}else {
							log.info("Fehlerhafter Eintrag. Verworfen.");
						}
					}
					log.info("Erfolgreich");
					return teilErgebnis;
				}
			}
		}
		log.info("Nicht erfolgreich");
		return null;
	}
	
	/**
	 * Holt sich die Stringform des Analyseergebnisses aus dem Speicher, und durchsucht diese nach den Daten über Umsatz pro Wochentag
	 * @param zeile Der Index des Analyseergebnisses im Speicher
	 * @see Speicher#getResult(int)
	 * @return Eine Map, die als Key-Value-Paare "Wochentag-Umsatz" hat, oder null falls die Daten nicht gefunden werden konnten oder das Ergebnis nicht existiert.
	 */
	public LinkedHashMap<String, Integer> get_T(int zeile) {
		LinkedHashMap<String,Integer> teilErgebnis = new LinkedHashMap<>();
		String line = Speicher.getResult(zeile); 
		if(line != null) {
			String []s = line.split("/"); //Aufteilen in Unterergebnisse
			
			for(int i = 0; i < s.length; i++) {
				String []teil = s[i].split(prefSeparator,2);
				if(teil[0].equals(prefUmsTag)) {	//Unterergebnisse nach entsprechender Markierung durchsuchen
					String []m1 = teil[1].split(";");
					for(int j = 0; j < m1.length; j++) {
						String []mm = m1[j].split(":");
						
						if(mm.length > 1 && isInt(mm[1])) {
						teilErgebnis.put(mm[0], Integer.parseInt(mm[1]));
						} else {
							log.info("Fehlerhafter Eintrag. Verworfen.");
						}
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
