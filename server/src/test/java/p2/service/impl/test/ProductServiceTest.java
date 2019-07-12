package p2.service.impl.test;

import p2.model.User;

import p2.dao.impl.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

//import junit.framework.Assert;
import p2.model.Product;

public class ProductServiceTest {
	
	public static User user;
	public static Product product;
	private static ProductDAO productDAO;
	private static List<Product> pList;
	@BeforeSuite
	public void setup() {
		user = new User(9999);
		product = new Product();
		product.setSeller(user);
		product.setPrice(25.00);	
		productDAO = new ProductDAO();
	}
	
	@Test(priority=1)
	public void insertProduct() {
		int test = -1;
		test = productDAO.insert(product);
		Assert.assertTrue(test != -1);;
	}
	
	@Test(priority=2)
	public void updateProduct() {
		product.setPrice(22.00);
		Assert.assertEquals(productDAO.update(product), true);
	}
	
	@Test(priority=3)
	public void findAllProduct(){
		pList = productDAO.findAll();
		Assert.assertEquals(pList.get(0).getPrice(), 22.00);
	}
	
	@Test(priority=4)
	public void findProductById() {
		product.setId(pList.get(0).getId());
		Product found = productDAO.findById(product.getId());
		Assert.assertTrue(product.getId() == found.getId());
	}
	
	@Test(priority=5)
	public void deleteProduct() {
		Assert.assertTrue(productDAO.deleteById(product.getId()) == true);
	}
	

}
