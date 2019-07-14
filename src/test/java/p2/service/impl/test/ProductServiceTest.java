package p2.service.impl.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

//import junit.framework.Assert;
import p2.model.Product;
import p2.model.Taxonomy;
import p2.model.User;
import p2.service.ProductService;
import p2.service.TaxonomyService;
import p2.service.UserService;
import p2.util.Roles;

public class ProductServiceTest {

	private static int uid;
	private static int tid;
	private static int id;
	private static LocalDate now;
	private static List<Product> productList;
	private Product product;

	@BeforeSuite
	public void setup() {
		now = LocalDate.now();
		uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123",
				Roles.ADMIN.value, "abc", 123466, 322));
		tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
		product = new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold", 0, new Taxonomy(tid),
				new User(uid));
	}

	@Test
	public void aProductCanBeInserted() {
		id = ProductService.insert(product);
		product = ProductService.findById(id);
		Assert.assertTrue(product.getId() >= 0);
		ProductService.deleteById(id);
	}

	@Test
	public void aProductCanBeUpdated() {
		id = ProductService.insert(product);
		Product updateProduct = ProductService.findById(id);
		double price = 22.00;
		updateProduct.setPrice(price);
		Assert.assertEquals(ProductService.update(updateProduct), true);
		updateProduct = ProductService.findById(id);
		Assert.assertEquals(updateProduct.getPrice(), price);
		ProductService.deleteById(id);
	}

	@Test
	public void allProductsCanBeFound() {
		id = ProductService.insert(product);
		int id2 = ProductService.insert(product);
		int id3 = ProductService.insert(product);

		productList = ProductService.findAll();
		Assert.assertEquals(productList.size(), 3);
		ProductService.deleteById(id);
		ProductService.deleteById(id2);
		ProductService.deleteById(id3);
	}

	@Test
	public void aProductCanBeFoundById() {
		id = ProductService.insert(product);
		product = ProductService.findById(id);
		Assert.assertTrue(product.getId() >= 0);
		ProductService.deleteById(id);
	}

	@Test
	public void aProductCanBeDeleted() {
		id = ProductService.insert(product);
		Product product = ProductService.findById(id);
		ProductService.deleteById(product.getId());
	}

	@Test(priority = 2)
	public void productsCanBeFoundByTaxName() {
		List<Product> products = new ArrayList<>();
		// int tid1 = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
		Product prod = new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold", 0,
				new Taxonomy(tid), new User(uid));
		// int tid2 = TaxonomyService.insert(new Taxonomy("Apple", "Electronics", "Phones"));

		// add three products with the same tax
		int pid1 = ProductService.insert(prod);
		int pid2 = ProductService.insert(prod);
		int pid3 = ProductService.insert(prod);
		// product.setTaxonomy(new Taxonomy(tid2));

		// add one product with a different tax
		// int pid4 = ProductService.insert(product);

		products = ProductService.findAllProductsByTaxonomyName("Samsung");
		Assert.assertEquals(products.size(), 3);
		// cleanup
		// TaxonomyService.deleteById(tid1);
		// TaxonomyService.deleteById(tid2);

		ProductService.deleteById(pid1);
		ProductService.deleteById(pid2);
		ProductService.deleteById(pid3);
		// ProductService.deleteById(pid4);

	}

	@Test(priority = 1)
	public void productsCanBeFoundByType() {
		List<Product> products = new ArrayList<>();
		// int tid1 = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
		// Product product = new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold", 0,
		// 		new Taxonomy(tid1), new User(uid));
		// int tid2 = TaxonomyService.insert(new Taxonomy("Samsung", "Military Technology", "Missiles"));

		// add three products with the same tax
		int pid1 = ProductService.insert(product);
		int pid2 = ProductService.insert(product);
		int pid3 = ProductService.insert(product);

		// product.setTaxonomy(new Taxonomy(tid2));

		// add one product with a different tax
		// int pid4 = ProductService.insert(product);

		products = ProductService.findAllProductsByType("Electronics");
		Assert.assertEquals(products.size(), 3);
		// cleanup
		// TaxonomyService.deleteById(tid1);
	
		// TaxonomyService.deleteById(tid2);

		ProductService.deleteById(pid1);
		ProductService.deleteById(pid2);
		ProductService.deleteById(pid3);
		// ProductService.deleteById(pid4);
		product.setTaxonomy(new Taxonomy(tid));


	}

	// @Test
	// public void productsCanByFoundBySubType() {
	// 	List<Product> products = new ArrayList<>();
	// 	int tid1 = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
	// 	Product prod = new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold", 0,
	// 			new Taxonomy(tid1), new User(uid));
	// 	int tid2 = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Smart Fridges"));

	// 	// add three products with the same tax
	// 	int pid1 = ProductService.insert(prod);
	// 	int pid2 = ProductService.insert(prod);
	// 	int pid3 = ProductService.insert(prod);
	// 	prod.setTaxonomy(new Taxonomy(tid2));

	// 	// add one product with a different tax
	// 	int pid4 = ProductService.insert(prod);

	// 	prod.setTaxonomy(new Taxonomy(tid2));

	// 	products = ProductService.findAllProductsBySubType("Phones");
	// 	Assert.assertEquals(products.size(), 3);
	// 	// cleanup
	// 	TaxonomyService.deleteById(tid1);
	// 	TaxonomyService.deleteById(tid2);

	// 	ProductService.deleteById(pid1);
	// 	ProductService.deleteById(pid2);
	// 	ProductService.deleteById(pid3);
	// 	ProductService.deleteById(pid4);
	// }

	@AfterSuite
	public void teardown() {
		UserService.deleteById(uid);
		TaxonomyService.deleteById(tid);
	}
}
