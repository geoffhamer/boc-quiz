package ca.boc.quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ca.boc.quiz.exception.DataParsingException;
import ca.boc.quiz.service.ClimateDataParsingServiceImpl;

@SpringBootApplication
public class BocQuizApplication {

	private final static Logger LOG = LoggerFactory.getLogger(BocQuizApplication.class);
	
	public static void main(String[] args) {
		
		String fileLocation;
		
		if ( args.length > 0 && 
		     args[0] != null ) {
			
			fileLocation = args[0];

			ConfigurableApplicationContext context = SpringApplication.run(BocQuizApplication.class, args);

			ClimateDataParsingServiceImpl service = (ClimateDataParsingServiceImpl)context.getBean(ClimateDataParsingServiceImpl.class);
			
			try {

				service.initClimateData(fileLocation);
				
			} catch (DataParsingException e) {
				
				System.out.println(e.getMessage());
				context.close();
				
			}
		
		} else {
			
			String errStr = "The location of a .csv file must be provided to execute this application.";
			
			LOG.error(errStr);
			System.out.println(errStr);
			System.out.println("Example: java -jar boc-quiz.jar <csv file location>");
			
		}
		
	}

}
