package ca.boc.quiz.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.boc.quiz.model.ClimateData;
import ca.boc.quiz.service.ClimateDataParsingServiceImpl;

@Controller
public class WebController {
	
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
        
    	List<ClimateData> climateData = dataService.getCityRows();
    	
    	model.addAttribute("stations", climateData);
    	model.addAttribute("filterDates", new FilterDates());
       
        return "climate-summary";
    }
    

    @PostMapping("/filterSummary")
	public String filterDataByDates(@ModelAttribute FilterDates filterDates, Model model) {
    	
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
    	
    	int indexVal = 0;
    	try {
			indexVal = Integer.parseInt(index);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	ClimateData station = dataService.getCityRows().get(indexVal);
    	
    	model.addAttribute("station", station);
        
        return "climate-detail";
    }
    

}
