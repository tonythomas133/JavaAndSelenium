package automation;

import java.awt.Desktop.Action;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TC2_Commands {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "F:\\selenium training\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(); //INTERVIEW QUESTION: why is there a difference in initiallizing the class. ans . webdriver is the interface (interface is an empty body where only declaration of functions are done: an object cannot be created of an interface)  and chromedriver is a class
		driver.get("https://in.musafir.com/");
		driver.manage().window().maximize(); //maximize the browser window
		
		String returnURL= driver.getCurrentUrl(); //this returns the current url
		String pageTitle = driver.getTitle(); //getTitle() will give us the browser tab heading
		String pageSourceCode = driver.getPageSource();//getPageSource is not used frequently
		driver.close(); //INTERVIEW QUESTION: at a time driver can have control of only one tab at a time. current tab is closed
		driver.quit(); //INTERVIEW QUESTION:close the entire browser.  
		
		//navigate command
		driver.navigate().to("https://in.musafir.com/"); //similar to driver.get
		driver.navigate().refresh(); //refresh browser window
		driver.navigate().forward(); //move forward in page instance
		driver.navigate().back(); // move backward in page instance
		
		/*
		 id
		 name
		 class -usually not unique
		 xpath  -more dependable
		 linkText
		 partialLinkText
		 CSS -cascade style sheet - rarely used
		 tagname
		 */
		WebElement elementName = driver.findElement(By.xpath("xpath value")); //WebElement is the class to be used. This will interact with the first available element and hence you are expected to use xpath that procduces a 1 of 1
		elementName.click(); //click command
		List<WebElement> offerlst = driver.findElements(By.xpath(" ")); //INTERVIEW QUESTION difference between findElement and findElements , findElements expects multiple elements
		offerlst.get(0).isDisplayed(); //
		offerlst.get(2).click();
		int count = offerlst.size();
		
		//typing text in browser
		WebElement fromLoc = driver.findElement(By.xpath(""));
		fromLoc.clear(); //considered best practice, it will make sure that if there is some text it will remove it before you type in 
		fromLoc.sendKeys("kochi, India(COK)");
		
		Thread.sleep(1000); //this will make the program wait for 1000 ms or 1 second.  you have to add throw InterruptedException to remove the error 
		
		
		boolean condition = fromLoc.isDisplayed(); //the true value will always work. in case of false it will probably not work   
		fromLoc.isEnabled(); //this checks if the element is clickable. eg: text box
		fromLoc.isSelected(); // radio button, checkbox
		
		WebElement oneWayTrvlType_Lnk;
		String elementText= oneWayTrvlType_Lnk.getText(); //will display the text in the element
		String elementTag = fromLoc.getTagName(); //rarely used, will give the tagname
		String attributeName = fromLoc.getAttribute("name");//will give the attribute value
		
		
		WebElement numberOfAdults = driver.findElement(By.xpath("//select[@name='AdultFlights'])"));
		Select dropDown = new Select(numberOfAdults);
		dropDown.selectByIndex(1); //index starts from 0  ..this is not prefered as the index is not constant and more over you have to find the index position which can be time consuming if the list is too long
		dropDown.selectByValue("4"); //take values from the value tag . the code will probably loose its context because the value field will not give an indication of what the field represents
		dropDown.selectByVisibleText("9 adults"); //the exact visible text has to be entered.. most preferred as it solves the problems of the previous 2 options
		
		List<WebElement> dropDownValues = dropDown.getOptions(); //use to get all options from a drop down
		for(int i=0; i<dropDownValues.size(); i++) {
			String dropDownText = dropDownValues.get(i).getText();
			System.out.println("Drop down values in adults: "+ dropDownText);
			
		
		//Action class
		Actions act = new Actions(driver);
		act.moveToElement(numberOfAdults).build().perform(); //mouse over
		act.click().build().perform(); //build.perform has to be used after using action class
		act.moveToElement(numberOfAdults).click().build().perform(); //single line alternative of above 2 lines
		act.click(numberOfAdults).build().perform();
		act.contextClick(numberOfAdults).keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).build().perform();
		act.doubleClick(numberOfAdults).build().perform();
		act.dragAndDrop(fromLoc, numberOfAdults).build().perform();//only works 60%
		act.clickAndHold(fromLoc).moveToElement(numberOfAdults).build().perform(); //replacement for previous line
		act.keyDown(Keys.ENTER).build().perform();
		act.keyUp(Keys.ENTER).build().perform();
		act.sendKeys(Keys.TAB).build().perform();
		
		
		//handling iframe
		driver.switchTo().frame(0); //indexing method : check ho many iframes are there and check which iframe you require , indexing starts from 0
		driver.switchTo().frame("launcher-frame"); //id
		WebElement frame_contactUs = driver.findElement(By.xpath(dropDownText)); //prefered
		//do whatever action you have to do
		driver.switchTo().defaultContent(); //this has to be used to exit the iframe
		}
		
		
		
	}

}
