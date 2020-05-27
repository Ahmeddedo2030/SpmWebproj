package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.*;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericCleaner;
import weka.filters.unsupervised.attribute.Remove;

/**
 * Diese Klasse enthält die Methoden zur ausertung der Daten aus einer spezifischen Datei.
 * 
 *
 */
public class AnalysisTool {
	/**
	 * Die Weka-Instanz, die die Daten aus dem Fileobjekt <i>src</i> geladen hat.
	 * @see #src
	 */
	private Instances alleDaten = null;
	/**
	 * Der Name der Datei, welche von dieser Klasseninstanz analysiert werden soll.
	 */
	private String fileName;
	
	/**
	 * Das Fileobjekt der Datei, die von dieser Klasseninstanz analysiert werden soll.
	 */
	private File src = null;
	private static final Logger log	= Logger.getLogger(AnalysisTool.class.getName());
	
	/**
	 * Konstruktor
	 * @see #src
	 * @see #fileName
	 * @see #datenLaden()
	 * @param sourceFile Der absolute Pfad der Datei, die analysiert werden soll
	 */
	public AnalysisTool(String sourceFile) {
		src = new File(sourceFile);
		fileName = src.getName();
		datenLaden();
	}

	/**
	 * Füllt <i>alleDaten</i> mit den Daten aus dem Fileobjekt <i>src</i>. Erstezt weiterhin alle Werte kleiner als 1 mit "?"
	 * @see #alleDaten
	 * @see #src
	 */
	public void datenLaden() {
		// Übergebene CSV-Datei laden
		CSVLoader loader = new CSVLoader();
		try {
			loader.setSource(src);
			log.info("Setting sourcefile to " + fileName);
			} catch (IOException e) {
			log.info("Could not set sourcefile to " + fileName);
			log.info(e.getMessage());
		}
		try {
			alleDaten = loader.getDataSet();
			log.info("Successfully retrieved data from " + fileName);
		} catch (IOException e) {
			log.info("Could not retrieve data from " + fileName);
			log.info(e.getMessage());
		}

		// 0 durch ? ersetzen, um für die Auswertung nur die Waren zu
		// berücksichtigen, die gekauft wurden
		NumericCleaner nc = new NumericCleaner();
		nc.setMinThreshold(1.0); // Schwellwert auf 1 setzen
		nc.setMinDefault(Double.NaN); // alle Werte unterhalb des Schwellwertes sollen durch "?" ersetzt werden
		try {
			nc.setInputFormat(alleDaten);  // Zielinstanz setzen
		} catch (Exception e) {
			log.info("Could not set InputFormat for filter for " + fileName);
			log.info(e.getMessage());
		}
		try {
			alleDaten = Filter.useFilter(alleDaten, nc);  // Filter anwenden
			log.info("Successfully applied filter to data from " + fileName);
		} catch (Exception e) {
			log.info("Could not apply filter to " + fileName);
			log.info(e.getMessage());
		}
	}
	
	/**
	 * Durchsucht die Wekainstanz nach dem Attribut "<i>Einkaufstag</i> und summiert für jeden Wert dessen den Wert von "<i>Einkaufssumme</i>" auf.
	 * @return Alle auftrtenden Werte von "<i>Einkaufstag</i>", geordnet nach den jeweiligen Summen von "<i>Einkaufssumme</i>"
	 */
	public LinkedHashMap<String, Integer> umsatzProTag() {
		// Instanz verkuerzen
		Instances nurTagundUmsatz = reduceToAttributes(alleDaten, alleDaten.attribute("Einkaufstag"), alleDaten.attribute("Einkaufssumme"));
		
		nurTagundUmsatz.sort(0);
		
		//testWriteArff(nurTagundUmsatz);

		Map<String, Integer> tagUmsatzMap = new HashMap<String, Integer>();
		int sum = 0;
		for (int i = 0; i < nurTagundUmsatz.size(); i++) {	//in der Schleife alle Instanzen für ein Attribut durchlaufen 

			if (i == (nurTagundUmsatz.size() - 1)) {
				String[] str1 = nurTagundUmsatz.get(i).toString().split(",");
				String[] str2 = nurTagundUmsatz.get(i - 1).toString().split(",");
				if (str1[1].equals("?")) { // machen die "?" zu "0"
					str1[1] = "0";
				}
				if (str2[1].equals("?")) {
					str2[1] = "0";
				}

				// System.out.println(str1[1]);
				if (str1[0].equals(str2[0])) {
					sum = sum + Integer.parseInt(str1[1]);
					tagUmsatzMap.put(str1[0], sum);
				}

			} else {

				String[] str1 = nurTagundUmsatz.get(i).toString().split(",");
				String[] str2 = nurTagundUmsatz.get(i + 1).toString().split(",");

				if (str1[1].equals("?")) { // machen die "?" zu "0"
					str1[1] = "0";
				}
				if (str2[1].equals("?")) {
					str2[1] = "0";
				}
				if (str1[0].equals(str2[0])) {
					sum = sum + Integer.parseInt(str1[1]);
				} else {
					tagUmsatzMap.put(str1[0], sum);
					sum = 0;
				}
			}

		}

		//Ordnen der Ergibnisse nach Value
		List<Map.Entry<String, Integer>> list1 = new ArrayList<Map.Entry<String, Integer>>(tagUmsatzMap.entrySet());
		Collections.sort(list1, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});
		
		LinkedHashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
		Iterator<Map.Entry<String, Integer>> iter1 = list1.iterator();
		
		while (iter1.hasNext()) {
            Map.Entry<String, Integer> entry = iter1.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            result.put(key.toString(), Integer.parseInt(val.toString()));
        }
		
		return result;
	}
	
	
	/**
	 * Durchsucht die Wekainstanz nach dem Attribut "<i>Einkaufsuhrzeit</i> und summiert für jeden Wert dessen den Wert von "<i>Einkaufssumme</i>" auf.
	 * @return Alle auftrtenden Werte von "<i>Einkaufsuhrzeit</i>", geordnet nach den jeweiligen Summen von "<i>Einkaufssumme</i>"
	 */
	public LinkedHashMap<String, Integer> umsatzProZeit() {
		//Instanz verkuerzen
		Instances nurZeitundUmsatz = reduceToAttributes(alleDaten, alleDaten.attribute("Einkaufsuhrzeit"), alleDaten.attribute("Einkaufssumme"));
				
		nurZeitundUmsatz.sort(0);
		Map<String, Integer> uhrumstaz = new HashMap<String, Integer>();
		int sum = 0;
		for (int i = 0; i < nurZeitundUmsatz.size(); i++) {

			if (i == (nurZeitundUmsatz.size() - 1)) {
				String[] str1 = nurZeitundUmsatz.get(i).toString().split(",");
				String[] str2 = nurZeitundUmsatz.get(i - 1).toString().split(",");
				if (str1[1].equals("?")) { // machen die "?" zu "0"
					str1[1] = "0";
				}
				if (str2[1].equals("?")) { // machen die "?" zu "0"
					str2[1] = "0";
				}

				if (str1[0].equals(str2[0])) {
					sum = sum + Integer.parseInt(str1[1]);
					uhrumstaz.put(str1[0], sum);
				}

			} else {

				String[] str1 = nurZeitundUmsatz.get(i).toString().split(",");
				String[] str2 = nurZeitundUmsatz.get(i + 1).toString().split(",");

				if (str1[1].equals("?")) {
					str1[1] = "0";
				}
				if (str2[1].equals("?")) {
					str2[1] = "0";
				}

				if (str1[0].equals(str2[0])) {
					sum = sum + Integer.parseInt(str1[1]);
				} else {
					uhrumstaz.put(str1[0], sum);
					sum = 0;
				}
			}

		}
		
		//Ordnen der Ergibnisse nach Value
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(uhrumstaz.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});
		
		LinkedHashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
		Iterator<Map.Entry<String, Integer>> iter1 = list.iterator();
		
		while (iter1.hasNext()) {
            Map.Entry<String, Integer> entry = iter1.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            result.put(key.toString(), Integer.parseInt(val.toString()));
        }
		
		return result;
	}
	
	/**
	 * Summiert die Werte aller Attribute hinter dem Attribute "<i>Einkaufssumme</i>" auf. Entsprechend sollten in der ursprünglichen .csv-Datei alle für Warengruppen stehenden Attribute an einem höheren Index als das Attribut "<i>Einkaussumme</i>" stehen.
	 * @return Die Summe der für die Warengruppen stehenden Attribute, geordnet nach Werthöhe
	 */
	public LinkedHashMap<String, Integer> umsatzProArtikel() {

        HashMap<String,Integer> map = new HashMap<>();
        
        //Anzahl der Attribute aus der zu analysierenden Datei
        int endPoint = alleDaten.numAttributes();	
        
        //Alle Warengruppen stehen hinter dem Attribut "Einkaufssumme", entsprechend ab dessen Index arbeiten
        int startPoint = alleDaten.attribute("Einkaufssumme").index() + 1;
        
        for(int j = startPoint; j < endPoint; j++) {		//für jedes Attribut das eine Warengruppe ist
            int tmp = 0;
            for (int i = 0; i < alleDaten.size(); i++) {
               tmp+= (int)(alleDaten.instance(i).value(j));	//den Wert aller Eintraege aufsummieren
            }
            map.put(alleDaten.attribute(j).name(),tmp);		//und in die Ergebnismap mit dem Namen der Warengruppe eintragen
        }
        
        //den Inhalt der Ergebnismap nach Value ordnen
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {//给Map依照value值排序
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        
        LinkedHashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
		Iterator<Map.Entry<String, Integer>> iter1 = list.iterator();
		
		while (iter1.hasNext()) {
            Map.Entry<String, Integer> entry = iter1.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            result.put(key.toString(), Integer.parseInt(val.toString()));
        }
        
        return result;	//geordnetes Ergebnis zurückgeben
    }
	
	/**
	 * Ruft <i>readDeckungsspanne</i> und <i>umsatzProArtikel</i> auf, und multipliziert die Einkaufsummen aus letzterem mit dem jeweiligen Deckungsbeitrag aus ersterem. Anders als die meisten Methoden in dieser Klasse
	 * ist die resultierende Map nicht nach den eigenen Werten geordnet, stattdessen ist die Reihenfolge aus <i>umsatzProArtikel</i> beibehalten um einen leichteren Vergleich zu erlauben.
	 * @see #readDeckungsspanne()
	 * @see #umsatzProArtikel()
	 * @return Die Warengruppen mit den jeweiligen Deckungspannen basierend auf den Einkaufssummen.
	 */
	public LinkedHashMap<String, Integer> deckungProArtikel() {
		Map<String, Integer> deckungsSpanne = readDeckungsspanne();			//Map<Warengruppe, Deckungsspanne> einlesen
		LinkedHashMap<String, Integer> artikelUmsatz = umsatzProArtikel();	//Map<Warengruppe, Einkaufssumme>
																			//benutze Methode, um Warengruppen gleich sortiert zu haben
		
		Iterator<Map.Entry<String, Integer>> iter = artikelUmsatz.entrySet().iterator();
		while (iter.hasNext()) {												//Für jeden Eintrag in der Liste
			Map.Entry<String, Integer> tmp = iter.next();
			int multiplied = tmp.getValue() * deckungsSpanne.get(tmp.getKey());	//Einkaufssumme mit Deckungsbetrag multiplizieren
			tmp.setValue(multiplied);
		}
		
		return artikelUmsatz;
	}
	
	public ArrayList<HashMap<String, Integer>> kundenBasicInfo() throws Exception {

		// machen die instence kuerzer
		Instances nurSex = new Instances(alleDaten);
		for (int i = 0; i < 25; i++) {
			nurSex.deleteAttributeAt(1); // einzelnes Attribut rausnehmen
		}

		// nurSex.sort(0);
		HashMap<String, Integer> sex = new HashMap<String, Integer>();
		int m = 0;
		int f = 0;

		for (int i = 0; i < nurSex.size(); i++) {
			if (nurSex.get(i).toString().equals("m")) {
				m++;
			} else {
				f++;
			}
		}

		sex.put("Herr", m);
		sex.put("Frau", f);

		// --------------------------------------------------------------------
		Instances nurKinder = new Instances(alleDaten);
		for (int i = 0; i < 2; i++) {
			nurKinder.deleteAttributeAt(0); // einzelnes Attribut rausnehmen
		}
		for (int i = 0; i < 23; i++) {
			nurKinder.deleteAttributeAt(1); // einzelnes Attribut rausnehmen
		}

		HashMap<String, Integer> kinder = new HashMap<String, Integer>();
		int j = 0;
		int n = 0;

		for (int i = 0; i < nurKinder.size(); i++) {
			if (nurKinder.get(i).toString().equals("ja")) {
				j++;
			} else {
				n++;
			}
		}

		kinder.put("ja", j);
		kinder.put("nein", n);

		// --------------------------------------------------------------------
		Instances nurBeruf = new Instances(alleDaten);
		for (int i = 0; i < 4; i++) {
			nurBeruf.deleteAttributeAt(0); // einzelnes Attribut rausnehmen
		}
		for (int i = 0; i < 21; i++) {
			nurBeruf.deleteAttributeAt(1); // einzelnes Attribut rausnehmen
		}

		HashMap<String, Integer> beruf = new HashMap<String, Integer>();
		j = 0;
		n = 0;

		for (int i = 0; i < nurBeruf.size(); i++) {
			if (nurBeruf.get(i).toString().equals("ja")) {
				j++;
			} else {
				n++;
			}
		}

		beruf.put("ja", j);
		beruf.put("nein", n);

		// --------------------------------------------------------------------
		Instances nurFami = new Instances(alleDaten);
		for (int i = 0; i < 3; i++) {
			nurFami.deleteAttributeAt(0); // einzelnes Attribut rausnehmen
		}
		for (int i = 0; i < 22; i++) {
			nurFami.deleteAttributeAt(1); // einzelnes Attribut rausnehmen
		}

		HashMap<String, Integer> fami = new HashMap<String, Integer>();
		j = 0;
		n = 0;

		for (int i = 0; i < nurFami.size(); i++) {
			if (nurFami.get(i).toString().equals("ledig")) {
				j++;
			} else {
				n++;
			}
		}

		fami.put("ledig", j);
		fami.put("Partnerschaft", n);
		
		// --------------------------------------------------------------------
		  Instances Haus = new Instances(alleDaten);
		  for (int i = 0; i < 8; i++) {
		   Haus.deleteAttributeAt(0); // einzelnes Attribut rausnehmen
		  }
		  for (int i = 0; i < 17; i++) {
		   Haus.deleteAttributeAt(1); // einzelnes Attribut rausnehmen
		  }

		  HashMap<String, Integer> haus = new HashMap<String, Integer>();
		  int a = 0;
		  int b = 0;
		  int c = 0;
		  int d = 0;
		  j = 0;

		  for (int i = 0; i < Haus.size(); i++) {
		   if (Haus.get(i).toString().equals("<1000")) {
		    a++;
		   } else if (Haus.get(i).toString().equals("1000-<2000")) {
		    b++;
		   } else if (Haus.get(i).toString().equals("2000-<3200")) {
		    c++;
		   } else if (Haus.get(i).toString().equals("3200-<4500")) {
		    d++;
		   } else {
		    j++;
		   }
		  }

		  haus.put("<1000", a);
		  haus.put("1000-<2000", b);
		  haus.put("2000-<3200", c);
		  haus.put("3200-<4500", d);
		  haus.put(">4500", j);

		  // --------------------------------------------------------------------
		  Instances Skunde = new Instances(alleDaten);
		  for (int i = 0; i < 9; i++) {
		   Skunde.deleteAttributeAt(0); // einzelnes Attribut rausnehmen
		  }
		  for (int i = 0; i < 16; i++) {
		   Skunde.deleteAttributeAt(1); // einzelnes Attribut rausnehmen
		  }

		  HashMap<String, Integer> sk = new HashMap<String, Integer>();
		  a = 0;
		  b = 0;
		  c = 0;

		  for (int i = 0; i < Skunde.size(); i++) {
		   if (Skunde.get(i).toString().equals("0")) {
		    a++;
		   } else if (Skunde.get(i).toString().equals("'> 5'")) {
		    c++;
		   } else {
		    b++;
		   }
		  }

		  sk.put("0", a);
		  sk.put("1 bis 5", b);
		  sk.put("> 5", c);
		  // --------------------------------------------------------------------
		// --------------------------------------------------------------------
		  Instances nuralt = new Instances(alleDaten);
		  for (int i = 0; i < 1; i++) {
		   nuralt.deleteAttributeAt(0); // einzelnes Attribut rausnehmen
		  }
		  for (int i = 0; i < 24; i++) {
		   nuralt.deleteAttributeAt(1); // einzelnes Attribut rausnehmen
		  }

		  HashMap<String, Integer> alt = new HashMap<String, Integer>();
		   a = 0;
		   b = 0;
		   c = 0;
		   d = 0;

		  for (int i = 0; i < nuralt.size(); i++) {
		   if (nuralt.get(i).toString().equals("18-30")) {
		    a++;
		   } else if (nuralt.get(i).toString().equals("31-40")) {
		    b++;
		   } else if (nuralt.get(i).toString().equals("41-50")) {
		    c++;
		   } else {
		    d++;
		   }
		  }

		  alt.put("18-30", a);
		  alt.put("31-40", b);
		  alt.put("41-50", c);
		  alt.put(">60", d);

		  // --------------------------------------------------------------------
		  Instances nurWO = new Instances(alleDaten);
		  for (int i = 0; i < 7; i++) {
		   nurWO.deleteAttributeAt(0); // einzelnes Attribut rausnehmen
		  }
		  for (int i = 0; i < 18; i++) {
		   nurWO.deleteAttributeAt(1); // einzelnes Attribut rausnehmen
		  }

		  HashMap<String, Integer> WO = new HashMap<String, Integer>();
		  a = 0;
		  b = 0;
		  c = 0;

		  for (int i = 0; i < nurWO.size(); i++) {
			  
		   if (nurWO.get(i).toString().equals("'> 25 km'")) {
		    c++;
		   } else if (nurWO.get(i).toString().equals("'10 - 25 km'")) {
		    b++;
		   } else {
		    a++;
		   }
		  }

		  WO.put("< 10 km", a);
		  WO.put("10 - 25 km", b);
		  WO.put("> 25 km", c);
		/* ***************************************************************************/
		/* ***************************************************************************/
		/* ***************************************************************************/
		ArrayList<HashMap<String,Integer>> result = new ArrayList<>();
		result.add(sex);
		result.add(kinder);
		result.add(beruf);
		result.add(fami);
		result.add(haus);
		result.add(sk);
		result.add(alt);
		result.add(WO);
		//System.out.print(result);
		return result;
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
	
	/**
	 * Liest die den Warengruppen zugehörigen Deckunsspannen aus der Datei "deckungsspanne.txt" aus. In dieser Datei müssen die Deckungsspannen in Zeilen aufgeteilt sein damit diese MEthode funktioniert.
	 * @return Die Warengruppen als Schlüssel und die zugehörigen Deckunsspannen als Werte in einer Map
	 */
	public Map <String,Integer> readDeckungsspanne()
    {

        FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(PathResultFolder.getResultFolder() + "deckungsspanne.txt");
		} catch (FileNotFoundException e) {
			log.info("Unable to load Deckungsspanne from file");
			return null;
		}
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        
        HashMap<String,String> map=new HashMap<>();
        Map <String,Integer> pr = new HashMap<>();
        
        String str = null;
        try {
			while((str = bufferedReader.readLine()) != null)
			{
			    String [] data = str.split(":");	//trenne Warengruppe von ihrer Deckungsspanne
			    map.put(data[0],data[1]);		//in Map speichern
			}
			log.info("File read successfully");
		} catch (IOException e) {
			log.info("Failed to read line from file");
		}
        
        for(Map.Entry<String,String> entry: map.entrySet() ) {
        	 String s = entry.getValue().toString().replace(" ", "");	//Leerzeichen entfernen
             pr.put(entry.getKey(), Integer.parseInt(s));				//Zu Integer konvertieren und in Map speichern
		}

        try {
			inputStream.close();
			bufferedReader.close();
			log.info("Inputreaders closed successfully");
		} catch (IOException e) {
			log.info("Unable to close Inputreaders");
		}
        return pr;		//Map mit Integerwerten zurückgeben

    }
	
	
	private Instances reduceToAttributes(Instances input, Attribute... attributes) {
		Instances result = new Instances(input);
		Remove removalFilter = new Remove();
		removalFilter.setInvertSelection(true);	//Instead of all selected Attributes being removed, all Attributes that are NOT selected are removed.
		
		String targetNames="";
		int[] targetAttributes = new int[attributes.length]; 
		for(int i = 0; i < attributes.length; i++) {
			targetAttributes[i] = attributes[i].index();
			targetNames += " " + attributes[i].name();
		}
		removalFilter.setAttributeIndicesArray(targetAttributes);
		
		try {
			removalFilter.setInputFormat(result);
			log.info("Successfully set InputFormat of input");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			result = Filter.useFilter(input, removalFilter);		//Filter anwenden
			log.info("Reduced dataset to target attributes:" + targetNames);
		} catch (Exception e) {
			log.info("Unable to reduce dataset to target attributes:" + targetNames);
		}
		//log.info(input.toString());		//for testing purposes
		//log.info(result.toString());		//for testing purposes
		return result;
	}
	
	/**
	 * Speichert eine Wekainstanz als .arff-Datei. Nur zu Testzwecken, nicht im Anwendungsverlauf verwendet.
	 * @param test Die Wekainstanz, die als .arff-Datei gespeichert werden soll
	 */
	private void testWriteArff(Instances test) {	//for testing purposes
		ArffSaver saver = new ArffSaver();
		saver.setInstances(test);
		try {
			saver.setFile(new File(PathResultFolder.getResultFolder() + "testArffFile.arff"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			saver.writeBatch();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
