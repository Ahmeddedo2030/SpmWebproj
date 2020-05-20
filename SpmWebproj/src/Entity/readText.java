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
        Map <String,Integer> pr = new LinkedHashMap<>();
        String str = null;
        int i = 0 , size = 0;
        while((str = bufferedReader.readLine()) != null)
        {
            //System.out.println(str);
        	size++;
            String [] data=str.split(":");
            map.put(data[0],data[1]);
        }
       
        for(Map.Entry<String,String> entry: map.entrySet() ) {
        	 String s = entry.getValue().toString().replace(" ", "");
             pr.put(entry.getKey(), Integer.parseInt(s));
		}
		
        inputStream.close();
        bufferedReader.close();
        return pr;

    }
    
}