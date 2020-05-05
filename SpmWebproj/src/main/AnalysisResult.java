package main;


import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("analysisResult")
@SessionScoped
public class AnalysisResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log	= Logger.getLogger(AnalysisResult.class.getName());
	
	private String sourceFileName;
	private List<Map.Entry<String, Integer>> wochentagUmsatz;
	private List<Map.Entry<String, Integer>> uhrzeitUmsatz;
	private List<Map.Entry<String, Integer>> artikelUmsatz;
	private List<Map.Entry<String, Integer>> artikelDeckungsbetrag;
	
	public AnalysisResult() {
		
	}
	

	public List<Map.Entry<String, Integer>> getWochentagUmsatz() {
		return wochentagUmsatz;
	}

	public void setWochentagUmsatz(List<Map.Entry<String, Integer>> wochentagUmsatz) {
		this.wochentagUmsatz = wochentagUmsatz;
	}

	public List<Map.Entry<String, Integer>> getUhrzeitUmsatz() {
		return uhrzeitUmsatz;
	}

	public void setUhrzeitUmsatz(List<Map.Entry<String, Integer>> uhrzeitUmsatz) {
		this.uhrzeitUmsatz = uhrzeitUmsatz;
	}

	public List<Map.Entry<String, Integer>> getArtikelUmsatz() {
		return artikelUmsatz;
	}

	public void setArtikelUmsatz(List<Map.Entry<String, Integer>> artikelUmsatz) {
		this.artikelUmsatz = artikelUmsatz;
	}

	public List<Map.Entry<String, Integer>> getArtikelDeckungsbetrag() {
		return artikelDeckungsbetrag;
	}

	public void setArtikelDeckungsbetrag(List<Map.Entry<String, Integer>> artikelDeckungsbetrag) {
		this.artikelDeckungsbetrag = artikelDeckungsbetrag;
	}


	public String getSourceFileName() {
		return sourceFileName;
	}


	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}
}
