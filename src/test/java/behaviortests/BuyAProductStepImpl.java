package behaviortests;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.cli.Main;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.MainDriver;

public class BuyAProductStepImpl {

	static {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}

	static ChromeDriver driver = new ChromeDriver();
	static MainDriver mainDriver = new MainDriver(driver);

	// -------------------------------------------------------------------------
	// Scenario: Filter Sumsung brand product

	@Given("^I am on products page$")
	public void i_am_on_products_page() throws Throwable {
		driver.get("http://localhost:4200/login");
		mainDriver.findLoginEmail().sendKeys("user@example.com");
		mainDriver.findLoginPass().sendKeys("password");	
		mainDriver.requestLogin().click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.get(MainDriver.homeUrl + "products");
	}

	@When("^I click samung fliter by brand name$")
	public void i_click_samung_fliter_by_brand_name() throws Throwable {
		mainDriver.findSamsungFilter().click();
		sleep();
	}

	@Then("^I shoud see only Samsung produts$")
	public void i_shoud_see_only_Samsung_produts() throws Throwable {
		String productName = mainDriver.findFirstProduct().getText();
		boolean isFound = productName.indexOf("Samsung") != -1 ? true : false;
		Assert.assertEquals(isFound, true);
	}

	// -------------------------------------------------------------------------
	// Scenario: Add to card Sumsung brand product

	@When("^I click add to card on product$")
	public void when_I_click_add_to_card() throws Throwable {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mainDriver.findFirstProductAddCart().click();
		sleep();
	}

	@Then("^Product should be on cart$")
	public void product_should_be_on_cart() throws Throwable {
		mainDriver.findLoginEmail().sendKeys("seller@example.com");
		mainDriver.findLoginPass().sendKeys("password");
		mainDriver.requestLogin().click();
		sleep();
		Assert.assertEquals(mainDriver.findCartNumberOfItems().getText(), "1");
	}

	// -------------------------------------------------------------------------
	// Chekout the Cart

	@Given("^I am on cart page$")
	public void i_am_on_cart_page() throws Throwable {
		mainDriver.findCart().click();
		sleep();
	}

	@When("^I click checkout button$")
	public void i_click_checkout_button() throws Throwable {
		mainDriver.findCheoutButton().click();
		sleep();
	}

	@Then("^Cart shoud be empty$")
	public void cart_shoud_be_empty() throws Throwable {
		Assert.assertEquals(mainDriver.findCartNumberOfItems().getText(), "0");
		sleep();
	}

	// -------------------------------------------------------------------------
	// Hold the automation
	private void sleep() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
