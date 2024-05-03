package StepDefinitions;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;

import PageObject.AddCustomer;
import PageObject.Login;
import PageObject.SearchCustomer;

public class BaseClass {
	public WebDriver driver;
	public Login loginPage;
	public AddCustomer addcustomerPage;
	public SearchCustomer searchCust;
	public Faker faker = new Faker();
	public static Logger log;
	public Properties config;
}
