package behaviortests;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.MainDriver;

public class LoginStepImpl {
	
	static {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}
	
	static ChromeDriver driver = new ChromeDriver();
	static MainDriver mainDriver = new MainDriver(driver);
	
	@Given("^I am on the login page$")
		public void i_am_on_the_login_page() {
			driver.get("http://ec2-13-59-19-55.us-east-2.compute.amazonaws.com:8080/project2-frontend/login");
	}
	
	@Given("^email and password are entered$")
		public void email_and_password_are_entered() {
			mainDriver.findLoginEmail().sendKeys("test@test.test");
			mainDriver.findLoginPass().sendKeys("password");	
			}
	
	@When("^I click the login button$")
		public void i_click_the_login_button() {
			mainDriver.requestLogin().click();
		}
	
	
	@Then("^I am redirected to home$")
		public void i_am_redirected_to_home() {
			Assert.assertEquals(driver.getTitle(), "PrettyPenny");
		}
	
}
