package ca.boc.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ca.boc.quiz.service.ClimateDataParsingServiceImpl;

@SpringBootApplication
public class BocQuizApplication {

	public static void main(String[] args) {
		
		String fileLocation = "C:/temp/eng-climate-summary.csv";
		
		ConfigurableApplicationContext context = SpringApplication.run(BocQuizApplication.class, args);
		
		if ( args.length > 0 && 
		     args[0] != null ) {
			
			fileLocation = args[0];
		}
		
		// todo: Replace with startup listener
		ClimateDataParsingServiceImpl service = (ClimateDataParsingServiceImpl)context.getBean(ClimateDataParsingServiceImpl.class);
		service.initClimateData(fileLocation);
	}

}
