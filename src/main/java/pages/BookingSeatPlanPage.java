package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import utils.TestBase;

public class BookingSeatPlanPage extends TestBase {

	By DateField = By.xpath("//div[@class='dayContainer']//span[@class='flatpickr-day t-affiliate-font-color']");

	By GetTicketsButton = By
			.xpath("//div[@class='full-fat-calendar__redirect-button gr-col gr-col--redirect-button full-fat-cta']");
	By AddToBasketButton = By.xpath(
			"//div[@class='seat-summary__purchase-panel']//button[@class='seat-summary__btn--submit t-btn t-btn-super']");

	public void SelectDate(String Datevalue) {

		List<WebElement> datecolumns = driver.findElements(DateField);
		for (int i = 0; i < datecolumns.size(); i++) {

			if (datecolumns.get(i).getText().contains(Datevalue)) {
				datecolumns.get(i).click();
				break;
			}

		}

	}

	public void SelectPerformance(String time) {
		WebElement element = driver.findElement(By.xpath("//input[@value='" + time + "']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

	}

	public void ClickGetTickets() {
		driver.findElement(GetTicketsButton).click();

	}

	public void AddToBasket() {
		WebElement element = driver.findElement(AddToBasketButton);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

	}

	public void SelectSeats() {

		driver.switchTo().frame(0);
		driver.manage().window().setSize(new Dimension(1440, 823));
		driver.findElement(By.cssSelector(".Floors")).click();
		driver.findElement(By.cssSelector(".Floors")).click();

	}
}
