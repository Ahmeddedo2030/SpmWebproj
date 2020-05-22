package Entity;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

@Deprecated
public class TFDeckung {
	public Map <String, Float> findMaximum(Instances daten, int index) throws Exception {

		daten.setClass(daten.attribute(index)); // Attribut dessen Maximum
					
		readText a =new readText();
    	Map<String, Integer> b=a.ReadPrice("C:/Users/小彭子hhh/Desktop/SpmWebproj/WebContent/WEB-INF/upload/deckungsspanne.txt");
		
		
		HashMap<String,Integer> map = new HashMap<>();
		HashMap<String,Float> map1 = new HashMap<>();
		for(int j =11;j<daten.numAttributes();j++) {
	            int tmp = 0;
	            for (int i = 0; i < daten.size(); i++) {//得到行数alleDaten.numInstances();
	               tmp+= (int)(daten.instance(i).value(j));
	            }
	            map.put(daten.attribute(j).name(),tmp);
	        }
		
		for(int i = 0;i<map.size();i++) {
			float at = map.get(daten.attribute(i+11).name());
			at=at*((float)b.get(daten.attribute(i+11).name())/(b.get(daten.attribute(i+11).name())+100));
			map1.put(daten.attribute(i+11).name(), at);
			
		}
		
		Map <String, Float> result= new LinkedHashMap<>();//hashmap和treeMap是无序的，只有LinkedMap是有序的
		/*float f = 0;
		float flop = 100000;
		for(Map.Entry<Integer, Float> entry: map1.entrySet() ) {//f换成了最大值，flop换成了最小值
			if(entry.getValue()>f)
				f=entry.getValue();
			if(entry.getValue()<flop)
				flop=entry.getValue();
		}
		//ArrayList<String> result = new ArrayList<>();//result[0] top,result[1] flop
		for(Map.Entry<Integer, Float> entry: map1.entrySet() ) {
			if(entry.getValue()==f)
				//mapsort.put(daten.attribute(entry.getKey()).name(), entry.getValue());
				//ss = "Top Artikel:"+ daten.attribute(entry.getKey()).name()+" Deckungsbeitrag:"+entry.getValue()+"\n";
				result.put(daten.attribute(entry.getKey()).name(),entry.getValue());
			if(entry.getValue()==flop)
				//System.out.println("最小的商品 "+ daten.attribute(entry.getKey()).name()+entry.getValue());
				result.put(daten.attribute(entry.getKey()).name(),entry.getValue());
		}*/
		   ArrayList<Map.Entry<String, Float>> list = new ArrayList<Map.Entry<String, Float>>(map1.entrySet());
	        Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {//给Map依照value值排序
	            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
	                return (int)(o2.getValue() - o1.getValue());
	            }
	        });
	     //   System.out.println(list);
	        Iterator<Entry<String, Float>> iter = list.iterator();
	         while (iter.hasNext()) {
	        	 Map.Entry<String, Float>entry = iter.next();
	             Object key = entry.getKey();
	             Object val = entry.getValue();
	             result.put(key.toString(), Float.parseFloat(val.toString()));
	         }
	       //  System.out.println(result);
		return result;
	}

	
	public Map <String, Float> tFDeckung (String path) throws Exception{
	
		
		TFDeckung a = new TFDeckung();
		
		Instances alleDaten;
		
		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(path));
		alleDaten = loader.getDataSet();
		
        return a.findMaximum(alleDaten, 1);
		
		
	}
}