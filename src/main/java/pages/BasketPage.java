package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import utils.TestBase;

public class BasketPage extends TestBase {

	By CreditCardRadioOption = By.xpath("//div[@value='Credit or Debit Card']//input[@value='Credit or Debit Card']");
	By ContinuetoCheckoutButton = By.xpath("//*[@id='__next']//button[@type='button']");
	By ImportantSeatInfoLink = By.xpath("//a[@class='seat-info__link']");
	By ImportantSeatInfoPopup = By.xpath("//div[@class='seat-info__seat-group']");

	public void ClickonCreditorDebitCardOption() {
		driver.findElement(CreditCardRadioOption).click();
	}

	public void ContinuetoCheckoutButton() {
		driver.findElement(ContinuetoCheckoutButton).click();
	}

	public void VerifyImportantSeatInfoLinkPresent() {
		Assert.assertTrue(driver.findElement(ImportantSeatInfoLink).isDisplayed());
	}

	public void ClickonImportantSeatInfoLink() {
		driver.findElement(ImportantSeatInfoLink).click();
	}

	public void GetImportantSeatInfoLink(String expectedSeatInfo) {

		String actualValue = driver.findElement(ImportantSeatInfoPopup).getAttribute("innerText");
		Assert.assertTrue(actualValue.contains(expectedSeatInfo));
	}

}
