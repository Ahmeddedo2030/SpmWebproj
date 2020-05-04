package Entity;
import weka.classifiers.rules.ZeroR;
import weka.core.Instance;
import weka.core.converters.CSVLoader;
import weka.core.Instances;
import java.io.File;
import java.util.*;

public class OrdnungNachUmsatz {
    public  ArrayList<String> OrdnungNachUmsatz(String path) throws Exception {

        // Eigenen Dateipfad eintragen, nicht meinen nehmen ;-)
        //String path = "C:/Users/Hilke Fasse/Documents/SS20/SPM/weka/";
        Instances alleDaten;

        // CSV-Datei laden
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(path));
        alleDaten = loader.getDataSet();
        //Instances b;
      //  b.attribute(i).value(j);  //�õ�attribute����Ķ�Ӧֵ
        //System.out.println(alleDaten.instance(0));//��õ���һ�е�����
        ArrayList<String> list1 = new ArrayList<>();
        HashMap<String,Integer> map = new HashMap<>();
       // ArrayList<String[]> list = new ArrayList<>();
        /*for(int i =0;i<alleDaten.size();i++){
            list.add(alleDaten.instance(i).toString().split(","));
        }*/
        int num = alleDaten.numAttributes();
        for(int j =11;j<num;j++) {
            int tmp = 0;
            for (int i = 0; i < alleDaten.size(); i++) {//�õ�����alleDaten.numInstances();
               tmp+= (int)(alleDaten.instance(i).value(j));
            }
            map.put(alleDaten.attribute(j).name(),tmp);
        }
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {//��Map����valueֵ����
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        //System.out.println(list);//�õ�������Umsatz�ź�����list.list<Hashmap<String,Integer>>���Եõ�map
        Iterator iter = list.iterator();
        ArrayList<String> result =new  ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            result.add(key.toString());
            //System.out.println(key);
            //Object val = entry.getValue();
            // System.out.println(val);
        }
       //System.out.println(result);
        return result;
    }
    

}