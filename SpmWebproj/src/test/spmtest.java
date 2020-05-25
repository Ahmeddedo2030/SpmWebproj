package test;

import static org.junit.Assert.*;

import main.PathResultFolder;
import org.junit.Before;
import org.junit.Test;
import Entity.TFDeckung;
import Entity.OrdnungNachUmsatz;
import Entity.FindBestDayandTime;

import java.net.URL;


public class spmtest {

	private String path0;
	private String path1;
	private String path2;

	@Before
	public void before() {
		String a = spmtest.class.getResource("").getPath();
		//System.out.println(a);
		String[] pt = a.split("/");//Pfad der Datei spmtest
		String Path = "";
		for (int i = 1; i < pt.length-4;i++) {//exkl.""
             Path += pt[i];
             Path +="/";
		}
		//System.out.println(Path);
		path0 = Path+"WebContent/WEB-INF/upload/kd100.csv";
		path1 = Path+"WebContent/WEB-INF/upload/kd1000.csv";
		path2 = Path+"WebContent/WEB-INF/upload/kd10000.csv";

		//path0 = "C:/Users/小彭子hhh/Desktop/java程序/SpmWebproj/SpmWebproj/WebContent/WEB-INF/upload/kd100.csv";
		//path1 = "C:/Users/小彭子hhh/Desktop/java程序/SpmWebproj/SpmWebproj/WebContent/WEB-INF/upload/kd1000.csv";
		//path2 = "C:/Users/小彭子hhh/Desktop/java程序/SpmWebproj/SpmWebproj/WebContent/WEB-INF/upload/kd10000.csv";

	}
	@Test
	public void test() throws Exception {
		FindBestDayandTime fbd = new FindBestDayandTime();
		TFDeckung tf = new TFDeckung();
		OrdnungNachUmsatz onu = new OrdnungNachUmsatz();
		
		
		//best time test
		assertEquals("{'>17 Uhr'=11415, '10-12 Uhr'=7688, '12-14 Uhr'=5089, '<10 Uhr'=4518, '14-17 Uhr'=3389}",fbd.FindBesttagUndUhrzeit(path0).get(0).toString());
		assertEquals("{'>17 Uhr'=126729, '10-12 Uhr'=73397, '<10 Uhr'=51860, '14-17 Uhr'=48722, '12-14 Uhr'=44852}",fbd.FindBesttagUndUhrzeit(path1).get(0).toString());
		assertEquals("{'>17 Uhr'=1239152, '10-12 Uhr'=763880, '14-17 Uhr'=492590, '<10 Uhr'=491096, '12-14 Uhr'=464294}",fbd.FindBesttagUndUhrzeit(path2).get(0).toString());

		
		
		//best day test
		assertEquals("{Samstag=9133, Montag=5930, Dienstag=5028, Mittwoch=4791, Freitag=4347, Donnerstag=2767}",fbd.FindBesttagUndUhrzeit(path0).get(1).toString());
		assertEquals("{Samstag=89640, Freitag=66669, Montag=59025, Mittwoch=50164, Dienstag=50155, Donnerstag=28965}",fbd.FindBesttagUndUhrzeit(path1).get(1).toString());
		assertEquals("{Samstag=835516, Freitag=692992, Montag=546452, Mittwoch=530038, Dienstag=520941, Donnerstag=324966}",fbd.FindBesttagUndUhrzeit(path2).get(1).toString());

		
		
		//top and flop article by revenue
		assertEquals("{Aktionsware=7083, Baustoffe=6970, Armaturen=6509, Renovierung=5200, Fliesen=3680, Elektromaterial=3175, Sanitaermaterial=2611, Elektrowerkzeuge=2117, Schliesstechnik=1729, Leuchten=1357, Holz=1121, Handwerkzeuge=965, Farben=825, Bodenbelag=623, Eisenwaren=588}",onu.ordnungNachUmsatz(path0).toString());
		assertEquals("{Baustoffe=81689, Aktionsware=73704, Renovierung=53144, Armaturen=46862, Fliesen=42076, Elektromaterial=28859, Sanitaermaterial=26300, Elektrowerkzeuge=24750, Schliesstechnik=23270, Leuchten=14547, Holz=11815, Handwerkzeuge=9890, Farben=7415, Bodenbelag=7345, Eisenwaren=5622}",onu.ordnungNachUmsatz(path1).toString());
		assertEquals("{Baustoffe=803281, Aktionsware=735576, Renovierung=534082, Armaturen=495321, Fliesen=443719, Elektromaterial=289725, Sanitaermaterial=263286, Elektrowerkzeuge=242198, Schliesstechnik=228349, Leuchten=144456, Holz=115860, Handwerkzeuge=101251, Bodenbelag=86846, Farben=57970, Eisenwaren=54400}",onu.ordnungNachUmsatz(path2).toString());

	
		//top and flop article by profit margin
		assertEquals("{Armaturen=3481.5583, Baustoffe=2720.0, Fliesen=1690.8108, Renovierung=1431.884, Elektromaterial=954.7203, Sanitaermaterial=870.3334, Leuchten=814.2, Elektrowerkzeuge=582.942, Aktionsware=463.37384, Schliesstechnik=345.80002, Bodenbelag=260.79068, Holz=224.2, Farben=204.69925, Handwerkzeuge=193.0, Eisenwaren=117.6}",tf.tFDeckung(path0).toString());
		assertEquals("{Baustoffe=31878.633, Armaturen=25065.723, Fliesen=19332.217, Renovierung=14633.8545, Sanitaermaterial=8766.667, Leuchten=8728.2, Elektromaterial=8677.881, Elektrowerkzeuge=6815.2173, Aktionsware=4821.757, Schliesstechnik=4654.0, Bodenbelag=3074.6511, Holz=2363.0, Handwerkzeuge=1978.0, Farben=1839.8121, Eisenwaren=1124.4}",tf.tFDeckung(path1).toString());
		assertEquals("{Baustoffe=313475.5, Armaturen=264939.16, Fliesen=203870.89, Renovierung=147066.06, Sanitaermaterial=87762.0, Elektromaterial=87120.1, Leuchten=86673.6, Elektrowerkzeuge=66692.2, Aktionsware=48121.793, Schliesstechnik=45669.8, Bodenbelag=36354.14, Holz=23172.0, Handwerkzeuge=20250.201, Farben=14383.534, Eisenwaren=10880.0}",tf.tFDeckung(path2).toString());


	}

}
