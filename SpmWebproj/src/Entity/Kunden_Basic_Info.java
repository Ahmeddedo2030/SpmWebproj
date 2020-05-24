package Entity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import weka.core.Instances;
import weka.core.converters.CSVLoader;


public class Kunden_Basic_Info {

	public ArrayList<HashMap<String, Integer>> Basic_Info(String path) throws Exception {
		Instances alleDaten;
		// CSV-Datei laden
		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(path));
		alleDaten = loader.getDataSet();
		/* ***************************************************************************/
		/* ***************************************************************************/
		/* ***************************************************************************/
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
}
