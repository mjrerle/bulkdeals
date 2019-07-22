package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainDriver {

	public WebDriver driver;
	// public static String homeUrl = "http://localhost:4200/";
	public static String homeUrl = "http://ec2-18-221-142-60.us-east-2.compute.amazonaws.com:8080/project2-frontend/";

	public MainDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement requestLogin() {
		return driver.findElement(By.id("logBtn"));
	}

	public WebElement requestRegistration() {
		return driver.findElement(By.id("sbmtBtn"));
	}

	public WebElement findLoginEmail() {
		return driver.findElement(By.id("emailL"));
	}

	public WebElement findLoginPass() {
		return driver.findElement(By.id("passwordL"));
	}

	public WebElement findRegisterEmail() {
		return driver.findElement(By.id("emailR"));
	}

	public WebElement findRegisterPass() {
		return driver.findElement(By.id("password"));
	}

	public WebElement findRegisterFname() {
		return driver.findElement(By.id("firstname"));
	}

	public WebElement findRegisterLname() {
		return driver.findElement(By.id("lastname"));
	}

	public WebElement findRegisterAddress() {
		return driver.findElement(By.id("address"));
	}

	public WebElement findCreditCardNumber() {
		return driver.findElement(By.id("creditcardnumber"));
	}

	public WebElement findCVV() {
		return driver.findElement(By.id("ccv"));
	}

	public WebElement findSamsungFilter() {
		return driver.findElement(By.linkText("Samsung"));
	}

	public WebElement findFirstProduct() {
		return driver.findElement(By.xpath(
				"/html/body/app-root/section/div/app-products/div/div/div/app-product-card[1]/div/header/div/div[1]/a"));
	}
	
	public WebElement findFirstProductAddCart() {
		return driver.findElement(By.xpath(
				"/html/body/app-root/section/div/app-products/div/div/div/app-product-card[1]/div/footer/div/button"));
	}

	public WebElement findCart() {
		return driver.findElement(By.xpath("//*[@id=\"header-navbar\"]/app-cartmini/div/a"));
	}
	
	public WebElement findCartNumberOfItems() {
		return driver.findElement(By.xpath("//*[@id=\"header-navbar\"]/app-cartmini/div/a/span[2]"));
	}

	public WebElement findCheoutButton() {
		return driver.findElement(By.xpath("/html/body/app-root/section/div/app-cart/div/div[2]/div/div[2]/a"));
	}

}