package Entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Deprecated
public class bekommen {
	//��all artical umsatz �ķ�����¼�浽�ĵ���
	public static String put_AU(LinkedHashMap<String,Integer>  a) throws IOException {
		String ergebnis="all-";
		for(Map.Entry<String,Integer> entry : a.entrySet()) {
			ergebnis+=entry.getKey()+":"+entry.getValue()+";";
		}	
		ergebnis+="/";
		return ergebnis;
		
	}
	//��Artikel Beitrag�ķ�����¼�浽�ĵ���
	public static String put_AB(LinkedHashMap<String, Float> a) throws IOException {
		String ergebnis="Beitrag-";
		for(Map.Entry<String,Float> entry : a.entrySet()) {
			ergebnis+=entry.getKey()+":"+entry.getValue()+";";
		}		
		ergebnis+="/";
		return ergebnis;
	}
	
	//�����ʱ�����������ķ�����¼�浽�ĵ���
	public static String put_ZT(ArrayList<LinkedHashMap<String,Integer>> a) throws IOException {
		String ergebnis="Z_T#Zeit,";
		for(Entry<String, Integer> entry : a.get(0).entrySet()) {
			ergebnis+=entry.getKey()+":"+entry.getValue()+";";
		}	
		ergebnis+=",Tag,";
		for(Entry<String, Integer> entry : a.get(1).entrySet()) {
			ergebnis+=entry.getKey()+":"+entry.getValue()+";";
		}
		ergebnis+="/";
		return ergebnis;
		
	}
	public void put(LinkedHashMap<String,Integer>a,LinkedHashMap<String, Float> b, ArrayList<LinkedHashMap<String,Integer>> c) throws IOException {
		String ergebnis="";
		ergebnis=put_AU(a)+put_AB(b)+put_ZT(c);
		
		speichern s = new speichern();
		s.readFile();
		s.writeFile(ergebnis);
	}
	
	public LinkedHashMap<String,Integer> get_all_umsatz(int zeile) {
		//zeile*=2;
		String pathname="speicher.txt";
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
			   if(zeile>=l.size()) {//ѡ���������������ļ��еĳ��ȣ��ͷ��
				   br.close();
				   return null;
			   }
			   String []s=(l.get(zeile)).split("/"); //����������
			  // System.out.println("s�ĳ��ȣ�"+s.length+"  "+s[0]);
			   for(int i=0;i<s.length;i++) {
				   String []teil=s[i].split("-");
				   //System.out.println("teil�ĳ��ȣ�"+teil.length+" "+teil[0]);//+","+teil[1]+","+teil[2]);
				   if(teil[0].equals("all")) {
					   String []m = teil[1].split(";");
					   //System.out.println("m�ĳ��ȣ�"+m.length+"   "+m[0]+","+m[1]);
					   for(int j=0;j<m.length;j++) {
						   String []mm=m[j].split(":");
						  // System.out.println(mm[0]+"    "+mm[1]);
						   
						   if(isInt(mm[1]))
						     All.put(mm[0], Integer.parseInt(mm[1]));
						   
					   }
					   br.close();
					   return All;
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
	public LinkedHashMap<String,Float> get_AB(int zeile) {
		//zeile*=2;
		String pathname="speicher.txt";
		List <String> l = new ArrayList<>(); 
	    LinkedHashMap<String,Float> AB = new LinkedHashMap<>();
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
			   }
			   String []s=(l.get(zeile)).split("/"); //����������
			   //System.out.println("s�ĳ��ȣ�"+s.length+"  "+s[0]);
			   for(int i=0;i<s.length;i++) {
				   String []teil=s[i].split("-");
				   //System.out.println("teil�ĳ��ȣ�"+teil.length+" "+teil[0]);//+","+teil[1]+","+teil[2]);
				   if(teil[0].equals("Beitrag")) {
					   String []m = teil[1].split(";");
					   //System.out.println("m�ĳ��ȣ�"+m.length+"   "+m[0]+","+m[1]);
					   for(int j=0;j<m.length;j++) {
						   String []mm=m[j].split(":");
						   //System.out.println(mm[0]+"    "+mm[1]);
						   
						   if(isInt(mm[1])&&mm[1].indexOf('.')==-1);
						     AB.put(mm[0], Float.parseFloat(mm[1]));
						   
					   }
					   br.close();
					   return AB;
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
	public ArrayList<LinkedHashMap<String,Integer>> get_ZT(int zeile) {
		//zeile*=2;
		String pathname="speicher.txt";
		List <String> l = new ArrayList<>(); 
		ArrayList <LinkedHashMap<String,Integer>> zt = new ArrayList<>();
	    LinkedHashMap<String,Integer> z = new LinkedHashMap<>();
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
			   }
			   String []s=(l.get(zeile)).split("/"); //����������
			   //System.out.println("s�ĳ��ȣ�"+s.length+"  "+s[0]);
			   for(int i=0;i<s.length;i++) {
				   String []teil=s[i].split("#");
				  // System.out.println("teil�ĳ��ȣ�"+teil.length+" "+teil[0]);//+","+teil[1]+","+teil[2]);
				   if(teil[0].equals("Z_T")) {
					   
					   String []sp= teil[1].split(",");
					  // System.out.println("m�ĳ��ȣ�"+sp.length+"   "+sp[0]+","+sp[1]);
					   if(sp.length==4) {
						   String []m1 = sp[1].split(";");
						//   System.out.println("m�ĳ��ȣ�"+m1.length+"   "+m1[0]+","+m1[1]);
						   for(int j=0;j<m1.length;j++) {
							   String []mm=m1[j].split(":");
							   //System.out.println(mm[0]+"    "+mm[1]);
							   
							   if(isInt(mm[1]));
							     z.put(mm[0], Integer.parseInt(mm[1]));
							   
						   }
						   zt.add(z);
						   String []m2 = sp[3].split(";");
						   //System.out.println("m�ĳ��ȣ�"+m.length+"   "+m[0]+","+m[1]);
						   for(int j=0;j<m2.length;j++) {
							   String []mm=m2[j].split(":");
							   //System.out.println(mm[0]+"    "+mm[1]);
							   
							   if(isInt(mm[1]));
							     t.put(mm[0], Integer.parseInt(mm[1]));
							   
						   }
						   zt.add(t);
					   }
					   br.close();
					   return zt;
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