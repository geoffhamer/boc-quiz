package ca.boc.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
    @GetMapping("/info")
    public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        
        model.addAttribute("name", name);
        
        //DataFileReader reader = new DataFileReader();
        //reader.fetchRawData();
        
        return "info";
    }

    
    
    @GetMapping("/summary")
    public String summary(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        
    	List<ClimateData> climateData = dataService.getCityRows();
    	
    	model.addAttribute("stations", climateData);
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
    
    @PostMapping("/filterSummary")
	public String filterDataByDates(@ModelAttribute FilterDates filterDates, Model model) {
    	
    	model.addAttribute("filterDates", filterDates);
      
    	return "climate-summary";
    }
    
}
