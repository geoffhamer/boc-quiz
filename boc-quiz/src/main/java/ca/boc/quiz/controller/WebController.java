package ca.boc.quiz.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.boc.quiz.BocQuizApplication;
import ca.boc.quiz.model.ClimateData;
import ca.boc.quiz.service.ClimateDataParsingServiceImpl;

@Controller
public class WebController {
	
	private final static Logger LOG = LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	private ClimateDataParsingServiceImpl dataService;
	
	private Date earliestDate = new Date(Long.MIN_VALUE);
	private Date latestDate = new Date(Long.MAX_VALUE);
	

    @GetMapping("/")
    /**
     * Default page
     * 
     * @param name
     * @param model
     * @return
     */
    public String home(Model model) {
        
    	List<ClimateData> climateData = dataService.getCityRows();
    	
    	model.addAttribute("stations", climateData);
    	model.addAttribute("filterDates", new FilterDates());
       
        return "climate-summary";
    }
    
    
    @GetMapping("/summary")
    public String summary(Model model) {
    	
    	LOG.trace("Call to summary page");
        
    	List<ClimateData> climateData = dataService.getCityRows();
    	
    	model.addAttribute("stations", climateData);
    	model.addAttribute("filterDates", new FilterDates());
       
        return "climate-summary";
    }
    

    @PostMapping("/filterSummary")
	public String filterDataByDates(@ModelAttribute FilterDates filterDates, Model model) {
    	
    	LOG.trace("Call to filtered summary page");
    	
    	// If a date isn't provided, set it to the limit
    	if ( filterDates.getAfterDate() == null) {
			filterDates.setAfterDate (this.earliestDate );
		}
    	
    	if ( filterDates.getBeforeDate() == null) {
			filterDates.setBeforeDate (this.latestDate );
		}
    	
    	List<ClimateData> filteredClimateData = new ArrayList<ClimateData>();
    	
    	for (ClimateData climateData : dataService.getCityRows()) {
			
    		if ( climateData.getDate().after(filterDates.getAfterDate() ) && 
    			 climateData.getDate().before(filterDates.getBeforeDate() ) ) {
				
    			filteredClimateData.add( climateData );
			}
    		
		}
    	
    	model.addAttribute("stations", filteredClimateData);
    	model.addAttribute("filterDates", new FilterDates());
    	
    	return "climate-summary";
    }
       
    
    @GetMapping("/detail")
    public String detail(@RequestParam(name="index", required=true) String index, Model model) {
    	
    	LOG.trace("Call to details page");

    	int indexVal = -1;
    	try {
			indexVal = Integer.parseInt(index);
		} catch (Exception e) {
			
			LOG.error("Index provided for details page is not an integer.. setting ot '-1'");
		}
    	
    	ClimateData station;
    	try {
			
    		station = dataService.getCityRows().get(indexVal);
    		
		} catch (IndexOutOfBoundsException e) {
			
			LOG.error("Index provided for details page "+indexVal+" does not exist.");
			
			model.addAttribute("errorMessage", "Index provided for details page "+indexVal+" does not exist.");
	    	model.addAttribute("filterDates", new FilterDates());
	    	model.addAttribute("stations", dataService.getCityRows());
	    	return "climate-summary";
		}
    	
    	model.addAttribute("station", station);
        
        return "climate-detail";
    }
    

}
