package behaviortests;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.MainDriver;

public class LoginAlertStepImpl {
	
	static {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}
	
	static ChromeDriver driver = new ChromeDriver();
	static MainDriver mainDriver = new MainDriver(driver);
	
	@Given("^I am on the login page again$")
	public void i_am_on_the_login_page_again() {
		driver.get("http://ec2-18-221-142-60.us-east-2.compute.amazonaws.com:8080/project2-frontend/login");
	}
	
	@Given("^email is entered$")
	public void email_is_entered() {
		mainDriver.findLoginEmail().sendKeys("test@test.test");
		}
	
	@When("^I click the login button again$")
	public void i_click_the_login_button_again() {
		mainDriver.requestLogin().click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

	}
	
	@Then("^I get an alert$")
	public void i_get_an_alert() {
			Assert.assertEquals(driver.findElement(By.id("genAlert")).getText(), "Must enter a password.");
	}

}
