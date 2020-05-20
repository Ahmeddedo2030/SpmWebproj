package Entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.AnalysisTool;
import main.PathResultFolder;

public class SpeicherHandler {
	
	private static final Logger log = Logger.getLogger(SpeicherHandler.class.getName());
	
	private static final String prefUmsArt = "UpA-";
	private static final String prefDeckArt = "DpA-";
	private static final String prefUmsZeit = "UpZ-";
	private static final String prefUmsTag = "UpT-";
	
	
	/**
	 * 
	 * @param umsatzProArtikel Eine Map, die als Key-Value-Paare "Warengruppe-Umsatz" hat
	 * @return	Ein String, der den Inhalt der Map representiert. KV-Paare sind durch ";" getrennt, und Key von Value durch ":"
	 */
	public static String put_AU(LinkedHashMap<String,Integer>  umsatzProArtikel){
		String ergebnis= prefUmsArt;
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
		String ergebnis = prefDeckArt;
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
		String ergebnis = prefUmsZeit;
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
		String ergebnis = prefUmsTag;
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
		
		speichern s = new speichern();
		s.readFile();
		s.writeFile(ergebnis);
	}
	
	public LinkedHashMap<String,Integer> get_all_umsatz(int zeile) {
		String pathname= PathResultFolder.getResultFolder() + "speicher.txt";
		List <String> l = new ArrayList<>();
	    LinkedHashMap<String,Integer> All = new LinkedHashMap<>();
		try{
			File file = new File(pathname);
			if(file.exists()) {
			   FileReader read = new FileReader(pathname);
			   BufferedReader br = new BufferedReader(read);
			   String line;			   
			   while((line = br.readLine())!=null){
				   l.add(line);
			   }
			   if(zeile>=l.size())//选择的行数如果大于文件有的长度，就返回空值
				   return null;
			   String []s=(l.get(zeile)).split("/"); //读两行数据
			  // System.out.println("s的长度："+s.length+"  "+s[0]);
			   for(int i=0;i<s.length;i++) {
				   String []teil=s[i].split("-");
				   //System.out.println("teil的长度："+teil.length+" "+teil[0]);//+","+teil[1]+","+teil[2]);
				   if(teil[0].equals(prefUmsArt)) {
					   String []m = teil[1].split(";");
					   //System.out.println("m的长度："+m.length+"   "+m[0]+","+m[1]);
					   for(int j=0;j<m.length;j++) {
						   String []mm=m[j].split(":");
						  // System.out.println(mm[0]+"    "+mm[1]);
						   
						   if(isInt(mm[1]))
						     All.put(mm[0], Integer.parseInt(mm[1]));
						   
					   }
					   return All;
				   }
			   }
			}else {
				System.out.println("keine File!");
				
			}
		}catch(IOException e) {
			   e.printStackTrace();
			   
		}
		
		return null;
		
		
	}
	public LinkedHashMap<String, Integer> get_AB(int zeile) {
		//zeile*=2;
		String pathname = PathResultFolder.getResultFolder() + "speicher.txt";
		List <String> l = new ArrayList<>(); 
	    LinkedHashMap<String, Integer> AB = new LinkedHashMap<>();
		try{
			File file = new File(pathname);
			if(file.exists()) {
			   FileReader read = new FileReader(pathname);
			   BufferedReader br = new BufferedReader(read);
			   String line;			   
			   while((line = br.readLine())!=null){
				   l.add(line);
			   }
			   if(zeile>=l.size())
				   return null;
			   String []s=(l.get(zeile)).split("/"); //读两行数据
			   //System.out.println("s的长度："+s.length+"  "+s[0]);
			   for(int i=0;i<s.length;i++) {
				   String []teil=s[i].split("-");
				   //System.out.println("teil的长度："+teil.length+" "+teil[0]);//+","+teil[1]+","+teil[2]);
				   if(teil[0].equals(prefDeckArt)) {
					   String []m = teil[1].split(";");
					   //System.out.println("m的长度："+m.length+"   "+m[0]+","+m[1]);
					   for(int j=0;j<m.length;j++) {
						   String []mm=m[j].split(":");
						   //System.out.println(mm[0]+"    "+mm[1]);
						   
						   if(isInt(mm[1])&&mm[1].indexOf('.')==-1);
						     AB.put(mm[0], Integer.parseInt(mm[1]));
						   
					   }
					   return AB;
				   }
			   }
			}else {
				System.out.println("keine File!");
				
			}
		}catch(IOException e) {
			   e.printStackTrace();
			   
		}
		
		return null;
		
		
	}
	public LinkedHashMap<String,Integer> get_Z(int zeile) {
		//zeile*=2;
		String pathname=PathResultFolder.getResultFolder() + "speicher.txt";
		List <String> l = new ArrayList<>(); 
	    LinkedHashMap<String,Integer> z = new LinkedHashMap<>();
		try{
			File file = new File(pathname);
			if(file.exists()) {
			   FileReader read = new FileReader(pathname);
			   BufferedReader br = new BufferedReader(read);
			   String line;			   
			   while((line = br.readLine())!=null){
				   l.add(line);
			   }
			   if(zeile>=l.size())
				   return null;
			   String []s=(l.get(zeile)).split("/"); //读两行数据
			   //System.out.println("s的长度："+s.length+"  "+s[0]);
			   for(int i=0;i<s.length;i++) {
				   String []teil=s[i].split("#");
				  // System.out.println("teil的长度："+teil.length+" "+teil[0]);//+","+teil[1]+","+teil[2]);
				   if(teil[0].equals(prefUmsZeit)) {
					   
					   String []sp= teil[1].split(",");
					  // System.out.println("m的长度："+sp.length+"   "+sp[0]+","+sp[1]);
					   if(sp.length==4) {
						   String []m1 = sp[1].split(";");
						//   System.out.println("m的长度："+m1.length+"   "+m1[0]+","+m1[1]);
						   for(int j=0;j<m1.length;j++) {
							   String []mm=m1[j].split(":");
							   //System.out.println(mm[0]+"    "+mm[1]);
							   
							   if(isInt(mm[1]));
							     z.put(mm[0], Integer.parseInt(mm[1]));
							   
						   }					   }
					   return z;
				   }
			   }
			}else {
				System.out.println("keine File!");
				
			}
		}catch(IOException e) {
			   e.printStackTrace();
			   
		}
		
		return null;
		
		
	}
	
