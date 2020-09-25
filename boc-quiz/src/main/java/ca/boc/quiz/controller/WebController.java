package ca.boc.quiz.controller;

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

import ca.boc.quiz.model.ClimateData;
import ca.boc.quiz.service.ClimateDataParsingServiceImpl;

@Controller
public class WebController {
	
	private final static Logger LOG = LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	private ClimateDataParsingServiceImpl dataService;
	
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
    	
    	List<ClimateData> filteredClimateData = this.dataService.getCityRowsByDate( filterDates.getAfterDate() , filterDates.getBeforeDate() );
    	
    	model.addAttribute("stations", filteredClimateData);
    	model.addAttribute("filterDates", new FilterDates());
    	
    	return "climate-summary";
    }
       
    
    @GetMapping("/detail")
    public String detail(@RequestParam(name="index") String index, Model model) {
    	
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
    
    @GetMapping("/heartbeat")
    public String getHeartbeat() {
    	return "hello";    	
    }

}
