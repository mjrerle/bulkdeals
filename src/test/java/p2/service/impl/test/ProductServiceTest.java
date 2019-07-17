package p2.service.impl.test;

import java.time.LocalDate;
import java.util.List;

import org.testng.Assert;
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

	@Test
	public void aProductCanBeInserted() {
		LocalDate now = LocalDate.now();
		int uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123",
				Roles.ADMIN.value, "abc", 123466, 322));
		int tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
		Product product = new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold", 0,
				new Taxonomy(tid), new User(uid));
		int id = ProductService.insert(product);
		product = ProductService.findById(id);
		Assert.assertTrue(product.getProductId() >= 0);
		ProductService.deleteById(id);
		UserService.deleteById(uid);
		TaxonomyService.deleteById(tid);
	}

	@Test
	public void aProductCanBeUpdated() {
		LocalDate now = LocalDate.now();
		int uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123",
				Roles.ADMIN.value, "abc", 123466, 322));
		int tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
		Product product = new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold", 0,
				new Taxonomy(tid), new User(uid));
		int id = ProductService.insert(product);
		Product updateProduct = ProductService.findById(id);
		double price = 22.00;
		updateProduct.setPrice(price);
		Assert.assertEquals(ProductService.update(updateProduct), true);
		updateProduct = ProductService.findById(id);
		Assert.assertEquals(updateProduct.getPrice(), price);
		ProductService.deleteById(id);
		UserService.deleteById(uid);
		TaxonomyService.deleteById(tid);
	}

	@Test
	public void allProductsCanBeFound() {
		LocalDate now = LocalDate.now();
		int uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123",
				Roles.ADMIN.value, "abc", 123466, 322));
		int tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
		Product product = new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold", 0,
				new Taxonomy(tid), new User(uid));
		int id = ProductService.insert(product);
		int id2 = ProductService.insert(product);
		int id3 = ProductService.insert(product);

		List<Product> productList = ProductService.findAll();
		Assert.assertEquals(productList.size(), 3);
		ProductService.deleteById(id);
		ProductService.deleteById(id2);
		ProductService.deleteById(id3);
		UserService.deleteById(uid);
		TaxonomyService.deleteById(tid);
	}

	@Test
	public void aProductCanBeFoundById() {
		LocalDate now = LocalDate.now();
		int uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123",
				Roles.ADMIN.value, "abc", 123466, 322));
		int tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
		Product product = new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold", 0,
				new Taxonomy(tid), new User(uid));
		int id = ProductService.insert(product);
		product = ProductService.findById(id);
		Assert.assertTrue(product.getProductId() >= 0);
		ProductService.deleteById(id);
		UserService.deleteById(uid);
		TaxonomyService.deleteById(tid);
	}

	@Test
	public void aProductCanBeDeleted() {
		LocalDate now = LocalDate.now();
		int uid = UserService.insert(new User("vajira", "Hapu Arachchige", "vajirakarunathilake@gmail.com", "abc123",
				Roles.ADMIN.value, "abc", 123466, 322));
		int tid = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
		Product product = new Product("name", "description", 190, 190, 0, 0, "url", now, "Within Threshold", 0,
				new Taxonomy(tid), new User(uid));

		int id = ProductService.insert(product);
		product = ProductService.findById(id);
		ProductService.deleteById(product.getProductId());
		UserService.deleteById(uid);
		TaxonomyService.deleteById(tid);
	}

	@Test
	public void productsCanBeFoundByTaxName() {
		LocalDate now1 = LocalDate.now();
		int tid1 = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
		int tid2 = TaxonomyService.insert(new Taxonomy("Apple", "Electronics", "Phones"));
		int uid1 = UserService.insert(
				new User("vajira", "Hapu Arachchige", "sadkflj@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
		Product prod = new Product("name", "description", 190, 190, 0, 0, "url", now1, "Within Threshold", 0,
				new Taxonomy(tid1), new User(uid1));

		// add three products with the same tax
		int pid1 = ProductService.insert(prod);
		int pid2 = ProductService.insert(prod);
		int pid3 = ProductService.insert(prod);

		// one product with a different tax ;))
		int pid4 = ProductService.insert(new Product("name", "description", 190, 190, 0, 0, "url", now1, "Within Threshold",
				0, new Taxonomy(tid2), new User(uid1)));

		List<Product> products = ProductService.findAllProductsByTaxonomyName("Samsung");
		Assert.assertEquals(products.size(), 3);
		// cleanup
		ProductService.deleteById(pid1);
		ProductService.deleteById(pid2);
		ProductService.deleteById(pid3);
		ProductService.deleteById(pid4);

		UserService.deleteById(uid1);

		TaxonomyService.deleteById(tid2);

		TaxonomyService.deleteById(tid1);

	}

	@Test
	public void productsCanBeFoundByType() {
		LocalDate now1 = LocalDate.now();
		int tid1 = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
		int tid2 = TaxonomyService.insert(new Taxonomy("Apple", "User Data", "IP Addresses"));
		int uid1 = UserService.insert(
				new User("vajira", "Hapu Arachchige", "dsfsd@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
		Product prod = new Product("name", "description", 190, 190, 0, 0, "url", now1, "Within Threshold", 0,
				new Taxonomy(tid1), new User(uid1));

		// add three products with the same tax
		int pid1 = ProductService.insert(prod);
		int pid2 = ProductService.insert(prod);
		int pid3 = ProductService.insert(prod);

		// one product with a different tax ;))
		int pid4 = ProductService.insert(new Product("name", "description", 190, 190, 0, 0, "url", now1, "Within Threshold",
				0, new Taxonomy(tid2), new User(uid1)));

		List<Product> products = ProductService.findAllProductsByType("Electronics");
		Assert.assertEquals(products.size(), 3);
		// cleanup
		ProductService.deleteById(pid1);
		ProductService.deleteById(pid2);
		ProductService.deleteById(pid3);
		ProductService.deleteById(pid4);

		UserService.deleteById(uid1);
		TaxonomyService.deleteById(tid2);

		TaxonomyService.deleteById(tid1);

	}

	@Test
	public void productsCanByFoundBySubType() {
		LocalDate now1 = LocalDate.now();
		int tid1 = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
		int tid2 = TaxonomyService.insert(new Taxonomy("Apple", "Electronics", "Laptops"));
		int uid1 = UserService.insert(
				new User("vajira", "Hapu Arachchige", "sfasda@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
		Product prod = new Product("name", "description", 190, 190, 0, 0, "url", now1, "Within Threshold", 0,
				new Taxonomy(tid1), new User(uid1));

		// add three products with the same tax
		int pid1 = ProductService.insert(prod);
		int pid2 = ProductService.insert(prod);
		int pid3 = ProductService.insert(prod);
		// one product with a different tax ;))
		int pid4 = ProductService.insert(new Product("name", "description", 190, 190, 0, 0, "url", now1, "Within Threshold",
				0, new Taxonomy(tid2), new User(uid1)));

		List<Product> products = ProductService.findAllProductsBySubType("Phones");
		Assert.assertEquals(products.size(), 3);
		// cleanup
		ProductService.deleteById(pid1);
		ProductService.deleteById(pid2);
		ProductService.deleteById(pid3);
		ProductService.deleteById(pid4);
		UserService.deleteById(uid1);

		TaxonomyService.deleteById(tid2);
		TaxonomyService.deleteById(tid1);

	}

	@Test
	public void productsCanBeFoundByAnyTaxValue() {
		LocalDate now1 = LocalDate.now();
		int tid1 = TaxonomyService.insert(new Taxonomy("Samsung", "Electronics", "Phones"));
		int tid2 = TaxonomyService.insert(new Taxonomy("Apple", "Electronics", "Phones"));
		int uid1 = UserService.insert(
				new User("vajira", "Hapu Arachchige", "sfasda@gmail.com", "abc123", Roles.ADMIN.value, "abc", 123466, 322));
		Product prod = new Product("name", "description", 190, 190, 0, 0, "url", now1, "Within Threshold", 0,
				new Taxonomy(tid1), new User(uid1));

		// add three products with the same tax
		int pid1 = ProductService.insert(prod);
		int pid2 = ProductService.insert(prod);
		int pid3 = ProductService.insert(prod);
		int pid4 = ProductService.insert(new Product("name", "description", 190, 190, 0, 0, "url", now1, "Within Threshold",
				0, new Taxonomy(tid2), new User(uid1)));

		List<Product> products = ProductService.findAllProductsByTaxonomy("Samsung", "Electronics", "Phones");
		Assert.assertEquals(products.size(), 3);

		products = ProductService.findAllProductsByTaxonomy("Apple", "Electronics", "Phones");
		Assert.assertEquals(products.size(), 1);

		products = ProductService.findAllProductsByTaxonomy("Yo!", "Its", "Matt :O");
		Assert.assertEquals(products.size(), 0);

		products = ProductService.findAllProductsByTaxonomy("%", "%", "%");
		Assert.assertEquals(products.size(), 4);
		// cleanup
		ProductService.deleteById(pid1);
		ProductService.deleteById(pid2);
		ProductService.deleteById(pid3);
		ProductService.deleteById(pid4);
		UserService.deleteById(uid1);

		TaxonomyService.deleteById(tid2);
		TaxonomyService.deleteById(tid1);

	}
}
