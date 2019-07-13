package p2.service.impl.test;

import java.time.LocalDate;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

//import junit.framework.Assert;
import p2.model.Product;
import p2.model.User;
import p2.service.ProductService;
import p2.service.UserService;
import p2.util.Roles;

public class ProductServiceTest {
	
	private static int uid;
	private static int id;
	private static LocalDate now;
	private static List<Product> productList;
	@BeforeSuite
	public void setup() {
		now = LocalDate.now();
		uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
			
	}
	
	@Test(priority=1)
	public void aProductCanBeInserted() {
		id = ProductService.insert(new Product("abc", 190, 190, 0, 0, now, "Within Threshold", 0, new User(uid)));
		Product product = ProductService.findById(id);
		Assert.assertTrue(product.getId() >= 0);
		ProductService.deleteById(id);
	}
	
	@Test(priority=2)
	public void updateProduct() {
		id = ProductService.insert(new Product("abc", 190, 190, 0, 0, now, "Within Threshold", 0, new User(uid)));
		Product product = ProductService.findById(id);
		double price = 22.00;
		product.setPrice(price);
		Assert.assertEquals(ProductService.update(product), true);
		product = ProductService.findById(id);
		Assert.assertEquals(product.getPrice(), price);
		ProductService.deleteById(id);
	}
	
	@Test(priority=3)
	public void findAllProduct(){
		id = ProductService.insert(new Product("abc", 190, 190, 0, 0, now, "Within Threshold", 0, new User(uid)));
		int id2 = ProductService.insert(new Product("abc", 190, 190, 0, 0, now, "Within Threshold", 0, new User(uid)));
		int id3 = ProductService.insert(new Product("abc", 190, 190, 0, 0, now, "Within Threshold", 0, new User(uid)));

		productList = ProductService.findAll();
		Assert.assertEquals(productList.size(), 3);
		ProductService.deleteById(id);
		ProductService.deleteById(id2);
		ProductService.deleteById(id3);

	}
	
	@Test(priority=4)
	public void findProductById() {
		id = ProductService.insert(new Product("abc", 190, 190, 0, 0, now, "Within Threshold", 0, new User(uid)));
		Product product = ProductService.findById(id);
		Assert.assertTrue(product.getId()>= 0);
		ProductService.deleteById(id);
	}
	
	@Test(priority=5)
	public void deleteProduct() {
		id = ProductService.insert(new Product("abc", 190, 190, 0, 0, now, "Within Threshold", 0, new User(uid)));
		Product product = ProductService.findById(id);
		ProductService.deleteById(id);
	}
}
