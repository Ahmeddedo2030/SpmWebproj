package Entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.AnalysisTool;
import main.PathResultFolder;

public class speichern {
	private static final Logger log	= Logger.getLogger(speichern.class.getName());
	
	private static List <String> l = new ArrayList<>(); 
	
	public boolean readFile() {
		String pathname = PathResultFolder.getResultFolder() + "speicher.txt";
		try{
			File file = new File(pathname);
			if(file.exists()) {
			   FileReader read = new FileReader(pathname);
			   BufferedReader br = new BufferedReader(read);
			   String line;
			   int i = 0;
			   while((line = br.readLine())!=null){
				   this.l.add(line);
				   i++;
			   }
			   log.info("Anzahl von Speichereintraegen: "+i);
			   br.close();
			   read.close();
			   if(i<=10)
				   return true;
			   return false;
			}else {
				log.info("Speicherdatei konnte nicht gefunden werden");
				return false;
			}
		}catch(IOException e) {
			   e.printStackTrace();
			   return false;
		}
	} 
	public void writeFile(String Ergebnis) throws IOException {
	  
	   String pathname = PathResultFolder.getResultFolder() + "speicher.txt";
	   FileWriter write = new FileWriter(pathname);
	   BufferedWriter out =  new BufferedWriter(write);
	   //File file = new File(pathname);
	   
		   if(this.l.size()<5) {
			   log.info("!!!!读取文件成功");
			   this.l.add(Ergebnis);	
			   //System.out.println(l);
		   }else {
			   //int i=this.l.size();
			   //int j=0;
			   
			   for(int i=0;this.l.size()>4;i++) {
				   this.l.remove(0);		
				   //this.l.remove(1);
			   }
			   log.info("!!!!行数大于5");
			   //this.l.add(Ergebnis);
		   }
		   for(int i=0;i<this.l.size();i++) {
			   out.write(this.l.get(i));
			   out.newLine();
		   }
		   //System.out.println("!!!!"+l.size());
		   out.flush();
		   out.close();
		   write.close();
	  
   }
	
	
	/*public static void main(String[]args) throws IOException {
		speichern s = new speichern();
		//s.Ergebnisspeichern();
		s.readFile();
		//s.writeFile("This is a test eingabekkkkkkk");
	}
 */
}
