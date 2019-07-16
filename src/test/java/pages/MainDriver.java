package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainDriver {
	
	public WebDriver driver;
	
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
	
}