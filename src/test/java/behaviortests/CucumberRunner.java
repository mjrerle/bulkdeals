package behaviortests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(

	features = {"src/test/resources/Login.feature", "src/test/resources/Register.feature", "src/test/resources/AddProduct.feature", "src/test/resources/BuyProduct.feature", "src/test/resources/LoginAlert.feature"},
	glue = {"behaviortests"}
	
)

public class CucumberRunner extends  AbstractTestNGCucumberTests{
	
	
}

