package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import weka.associations.*;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.*;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericCleaner;
import weka.filters.unsupervised.attribute.Remove;
import weka.classifiers.rules.*;

public class AnalysisTool {
	private Instances alleDaten;
	private String fileName;
	private static final Logger log	= Logger.getLogger(AnalysisTool.class.getName());
	
	public AnalysisResult doAnalysis(File inputFile) {
		AnalysisResult result = new AnalysisResult();
		long startTime = System.nanoTime();
		datenLaden(inputFile);
		result.setSourceFileName(fileName);
		result.setWochentagUmsatz(findBestDay());
		result.setUhrzeitUmsatz(findBestTime());
		result.setArtikelUmsatz(ordnenNachUmsatz());
		result.setArtikelDeckungsbetrag(findMaximum());
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		log.info("Analysis in " + timeElapsed / 1000000 + " milliseconds");
		return result;
	}
	
	public AnalysisResult createAnalysisResult(File inputFile) {
		return null;
	}
	
	public void datenLaden(File inputFile) {
		// Übergebene CSV-Datei laden
		fileName = inputFile.getName();
		CSVLoader loader = new CSVLoader();
		try {
			loader.setSource(inputFile);
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
	
	private List<Map.Entry<String, Integer>> findBestDay() {

		/*
		 * ******************* Start der Auswertungen ***********************
		 */
		// machen die instence kuerzer
		Instances nurTagundUmsatz = reduceToAttributes(alleDaten, alleDaten.attribute("Einkaufstag"), alleDaten.attribute("Einkaufssumme"));
		
		nurTagundUmsatz.sort(0);
		
		//testWriteArff(nurTagundUmsatz);

		Map<String, Integer> tagumstaz = new HashMap<String, Integer>();
		int sum = 0;
		for (int i = 0; i < nurTagundUmsatz.size(); i++) {

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
					tagumstaz.put(str1[0], sum);
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
					tagumstaz.put(str1[0], sum);
					sum = 0;
				}
			}

		}

		// ordnen die Ergibnisse
		List<Map.Entry<String, Integer>> list1 = new ArrayList<Map.Entry<String, Integer>>(tagumstaz.entrySet());
		Collections.sort(list1, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});

		/*System.out.println(" Top Einkaufstag : " + list1.get(0).getKey() + "mit Umsatz :" + list1.get(0).getValue()
				+ "\n Flop Einkaufstag : " + list1.get(list1.size() - 1).getKey() + "mit Umsatz :"
				+ list1.get(list1.size() - 1).getValue());*/
		
		return list1;
	}
	
	private List<Map.Entry<String, Integer>> findBestTime() {
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
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(uhrumstaz.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});
		/*System.out.println(" Top Einkaufsuhrzeit : " + list.get(0).getKey() + "mit Umsatz :" + list.get(0).getValue()
				+ "\n Flop Einkaufsuhrzeit : " + list.get(list.size() - 1).getKey() + "mit Umsatz :"
				+ list.get(list.size() - 1).getValue());*/
		return list;
	}
	
	private List<Map.Entry<String, Integer>> ordnenNachUmsatz() {

        HashMap<String,Integer> map = new HashMap<>();
        int num = alleDaten.numAttributes();	//Anzahl der Attribute
        for(int j =11;j<num;j++) {
            int tmp = 0;
            for (int i = 0; i < alleDaten.size(); i++) {//得到行数alleDaten.numInstances();
               tmp+= (int)(alleDaten.instance(i).value(j));
            }
            map.put(alleDaten.attribute(j).name(),tmp);
        }
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {//给Map依照value值排序
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        return list;
    }
	
	public List<Map.Entry<String, Integer>> findMaximum() {
		Instances daten = new Instances(alleDaten);		//alleDaten kopieren
		//System.out.print(daten.toString());
		//ZeroR za = new ZeroR(); // wekafunktion
		
		//daten.setClass(daten.attribute(index)); // Attribut dessen Maximum
					
		Map<String, Integer> deckungsspanne= readDeckungsspanne();	//Map<Warengruppe, Deckungsspanne> einlesen
		
		//System.out.println("shishi"+"  "+daten.instance(10).classValue());
		//System.out.println("daxiao"+"  "+daten.size());
		//System.out.println("attdechangdu"+"  "+daten.numAttributes());
		//System.out.println("shuxingzhi"+"  "+daten.attribute(0));
		
		
		HashMap<String,Integer> map = new HashMap<>();
		//Map<Integer,Float> map1 = new HashMap<>();
		
		for(int j = 11;j<daten.numAttributes();j++) {		//für jedes Attribut hinter der Einkaufssumme
			int summe = 0;
			for (int i = 0; i < daten.size(); i++) {//得到行数alleDaten.numInstances();
				summe+= (int)(daten.instance(i).value(j));		//Instanzen lesen und Werte des Attributs summieren
			}
			map.put(daten.attribute(j).name(),summe);		//Attributname und Summe in Map speichern
		}
		
		List<Map.Entry<String, Integer>> mapEntryList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		
		ListIterator<Map.Entry<String, Integer>> iter = mapEntryList.listIterator();
		while (iter.hasNext()) {		//Für jeden Eintrag in der Liste
			Map.Entry<String, Integer> tmp = iter.next();
			int absatz = tmp.getValue();
			int deckung = deckungsspanne.get(tmp.getKey());
			tmp.setValue(absatz*deckung);	//multipliziere Umsatz mit Deckungsspanne
			iter.set(tmp);					//Speicher wieder in der Liste
		}
		
		return mapEntryList;
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
	
	public   Map <String,Integer> readDeckungsspanne()
    {

        //BufferedReader	ÊÇ¿ÉÒÔ°´ÐÐ¶ÁÈ¡ÎÄ¼þ
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
			    String [] data=str.split(":");	//trenne Warengruppe von ihrer Deckungsspanne
			    map.put(data[0],data[1]);		//in Map speichern
			}
			log.info("File read successfully");
		} catch (IOException e) {
			log.info("Failed to read line from file");
		}
        
        for(Map.Entry<String,String> entry: map.entrySet() ) {
        	 String s = entry.getValue().toString().replace(" ", "");	//Bereinigen
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
			targetAttributes[i]=attributes[i].index();
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
