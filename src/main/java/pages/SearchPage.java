package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import utils.JavaScriptHelper;
import utils.TestBase;
import utils.TestUtils;

public class SearchPage extends TestBase {
	

	JavaScriptHelper javaScriptHelper;
	By SearchField=By.xpath("//input[@placeholder='Search by show or attraction' and @type='search']");
	By ShowField=By.xpath("//div[@class='c-quick-search__item t-card-primary-hover']");
	By FindTicketsButton= By.xpath("//button[@type='submit' and @role='button']");
	

	
	public void SearchShow(String Show) {
			
		driver.findElement(SearchField).sendKeys(Show);
		
    }
	
	public void SelectShow() {
		WebElement element = driver.findElement(ShowField);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		
    }
	
	 public void ClickFindTickets() {
		    WebElement element = driver.findElement(FindTicketsButton);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT);
	    }

}
