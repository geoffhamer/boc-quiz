package ca.boc.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.boc.quiz.util.DataFileReader;

@Controller
public class WebController {
	
    @GetMapping("/info")
    public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        
        model.addAttribute("name", name);
        
        DataFileReader reader = new DataFileReader();
        reader.fetchRawData();
        
        return "info";
    }

}
