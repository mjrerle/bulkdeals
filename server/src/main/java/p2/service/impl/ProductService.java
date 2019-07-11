package p2.service.impl;
import java.util.List;
import p2.dao.impl.ProductDAO;
import p2.model.Product;


public class ProductService{


	public static int insert(Product t) {
		ProductDAO p = new ProductDAO();
		return p.insert(t);
	}

	public static void update(Product t) {
		ProductDAO p = new ProductDAO();
		p.update(t);
	}


	public static List<Product> findAll() {
		ProductDAO p = new ProductDAO();
		return p.findAll();
	}


	public static Product findById(int id) {
		ProductDAO p = new ProductDAO();
		return p.findById(id);
	}


	public static void deleteById(int id) {
		ProductDAO p = new ProductDAO();
		p.deleteById(id);
	}
	

}
