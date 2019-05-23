package Runner;



import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Feature/UnitedSpirit.feature", glue = {
		"Step" }, format = { "pretty", "html:target/cucumber" }, tags="@Smoke")

public class Runner extends AbstractTestNGCucumberTests {

}
