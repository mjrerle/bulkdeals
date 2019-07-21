package behaviortests;

import org.testng.annotations.AfterSuite;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(

		features = { "src/test/resources/Login.feature", "src/test/resources/Register.feature",
				"src/test/resources/AddProduct.feature",
				"src/test/resources/BuyProduct.feature" }, glue = { "behaviortests" }

)

public class CucumberRunner extends AbstractTestNGCucumberTests {

	@AfterSuite
	public void teardown() {
		LoginStepImpl.driver.quit();
		RegisterStepImpl.driver.quit();
		AddProductStepImpl.driver.quit();
		BuyAProductStepImpl.driver.quit();
	}

}
