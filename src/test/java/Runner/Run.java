package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = ".//Features/Login.feature",
				//features = {".//Features/Customers.feature",".//Features/Customers.feature"}
				//features = ".//Features/",
				glue="StepDefinitions",
				tags="@Sanity",  //@Sanity or @Regression , we can use and , @Sanity and not @Regression
				dryRun = false,
				monochrome=true,
				plugin= {"pretty","html:target/cucumber-reports/report.html"})
public class Run {

}
