package behaviortests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import p2.dao.impl.UserDAO;
import pages.MainDriver;
import org.openqa.selenium.support.ui.Select;
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
			driver.get("http://localhost:4200/login");
	}
	
	@Given("^registration fields are correctly filled$")
		public void registration_fields_are_correctly_filled() {
			mainDriver.findRegisterEmail().sendKeys("test@test.test");
			mainDriver.findRegisterPass().sendKeys("password");	
			mainDriver.findRegisterFname().sendKeys("john");
			mainDriver.findRegisterLname().sendKeys("doe");
			mainDriver.findRegisterAddress().sendKeys("No ty");
			mainDriver.findCreditCardNumber().sendKeys("1346");
			Select role = new Select(driver.findElement(By.name("role")));
			role.selectByVisibleText("USER");
			mainDriver.findCVV().sendKeys("123");
			}
	
	@When("^I click on submit registration$")
		public void i_click_on_submit_registration() {
			mainDriver.requestRegistration().click();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	@Then("^I am redirected home or given an email taken error$")
		public void i_am_redirected_home_or_given_an_email_taken_error() {
		if (driver.findElement(By.tagName("h1")).getText().equals("Welcome to PrettyPenny")){
			Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Welcome to PrettyPenny");
		}
		else {
			Assert.assertEquals(driver.findElement(By.id("passer")).getText(), "Invalid Entry/Email Taken");
		}
		}
	

}
