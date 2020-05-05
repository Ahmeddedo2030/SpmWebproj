package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@Named("analysisResultHandler")
@SessionScoped
public class AnalysisResultHandler implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger log	= Logger.getLogger(AnalysisResultHandler.class.getName());
	
	private List<AnalysisResult> analysisHistory;
	private AnalysisResult displayedResult;
	private AnalysisResult latestUnsavedResult;
	
	private BarChartModel chartZeitUmsatz;
	private BarChartModel chartTagUmsatz;
	private BarChartModel chartWarenUmsatz;
	private BarChartModel chartWarenDeckung;
	
	public AnalysisResultHandler() {
		
	}
	
	@PostConstruct
	public void init() {
		loadResultsFromFile();
		if(!analysisHistory.isEmpty()) {
			log.info("Successfully loaded analysisHistory");
			displayedResult = analysisHistory.get(0);
		}else {
			log.info("Could not load analysisHistory");
			displayedResult = null;
		}
		//displayLatestUploadResult();	//for testing purposes
		updateCharts();
		//justToConfirmTest("InsideMethod"); //for testing purposes
	}

	public void updateCharts() {
		log.info("Beginning chart update");
		updateTagUmsatz();
		updateZeitUmsatz();
		updateWarenUmsatz();
		updateWarenDeckung();
		log.info("Chart update concluded");
	}
	
	private void updateTagUmsatz() {
		chartTagUmsatz = new BarChartModel();
		log.info("Clearing chart");
		Axis xAxis = chartTagUmsatz.getAxis(AxisType.X);
		xAxis.setLabel("Wochentag");
		
		Axis yAxis = chartTagUmsatz.getAxis(AxisType.Y);
		yAxis.setLabel("Umsatz");
		yAxis.setMin(0);
		//yAxis.setMax(2500);
		
		log.info("Creating axes.");
		
		ChartSeries uPW = new ChartSeries();
		uPW.setLabel("Umsatz pro Wochentag");
		if(displayedResult != null) {
			for(Map.Entry<String, Integer> tmp: displayedResult.getWochentagUmsatz()) {
				uPW.set(tmp.getKey(), tmp.getValue());
			}
			log.info("Successfully loaded chart from result");
		} else {
			log.info("No Result to display");
		}
		
		chartTagUmsatz.addSeries(uPW);
	}
	
	private void updateZeitUmsatz() {
		chartZeitUmsatz = new BarChartModel();
		log.info("Clearing chart");
		Axis xAxis = chartZeitUmsatz.getAxis(AxisType.X);
		xAxis.setLabel("Uhrzeit");
		
		Axis yAxis = chartZeitUmsatz.getAxis(AxisType.Y);
		yAxis.setLabel("Umsatz");
		yAxis.setMin(0);
		//yAxis.setMax(2500);
		
		log.info("Creating axes.");
		
		ChartSeries uPW = new ChartSeries();
		uPW.setLabel("Umsatz pro Uhrzeit");
		if(displayedResult != null) {
			for(Map.Entry<String, Integer> tmp: displayedResult.getUhrzeitUmsatz()) {
				uPW.set(tmp.getKey(), tmp.getValue());
			}
			log.info("Successfully loaded chart from result");
		} else {
			log.info("No Result to display");
		}
		
		chartZeitUmsatz.addSeries(uPW);
	}
	
	private void updateWarenUmsatz() {
		chartWarenUmsatz = new BarChartModel();
		log.info("Clearing chart");
		Axis xAxis = chartWarenUmsatz.getAxis(AxisType.X);
		xAxis.setLabel("Warengruppen");
		
		Axis yAxis = chartWarenUmsatz.getAxis(AxisType.Y);
		yAxis.setLabel("Umsatz");
		yAxis.setMin(0);
		//yAxis.setMax(2500);
		
		log.info("Creating axes.");
		
		ChartSeries uPW = new ChartSeries();
		uPW.setLabel("Umsatz pro Warengruppe");
		if(displayedResult != null) {
			for(Map.Entry<String, Integer> tmp: displayedResult.getArtikelUmsatz()) {
				uPW.set(tmp.getKey(), tmp.getValue());
			}
			log.info("Successfully loaded chart from result");
		} else {
			log.info("No Result to display");
		}
		
		chartWarenUmsatz.addSeries(uPW);
	}
	
	private void updateWarenDeckung() {
		chartWarenDeckung = new BarChartModel();
		log.info("Clearing chart");
		Axis xAxis = chartWarenDeckung.getAxis(AxisType.X);
		xAxis.setLabel("Warengruppe");
		
		Axis yAxis = chartWarenDeckung.getAxis(AxisType.Y);
		yAxis.setLabel("Deckungsspanne");
		yAxis.setMin(0);
		//yAxis.setMax(2500);
		
		log.info("Creating axes.");
		
		ChartSeries uPW = new ChartSeries();
		uPW.setLabel("Deckung pro Warengruppe");
		if(displayedResult != null) {
			for(Map.Entry<String, Integer> tmp: displayedResult.getArtikelDeckungsbetrag()) {
				uPW.set(tmp.getKey(), tmp.getValue());
			}
			log.info("Successfully loaded chart from result");
		} else {
			log.info("No Result to display");
		}
		
		chartWarenDeckung.addSeries(uPW);
	}
	
	public String changeDisplayedResult(int index) {
		try {
			displayedResult = analysisHistory.get(index);
		}catch(IndexOutOfBoundsException e) {
			return null;
		}
		updateCharts();
		return "/ResultDisplay.xhtml";
	}
	
	public String displayLatestUnsaved() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		AnalysisResult tmp = (AnalysisResult) session.getAttribute("latestUploadResult");
		if(tmp != null) {
			log.info("New upload recognized. Overwriting last unsaved.");
			session.setAttribute("latestUploadResult", null);
			log.info("Cleared new upload flag");
			latestUnsavedResult = tmp;
			displayedResult = tmp;
			//System.out.println(latestUnsavedResult.getWochentagUmsatz().get(0).getKey());	//for testing purposes
			log.info("Retrieved latest upload during this session");
		}
		else {
			log.info("No new upload since last check");
			displayedResult = latestUnsavedResult;
		}
		updateCharts();
		return "/ResultDisplay.xhtml";
	}
	
	public void saveUnsavedToFile() {
		
	}
	
	private void loadResultsFromFile() {
		analysisHistory = new ArrayList<AnalysisResult>();
	}

	//Chart Getters
	
	public BarChartModel getChartTagUmsatz() {
		return chartTagUmsatz;
	}
	
	public BarChartModel getChartZeitUmsatz() {
		return chartZeitUmsatz;
	}
	
	public BarChartModel getChartWarenUmsatz() {
		return chartWarenUmsatz;
	}
	
	public BarChartModel getChartWarenDeckung() {
		return chartWarenDeckung;
	}

	//Misc
	
	public AnalysisResult getDisplayedResult() {
		return displayedResult;
	}
	
	public void justToConfirmTest(String msg) {
		log.info("Just to confirm it works: "+msg);
	}
}
