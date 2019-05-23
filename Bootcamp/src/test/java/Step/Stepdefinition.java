package Step;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import Util.Lib;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en_old.Ac;
import junit.framework.Assert;

public class Stepdefinition extends Lib {

	public WebDriver driver;
	public String unitedlow;
	public String spiritlow;
	public String unitedticket;
	public String spiritticket;

	@Before
	public void initialize() throws Throwable {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Chau Nguyen\\eclipse-workspace\\Bootcamp\\chromedriver.exe");
		
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Chau Nguyen\\eclipse-workspace\\Bootcamp\\geckodriver.exe");
		//driver = new FirefoxDriver();
	
		//System.setProperty("webdriver.edge.driver","C:\\Users\\Chau Nguyen\\eclipse-workspace\\Bootcamp\\MicrosoftWebDriver.exe");
		//driver=new EdgeDriver();
		
		// Webdriver is interface
		driver = new ChromeDriver();
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(readproperties(driver, "unitedhomepage"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Given("^user at united homepage$")
	public void unitedhomepage() throws IOException {
		System.out.println(driver.getTitle());
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Find element by link text and store in variable "Element"
		WebElement Element = driver.findElement(By.xpath(readproperties(driver, "uroundtrip")));
		// This will scroll the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", Element);
	}

	@And("^user click on ticket type \"([^\"]*)\" \"([^\"]*)\"$")
	public void tickettype(String rownum, String colnum) throws Throwable, IOException {
		String ticket = readexcel(driver, Integer.parseInt(rownum), Integer.parseInt(colnum));
		if (driver.getTitle().contains("United Airlines")) {
			if (ticket.contains("One-way")) {
				driver.findElement(By.xpath(readproperties(driver, "uoneway"))).click();
				unitedticket="oneway";
			} else {
				driver.findElement(By.xpath(readproperties(driver, "uroundtrip"))).click();
				unitedticket="roundtrip";
			}
		} else {
			if (ticket.contains("One-way")) {
				driver.findElement(By.xpath(readproperties(driver, "aoneway"))).click();
				spiritticket="oneway";
			} else {
				driver.findElement(By.xpath(readproperties(driver, "aroundtrip"))).click();
				spiritticket="roundtrip";
			}
		}
	}

	@And("^user select ticket class \"([^\"]*)\" \"([^\"]*)\"$")
	public void ticketclass(String rownum, String colnum) throws Throwable, IOException {
		String ticket = readexcel(driver, Integer.parseInt(rownum), Integer.parseInt(colnum));
		if (driver.getTitle().contains("United Airlines")) {
			if (ticket.contains("Economy")) {
				driver.findElement(By.xpath(readproperties(driver, "ueconomy"))).click();
			} else {
				driver.findElement(By.xpath(readproperties(driver, "ubusiness"))).click();
			}
		} else {
			if (ticket.contains("Economy")) {
				driver.findElement(By.xpath(readproperties(driver, "aeconomy"))).click();
			} else {
				driver.findElement(By.xpath(readproperties(driver, "abusiness"))).click();
			}
		}
	}

	@And("^user select travelers \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void travelers(String travelers, String rownum, String colnum) throws Throwable, IOException {

		driver.findElement(By.xpath(readproperties(driver, "utravelers"))).clear();
		driver.findElement(By.xpath(readproperties(driver, "utravelers"))).click();

		int num = Integer.parseInt(readexcel(driver, Integer.parseInt(rownum), Integer.parseInt(colnum)));
		for (int i = num; i > 1; i--) {
			driver.findElement(By.xpath(readproperties(driver, "uadults"))).click();
		}
		driver.findElement(By.xpath(readproperties(driver, "uclosepanel"))).click();
	}

	@And("^user mouse over \"([^\"]*)\"$")
	public void usermouseover(String key) throws IOException {
		Actions ac = new Actions(driver);
		ac.moveToElement(driver.findElement(By.xpath(readproperties(driver, key)))).build().perform();
	}

	@And("^user wait for \"([^\"]*)\"$")
	public void wait(String tm) throws InterruptedException {
		Thread.sleep(1000 * Integer.parseInt(tm));
	}

	@And("^user print page title$")
	public void printpagetitle() {
		System.out.println(driver.getTitle());
	}

	@And("^user close webpage$")
	public void closewebpage() {
		driver.close();
	}

	@And("^user navigate to \"([^\"]*)\"$")
	public void openwebpage(String webpage) throws IOException {

		driver.navigate().to(readproperties(driver, webpage));
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Find element by link text and store in variable "Element"
		WebElement Element = driver.findElement(By.xpath(readproperties(driver, "aroundtrip")));
		// This will scroll the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", Element);
	}

	@And("^user click \"([^\"]*)\"$")
	public void userclickon(String key) throws IOException {
//		if(unitedticket=="roundtrip") {
//			driver.findElement(By.xpath(readproperties(driver, "ureturndatebox"))).click();
//		}
//		if(spiritticket=="roundtrip") {
//			driver.findElement(By.xpath(readproperties(driver, "areturndatebox"))).click();
//		}
		
		driver.findElement(By.xpath(readproperties(driver, key))).click();
		
	}
	
	@And("^user print element \"([^\"]*)\"$")
	public void userprintelement(String key) throws IOException {
		System.out.println(driver.findElement(By.xpath(readproperties(driver, key))).getText());
	}

	@And("^user print united tickets \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void userprintunitedtickets(String t1, String t2, String t3) throws IOException {
		
		String ulowest=driver.findElement(By.xpath(readproperties(driver, "uprice"))).getText();
		String united1=driver.findElement(By.xpath(readproperties(driver, t1))).getText();
		String united2=driver.findElement(By.xpath(readproperties(driver, t2))).getText();
		String united3=driver.findElement(By.xpath(readproperties(driver, t3))).getText();
		
		united1 = united1.replaceAll("\\D+","");
		united2 = united2.replaceAll("\\D+","");
		united3 = united3.replaceAll("\\D+","");
		ulowest = ulowest.replaceAll("\\D+","");
		unitedlow = ulowest;
		
		System.out.println(united1);
		System.out.println(united2);
		System.out.println(united3);
		
		//System.out.println("Including lowest price: "+ulowest);
		
	}
	
	@And("^user print spirit tickets \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void userprintspirittickets(String t1, String t2, String t3) throws IOException {
		
		String alowest=driver.findElement(By.xpath(readproperties(driver, "aprice"))).getText();
		String american1=driver.findElement(By.xpath(readproperties(driver, t1))).getText();
		String american2=driver.findElement(By.xpath(readproperties(driver, t2))).getText();
		String american3=driver.findElement(By.xpath(readproperties(driver, t3))).getText();
		
		american1 = american1.replaceAll("\\D+","");
		american2 = american2.replaceAll("\\D+","");
		american3 = american3.replaceAll("\\D+","");
		alowest = alowest.replaceAll("\\D+","");
		spiritlow=alowest;
		
		int a1= Integer.parseInt(american1);
		int a2= Integer.parseInt(american2);
		int a3= Integer.parseInt(american3);
		
		System.out.println(a1/100);
		System.out.println(a2/100);
		System.out.println(a3/100);
		
		//System.out.println("Including lowest price: "+alowest);
		
	}

	@And("^user enter \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void userenter(String textbox, String rownum, String colnum) throws IOException, InterruptedException {
		String data = readexcel(driver, Integer.parseInt(rownum), Integer.parseInt(colnum));
//		if(spiritticket=="roundtrip") {
//			driver.findElement(By.xpath(readproperties(driver, "areturndatebox"))).clear();
//			sleep(driver, 1);
//			driver.findElement(By.xpath(readproperties(driver, "areturndatebox"))).sendKeys(data);
//			sleep(driver, 1);
//		}

		driver.findElement(By.xpath(readproperties(driver, textbox))).clear();
		sleep(driver, 1);
		driver.findElement(By.xpath(readproperties(driver, textbox))).sendKeys(data);
		sleep(driver, 1);
		
	}

	@And("^user select \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void userselect(String locator, String rownum, String colnum) throws IOException {
		
		Select item = new Select(driver.findElement(By.xpath(readproperties(driver, locator))));
//		int index = Integer.parseInt(readexcel(driver, Integer.parseInt(rownum), Integer.parseInt(colnum)));
//		item.selectByIndex(index-1);
		item.selectByValue(readexcel(driver, Integer.parseInt(rownum), Integer.parseInt(colnum)));
	}

	@And("^user select date \"([^\"]*)\"$")
	public void userselectdate(String date) throws IOException, InterruptedException {
//		if(unitedticket=="roundtrip") {
//			
//		}
		driver.findElement(By.xpath(readproperties(driver, date))).click();
	}

	@Then("^user will validate \"([^\"]*)\" \"([^\"]*)\"$")
	public void validate(String united, String spirit) throws IOException {
		
		System.out.println("United has low price of: "+unitedlow);
		
		int a= Integer.parseInt(spiritlow);
		System.out.println("Spirit has low price of: "+(a/100));
		
		int united1=Integer.parseInt(unitedlow);
		int spirit1=Integer.parseInt(spiritlow);
		
		//Assert.assertEquals("Prices are not the same!!!!", unitedlow, spiritlow);
		
		//Assert.assertTrue("Spirit is lower", (united1 > spirit1));
		Assert.assertTrue("United is lower", (spirit1 > united1));
	}

	@After
	public void teardown() throws Throwable {
		System.out.println("Testing is finished!!!!!");
		driver.manage().deleteAllCookies();
		sleep(driver, 5);
		driver.quit();
	}

}
