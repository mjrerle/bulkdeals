package behaviortests;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(

	features = {"src/test/resources/Login.feature", "src/test/resources/Register.feature"},
	glue = {"behaviortests"}
	
)

public class CucumberRunner extends  AbstractTestNGCucumberTests{
	
	
}

