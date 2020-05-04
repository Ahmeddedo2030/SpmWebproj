package Entity;
import java.io.*;
import java.util.*;


public class readText{

    public   Map <String,Integer> ReadPrice(String file) throws IOException
    {

        //BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        HashMap<String,String> map=new HashMap<>();
        Map <String,Integer> pr = new HashMap<>();
        String str = null;
        int i = 0 , size = 0;
        while((str = bufferedReader.readLine()) != null)
        {
            //System.out.println(str);
        	size++;
            String [] data=str.split(":");
            map.put(data[0],data[1]);
        }
        int[] price = new int[size];
        for(Map.Entry<String,String> entry: map.entrySet() ) {
        	 String s = entry.getValue().toString().replace(" ", "");
             pr.put(entry.getKey(), Integer.parseInt(s));
		}
		
        
        
       /* 
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
        Map.Entry entry = (Map.Entry) iter.next();
        //Object key = entry.getKey();
           // System.out.println(key);
        Object val = entry.getValue();
       
        price[i]=Integer.parseInt(s);
        System.out.println(price[i]);
        i++;
        
}*/

        //System.out.println(map);    
        //close
        inputStream.close();
        bufferedReader.close();
        return pr;

    }
    
}