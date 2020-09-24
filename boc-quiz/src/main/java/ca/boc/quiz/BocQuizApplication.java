package ca.boc.quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ca.boc.quiz.service.ClimateDataParsingServiceImpl;

@SpringBootApplication
public class BocQuizApplication {

	private final static Logger LOG = LoggerFactory.getLogger(BocQuizApplication.class);
	
	public static void main(String[] args) {
		
		String fileLocation = "C:/temp/eng-climate-summary.csv";
		
		ConfigurableApplicationContext context = SpringApplication.run(BocQuizApplication.class, args);
		
		if ( args.length > 0 && 
		     args[0] != null ) {
			
			fileLocation = args[0];

			ClimateDataParsingServiceImpl service = (ClimateDataParsingServiceImpl)context.getBean(ClimateDataParsingServiceImpl.class);
			service.initClimateData(fileLocation);
		
		} else {
			
			String errStr = "The location of a .csv file must be provided to execute this application.";
			
			LOG.error(errStr);
			System.out.println(errStr);
			System.out.println("Example: java -jar boc-quiz.jar <csv file location>");
			
		}
		
	}

}
