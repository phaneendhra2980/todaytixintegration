package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.BookingSeatPlanPage;
import pages.SearchPage;
import utils.JavaScriptHelper;
import utils.TestBase;

public class BookTicketJourneyIntegrationTests extends TestBase {

	public WebDriver driver;
	JavaScriptHelper javaScriptHelper;
	SearchPage searchPage;
	BookingSeatPlanPage bookingseatplanPage;
	public static Properties properties;

	public BookTicketJourneyIntegrationTests() {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		try {

			properties = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/config/config.properties");
			properties.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("file not found");
		} catch (IOException e) {
			System.out.println("io exception");

		}
		initialization();
		searchPage = new SearchPage();
		bookingseatplanPage = new BookingSeatPlanPage();

	}

	@Test
	public void SearchShow() {

		searchPage.SearchShow("TINA-The Tina Turner");
		searchPage.SelectShow();
		searchPage.ClickFindTickets();

		// Start Date and End Date Input to Check Journey Service
		Date START_DATE = new Date(System.currentTimeMillis() + 86400 * 1000 * 1);
		Date END_DATE = new Date(System.currentTimeMillis() + 86400 * 1000 * 2);

		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("dd");
		String startdate = DATE_FORMAT.format(START_DATE);
		String enddate = DATE_FORMAT.format(END_DATE);
		String startday = DAY_FORMAT.format(START_DATE);
		System.out.println(startdate);
		System.out.println(enddate);

		ArrayList<String> response = services.DateTimeService.getCheckOutJourney(startdate, enddate);
		Random random = new Random();
		int index = random.nextInt(response.size());
		System.out.println("Random Date Selected :" + response.get(index));

		for (String a : response) {

			services.SeatsService.getSeats(startdate, "1930");

		}
		bookingseatplanPage.SelectDate(startday);		
		bookingseatplanPage.ClickGetTickets();
		bookingseatplanPage.SelectSeats();
	}

	@Test
	public void SearchRestrictedShow() {

		searchPage.SearchShow("TINA-The Tina Turner");
		searchPage.SelectShow();
		searchPage.ClickFindTickets();

		// Start Date and End Date Input to Check Journey Service
		Date START_DATE = new Date(System.currentTimeMillis() + 86400 * 1000 * 1);
		Date END_DATE = new Date(System.currentTimeMillis() + 86400 * 1000 * 2);

		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("dd");
		String startdate = DATE_FORMAT.format(START_DATE);
		String enddate = DATE_FORMAT.format(END_DATE);
		String startday = DAY_FORMAT.format(START_DATE);
		System.out.println(startdate);
		System.out.println(enddate);

		ArrayList<String> response = services.DateTimeService.getCheckOutJourney(startdate, enddate);
		Random random = new Random();
		int index = random.nextInt(response.size());
		System.out.println("Random Date Selected :" + response.get(index));
		for (String a : response) {

			services.SeatsService.getRestrictedSeats(startdate, "1900");

		}

		bookingseatplanPage.SelectDate(startday);		
		bookingseatplanPage.ClickGetTickets();
		bookingseatplanPage.SelectSeats();
	}

	@AfterMethod
	public void teardown() throws InterruptedException {

		tearDownMain();

	}

}
