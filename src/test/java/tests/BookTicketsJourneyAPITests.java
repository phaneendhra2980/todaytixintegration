package tests;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.logging.SimpleFormatter;

import org.testng.annotations.Test;

public class BookTicketsJourneyAPITests {

	@Test
	public void getCheckOutJourney() {

		//Start Date and End Date Input to Check Journey Service 
		Date START_DATE = new Date(System.currentTimeMillis() + 86400 * 1000 * 1);
		Date END_DATE = new Date(System.currentTimeMillis() + 86400 * 1000 * 2);

		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
		String startdate = DATE_FORMAT.format(START_DATE);
		String enddate = DATE_FORMAT.format(END_DATE);
		
		
		
		System.out.println(startdate);
		System.out.println(enddate);
		

		ArrayList<String> response = services.DateTimeService.getCheckOutJourney(startdate, enddate);

		Random random = new Random();
		int index = random.nextInt(response.size());
		System.out.println("Random Date Selected :" + response.get(index));
		
		for (String a : response) {

			System.out.println("The Date Time value fetched is " + a);
			
			SimpleDateFormat simpleformat  = new SimpleDateFormat("yyyyMMdd");
			DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate ld = LocalDate.parse(a, DATEFORMATTER);
		    LocalDateTime ldt = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());
			String randomdate = simpleformat.format(ldt);
			System.out.println(randomdate);
			
			SimpleDateFormat timeformat  = new SimpleDateFormat("HH:mm");
			String randomtime = timeformat.format(ldt);			
			System.out.println(randomtime);
			

		}
		
		

	}

	@Test
	public void getSeats() {

		services.SeatsService.getSeats("20220523", "1900");
		
	}
	
	@Test
	public void getRestrictedSeats() {

		services.SeatsService.getRestrictedSeats("20220521", "1930");
		
	}
}
