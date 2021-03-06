package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class JavaScriptHelper {
	
	WebDriver driver;
	JavascriptExecutor jsExecutor ;
	
	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
		jsExecutor = (JavascriptExecutor) driver;
	}
	
	public void highlightElement(String locator) {
		  String jsQuery = 
		  String.format("%s.style.backgroundColor='red'", locator);

		  jsExecutor.executeScript(jsQuery);
		}

	public void typeJS(String locator, String keyword) {
		  String jsQuery = 
		    String.format("%s.value='%s'", locator, keyword);

		  jsExecutor.executeScript(jsQuery);
		}

		public void clickJS(String locator) {
		  String jsQuery = String.format("%s.click()", locator);
		  jsExecutor.executeScript(jsQuery);
		}

		public String getValueJS(String locator) {

		  String jsQuery = 
		      String.format("function getValue() " +
		      "{var value=%s.innerHTML;return value;};" +
		      "return getValue()", locator);

		  return (String) jsExecutor
		         .executeScript(jsQuery);

		}

		public WebElement getElementJS(String locator) {

		  String jsQuery = 
		    String.format("function getElement()" +
		          "{var element = %s; return element; }; " +
		          "return getElement()", locator);

		  WebElement element = 
		    (WebElement) jsExecutor.executeScript(jsQuery);

		  return element;
		}

		

		public void waitUntilPageLoaded() {
		  Boolean isLoaded = false;
		  while (!isLoaded) {
		    isLoaded = isPageLoaded();
		   
		  }
		}

		public Boolean isPageLoaded() {
		   String jsQuery = "function pageLoaded() {var loadingStatus = (document.readyState == 'complete');return loadingStatus; }; return pageLoaded()";

		    return (Boolean)jsExecutor
		        .executeScript(jsQuery);
		 }

}
