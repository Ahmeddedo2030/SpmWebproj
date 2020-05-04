package Entity;
import java.io.*;
import java.util.*;
import weka.associations.*;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.*;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.*;
import weka.classifiers.rules.*;

public class FindBestDayandTime {

	public ArrayList<ArrayList<String>> FindBesttagUndUhrzeit(String path) throws Exception {
		// Eigenen Dateipfad eintragen
		//String path = "C:/Users/lenovo/eclipse-workspace/SPM/src/weka/";
		//String roh = path + "kd100.csv";

		Instances alleDaten, nurWaren, nurKunden;

		FindBestDayandTime dt = new FindBestDayandTime();

		// CSV-Datei laden
		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(path));
		alleDaten = loader.getDataSet();

		// 0 durch ? ersetzen, um für die Auswertung nur die Waren zu
		// berücksichtigen, die gekauft wurden
		NumericCleaner nc = new NumericCleaner();
		nc.setMinThreshold(1.0); // Schwellwert auf 1 setzen
		nc.setMinDefault(Double.NaN); // alles unter 1 durch ? ersetzen
		nc.setInputFormat(alleDaten);
		alleDaten = Filter.useFilter(alleDaten, nc); // Filter anwenden


		/*
		 * ******************* Start der Auswertungen ***********************
		 */
		// machen die instence kuerzer
		Instances nurTagundUmsatz = new Instances(alleDaten);
		for (int i = 0; i < 15; i++) {
			nurTagundUmsatz.deleteAttributeAt(11); // einzelnes Attribut rausnehmen
		}
		for (int i = 0; i < 5; i++) {
			nurTagundUmsatz.deleteAttributeAt(0); // einzelnes Attribut rausnehmen
		}
		for (int i = 0; i < 4; i++) {
			nurTagundUmsatz.deleteAttributeAt(1); // einzelnes Attribut rausnehmen
		}
		nurTagundUmsatz.sort(0);

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
					// System.out.println("Ö´ÐÐµ½ÁË");
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

		// machen die instence kuerzer
		Instances nurZeitundUmsatz = new Instances(alleDaten);
		for (int i = 0; i < 15; i++) {
			nurZeitundUmsatz.deleteAttributeAt(11); // einzelnes Attribut rausnehmen
		}
		for (int i = 0; i < 6; i++) {
			nurZeitundUmsatz.deleteAttributeAt(0); // einzelnes Attribut rausnehmen
		}
		for (int i = 0; i < 3; i++) {
			nurZeitundUmsatz.deleteAttributeAt(1); // einzelnes Attribut rausnehmen
		}
		nurZeitundUmsatz.sort(0);
		Map<String, Integer> uhrumstaz = new HashMap<String, Integer>();
		sum = 0;
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
		// ordnen die Ergibnisse
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(uhrumstaz.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});
		List<Map.Entry<String, Integer>> list1 = new ArrayList<Map.Entry<String, Integer>>(tagumstaz.entrySet());
		Collections.sort(list1, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        
        ArrayList<String> List1 = new ArrayList<>();
        ArrayList<String> List2 = new ArrayList<>();
		// output
		/*System.out.println(" Top Einkaufsuhrzeit : " + list.get(0).getKey() + "mit Umsatz :" + list.get(0).getValue()
				+ "\n Flop Einkaufsuhrzeit : " + list.get(list.size() - 1).getKey() + "mit Umsatz :"
				+ list.get(list.size() - 1).getValue());

		System.out.println(" Top Einkaufstag : " + list1.get(0).getKey() + "mit Umsatz :" + list1.get(0).getValue()
				+ "\n Flop Einkaufstag : " + list1.get(list1.size() - 1).getKey() + "mit Umsatz :"
				+ list1.get(list1.size() - 1).getValue());*/

		// System.out.println( nurTagundUmsatz.get(99));

		// System.out.println(nurZeitundUmsatz.size());

		// System.out.println("Haeufigste Einkaufstag: " +
		// dt.findMaximum(nurTagundUmsatz, 0));
		// System.out.println("Haeufigste Einkaufsuhrzeit: " +
		// dt.findMaximum(nurZeitundUmsatz, 0));
		List1.add(list.get(0).getKey().toString());
		List1.add(list.get(list.size() - 1).getKey().toString());
		List2.add(list1.get(0).getKey().toString());
		List2.add(list1.get(list1.size() - 1).getKey().toString());
		result.add(List1);
		result.add(List2);
		return result;
		
	}


}
