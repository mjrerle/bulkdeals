package behaviortests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.MainDriver;

public class AddProductStepImpl {
	
	static {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}
	
	static ChromeDriver driver = new ChromeDriver();
	static MainDriver mainDriver = new MainDriver(driver);
	
	@Given("^I am logged in as a seller and on the add product page$")
	public void i_am_logged_in_as_a_seller_and_on_the_add_product_page() {
		driver.get("http://localhost:4200/login");
		mainDriver.findLoginEmail().sendKeys("testSeller@test.test");
		mainDriver.findLoginPass().sendKeys("password");
		mainDriver.requestLogin().click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		driver.get("http://localhost:4200/addproduct");
	}
	
	@Given("^I have entered the necessary values$")
	public void i_have_entered_the_necessary_values() {
		driver.findElement(By.name("productName")).sendKeys("Apple Windows Phone");
		Select brName = new Select(driver.findElement(By.name("brandName")));
		brName.selectByVisibleText("Apple");
		Select brType = new Select(driver.findElement(By.name("brandType")));
		brType.selectByVisibleText("Electronics");
		Select brType2 = new Select(driver.findElement(By.name("brandSubType")));
		brType2.selectByVisibleText("Phone");
		driver.findElement(By.name("imagePath")).sendKeys("https://i2.wp.com/www.onmsft.com/wp-content/uploads/2018/02/Screen-Shot-2018-02-19-at-17.28.19.png?fit=831%2C580&ssl=1");
		driver.findElement(By.name("productDescription")).sendKeys("LOL you thought this was real I bet");
		driver.findElement(By.id("listTypePretty")).click();
		driver.findElement(By.name("msrp")).sendKeys("2.00");
	}
	
	@When("^I submit the product at standard sale$")
	public void i_submit_the_product_at_standard_price() {
		driver.findElement(By.className("btn btn-primary")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
	}
	
	@Then("^I receive a product added notification$")
	public void i_receive_a_product_added_notification() {
		//placeholder until i know how it works
		Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Welcome to PrettyPenny");
	}


}
