package Entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class speichern {
	
	List <String> l = new ArrayList<>(); 
	
	public boolean readFile() {
		String pathname="speicher.txt";
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
				//   System.out.println(line);
			   }
			   System.out.println("共有行数："+i);
			   br.close();
			   read.close();
			   if(i<=10)
				   return true;
			   return false;
			}else {
				System.out.println("keine File!");
				return false;
			}
		}catch(IOException e) {
			   e.printStackTrace();
			   return false;
		}
	} 
	public void writeFile(String Ergebnis) throws IOException {
	  
	   String pathname = "speicher.txt";
	   FileWriter write = new FileWriter(pathname);
	   BufferedWriter out =  new BufferedWriter(write);
	   //File file = new File(pathname);
	   
		   if(this.l.size()<6) {
			   System.out.println("!!!!读取文件成功");
			   this.l.add(Ergebnis);	
			   //System.out.println(l);
		   }else {
			   //int i=this.l.size();
			   //int j=0;
			   
			   for(int i=0;this.l.size()>5;i++) {
				   this.l.remove(0);
				   //this.l.remove(1);
			   }
			   System.out.println("!!!!行数大于5");
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
