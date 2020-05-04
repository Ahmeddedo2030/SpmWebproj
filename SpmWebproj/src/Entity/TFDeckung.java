package Entity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import weka.classifiers.rules.ZeroR;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericCleaner;

public class TFDeckung {
	public ArrayList<String> findMaximum(Instances daten, int index) throws Exception {
		String[] max;
		//System.out.print(daten.toString());
		ZeroR za = new ZeroR(); // wekafunktion

		daten.setClass(daten.attribute(index)); // Attribut dessen Maximum
					
		readText a =new readText();
    	Map<String, Integer> b=a.ReadPrice("C:/Users/小彭子hhh/AppData/Roaming/SPB_Data/git/SpmWebproj/SpmWebproj/WebContent/WEB-INF/upload/deckungsspanne.txt");
		
		//System.out.println("shishi"+"  "+daten.instance(10).classValue());
		//System.out.println("daxiao"+"  "+daten.size());
		//System.out.println("attdechangdu"+"  "+daten.numAttributes());
		//System.out.println("shuxingzhi"+"  "+daten.attribute(0));
		HashMap<String,Integer> map = new HashMap<>();
		Map<Integer,Float> map1 = new HashMap<>();
		for(int j =11;j<daten.numAttributes();j++) {
	            int tmp = 0;
	            for (int i = 0; i < daten.size(); i++) {//得到行数alleDaten.numInstances();
	               tmp+= (int)(daten.instance(i).value(j));
	            }
	            map.put(daten.attribute(j).name(),tmp);
	        }
		for(int i = 0;i<map.size();i++) {
			//System.out.println("duiyingdezhi:    "+b);
			//System.out.println(daten.attribute(i+11).name()+":    "+(float)b[i]/(b[i]+100));
			float at = map.get(daten.attribute(i+11).name());
			at=at*((float)b.get(daten.attribute(i+11).name())/(b.get(daten.attribute(i+11).name())+100));
			
			//System.out.println(at);
			map1.put(i+11, at);
			
		}
		Map <String, Float>mapsort= new HashMap<>();
		float f = 0;
		float flop = 100000;
		for(Map.Entry<Integer, Float> entry: map1.entrySet() ) {//f换成了最大值，flop换成了最小值
			if(entry.getValue()>f)
				f=entry.getValue();
			if(entry.getValue()<flop)
				flop=entry.getValue();
		}
		ArrayList<String> result = new ArrayList<>();//result[0] top,result[1] flop
		for(Map.Entry<Integer, Float> entry: map1.entrySet() ) {
			if(entry.getValue()==f)
				//mapsort.put(daten.attribute(entry.getKey()).name(), entry.getValue());
				//ss = "Top Artikel:"+ daten.attribute(entry.getKey()).name()+" Deckungsbeitrag:"+entry.getValue()+"\n";
				result.add(daten.attribute(entry.getKey()).name());
			if(entry.getValue()==flop)
				//System.out.println("最小的商品 "+ daten.attribute(entry.getKey()).name()+entry.getValue());
				result.add(daten.attribute(entry.getKey()).name());
		}
		


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
	
	
	public ArrayList<String> TFDeckung (String path) throws Exception{
	
		
		TFDeckung a = new TFDeckung();
		
		Instances alleDaten;
		
		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(path));
		alleDaten = loader.getDataSet();
		
        return a.findMaximum(alleDaten, 1);
		
		
	}
}