	public LinkedHashMap<String,Integer> get_T(int zeile) {
		//zeile*=2;
		String pathname=PathResultFolder.getResultFolder() + "speicher.txt";
		List <String> l = new ArrayList<>(); 
	    LinkedHashMap<String,Integer> t = new LinkedHashMap<>();
		try{
			File file = new File(pathname);
			if(file.exists()) {
			   FileReader read = new FileReader(pathname);
			   BufferedReader br = new BufferedReader(read);
			   String line;			   
			   while((line = br.readLine())!=null){
				   l.add(line);
			   }
			   
			   if(zeile>=l.size()) {
				   br.close();
				   return null;
			   } else {
				   String []s=(l.get(zeile)).split("/"); //读两行数据
				   //System.out.println("s的长度："+s.length+"  "+s[0]);
				   for(int i=0;i<s.length;i++) {
					   String []teil=s[i].split("#");
					  // System.out.println("teil的长度："+teil.length+" "+teil[0]);//+","+teil[1]+","+teil[2]);
					   if(teil[0].equals(prefUmsTag)) {
						   
						   String []sp= teil[1].split(",");
						  // System.out.println("m的长度："+sp.length+"   "+sp[0]+","+sp[1]);
						   if(sp.length==4) {
							   String []m2 = sp[3].split(";");
							   //System.out.println("m的长度："+m.length+"   "+m[0]+","+m[1]);
							   for(int j=0;j<m2.length;j++) {
								   String []mm=m2[j].split(":");
								   //System.out.println(mm[0]+"    "+mm[1]);
								   
								   if(isInt(mm[1]));
								     t.put(mm[0], Integer.parseInt(mm[1]));
								   
							   }
						   }
						   br.close();
						   return t;
					   }
				   }
			   }
			   br.close();
			}else {
				System.out.println("keine File!");
			}
		}catch(IOException e) {
			   e.printStackTrace();
			   
		}
		
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
