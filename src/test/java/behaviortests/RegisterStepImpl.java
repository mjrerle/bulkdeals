package behaviortests;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
import p2.model.User;
import p2.dao.impl.UserDAO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.MainDriver;

public class RegisterStepImpl {
	
	static {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}
	
	static ChromeDriver driver = new ChromeDriver();
	static MainDriver mainDriver = new MainDriver(driver);
	static UserDAO useful = new UserDAO();
	
	@Given("^I am on the register page$")
		public void i_am_on_the_register_page() {
			driver.get("http://ec2-13-59-19-55.us-east-2.compute.amazonaws.com:8080/project2-frontend/login");
	}
	
	@Given("^registration fields are correctly filled$")
		public void registration_fields_are_correctly_filled() {
			mainDriver.findRegisterEmail().sendKeys("test@test.test");
			mainDriver.findRegisterPass().sendKeys("password");	
			mainDriver.findRegisterFname().sendKeys("john");
			mainDriver.findRegisterLname().sendKeys("doe");
			mainDriver.findRegisterAddress().sendKeys("No ty");
			mainDriver.findCreditCardNumber().sendKeys("1346789");
			mainDriver.findCVV().sendKeys("123");
			}
	
	@When("^I click on submit registration$")
		public void i_click_on_submit_registration() {
			mainDriver.requestRegistration().click();
		}

	@Then("^I am redirected home or given an email taken error$")
		public void i_am_redirected_home_or_given_an_email_taken_error() {
			User tested = useful.findByEmailAndPassword("test@test.test", "psssword");
			useful.deleteById(tested.getId());
			Assert.assertEquals(driver.getTitle(), "PrettyPenny");
			
			
		}
	

}
