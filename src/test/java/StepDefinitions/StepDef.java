package StepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.random.RandomGenerator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.formula.atp.Switch;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

import com.github.javafaker.Faker;

import PageObject.AddCustomer;
import PageObject.Login;
import PageObject.SearchCustomer;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef extends BaseClass {

	@Before(order=2)
	public void setup() {
		//@Before(@Sanity) - executes only for sanity tc- conditional hook
		System.out.println("Before setup 1");
		
		log= LogManager.getLogger("StepDef");
		
		
		
		config= new Properties();
		try {
			config.load(new FileInputStream("config.properties"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String browser= config.getProperty("browser");
		if(browser.equals("chrome"))
			driver = new ChromeDriver();
		else if( browser.equals("edge")) 
			driver = new EdgeDriver();
		else
			Assert.assertTrue("provide browser in properties file", true);
			
		
	}
	
//	@Before(order=1)
//	public void setup2() {
//		System.out.println("Before setup 2");
//		//driver = new ChromeDriver();
//	}
//	
	@Given("User launch Google Chrome")
	public void user_launch_google_chrome() {
	
		loginPage = new Login(driver);
		addcustomerPage = new AddCustomer(driver);
		searchCust = new SearchCustomer(driver);
		log.info("launching browser");
	}

	@When("User opens the url {string}")
	public void user_opens_the_url(String url) {
		driver.get(url);
		log.info("opening the url");
	}

	@When("user enters email as {string} & password as {string}")
	public void user_enters_email_as_password_as(String email, String pass) {
		loginPage.setEmail(email);
		loginPage.setPassword(pass);
		log.info("entering email and password");
	}

	@When("click on login button")
	public void click_on_login_button() {
		loginPage.clickLogin();
		log.info("Click on login");
	}

	@Then("Page title should be {string}")
	public void page_title_should_be(String title) {
		if (driver.getTitle().equals(title)) {
			Assert.assertTrue(true);
			log.info("PageTitle verified");
		} else {
			Assert.assertTrue(false);
			log.error("Error - page title not verified");
		}
	}

	@When("user clicks logout button")
	public void user_clicks_logout_button() {
		loginPage.clickLogout();
		log.info("logged out");
	}

	@Then("user closes the browser")
	public void user_closes_the_browser() {
		driver.close();
		log.info("closing browser");
	}

	@When("user clicks on Customers menu")
	public void user_clicks_on_customers_menu() {
		loginPage.clickCustomerMenu();
	}

	@When("user clicks on Customers menu item")
	public void user_clicks_on_customers_menu_item() {
		loginPage.clickCustomerMenuItem();
	}

	@When("user clicks on add new button")
	public void user_clicks_on_add_new_button() {
		addcustomerPage.clickAddNew();
	}

	@Then("user can view add new customer page")
	public void user_can_view_add_new_customer_page() {
		Assert.assertEquals("Add new Customer Page not verified!", driver.getTitle(),
				"Add a new customer / nopCommerce administration");
	}

	@When("user enters details of new customer")
	public void user_enters_details_of_new_customer() {
		addcustomerPage.enterEmail(faker.internet().emailAddress());
		addcustomerPage.enterPassword("test1");
		addcustomerPage.enterFirstName(faker.name().firstName());
		addcustomerPage.enterLastName(faker.name().lastName());
		addcustomerPage.enterGender("Female");
		addcustomerPage.enterDob("6/13/1988");
		addcustomerPage.enterCompanyName("CodeStudio");
		addcustomerPage.enterAdminContent("Admin content");
		addcustomerPage.enterManagerOfVendor("Vendor 1");

	}

	@When("click on save button")
	public void click_on_save_button() {
		addcustomerPage.clickOnSave();
	}

	@Then("User can view the confirmation message {string}")
	public void user_can_view_the_confirmation_message(String exptectedConfirmationMsg) {

		String bodyTagText = driver.findElement(By.tagName("Body")).getText();
		if (bodyTagText.contains(exptectedConfirmationMsg)) {
			Assert.assertTrue(true);// pass

		} else {
			Assert.assertTrue(false);// fail

		}
	}
	

////////////Search Customer//////////////////////////
	@When("enter customer email")
	public void enter_customer_email() {
		searchCust.enterEmailAdd("victoria_victoria@nopCommerce.com");
	}

	@When("Click on search button")
	public void click_on_search_button() {
		searchCust.clickOnSearchButton();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
		String expectedEmail = "victoria_victoria@nopCommerce.com";

//   Assert.assertTrue(SearchCustPg.searchCustomerByEmail(expectedEmail));

		if (searchCust.searchCustomerByEmail(expectedEmail) == true) {
			Assert.assertTrue(true);
			

		} else {
			
			Assert.assertTrue(false);

		}

	}

///////////////search customer by name////////////////////

	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		searchCust.enterFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		searchCust.enterLastName("Terces");

	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		String expectedName = "Victoria Terces";

		if (searchCust.searchCustomerByName(expectedName) == true) {
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);

	}
	
	@After(order=2)
	public void tearDown(Scenario sc) {
		if(sc.isFailed()==true) {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String filepath="E:\\eclipse\\CucumberFramework\\Screenshot\\failed"+timeStamp+".png";
			TakesScreenshot ts= (TakesScreenshot)driver;
			File ss= ts.getScreenshotAs(OutputType.FILE);
			
			File target= new File(filepath);
			try {
				FileUtils.copyFile(ss, target);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.quit();
		System.out.println("tear1");
		//for multiple after - order value higher executed first
	}

//	@After(order=1)
//	public void tearDown2() {
//		System.out.println("tear2");
//	}
	
//	@BeforeStep
//	public void before_every_step() {
//		System.out.println("before step --");
//	}
//	
//	@AfterStep
//	public void after_every_step() {
//		System.out.println("after step --");
//	}
}
