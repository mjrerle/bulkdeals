package p2.service.impl;
import java.util.List;
import p2.dao.impl.ProductDAO;
import p2.model.Product;


public class ProductService{
	private static ProductDAO productDAO = new ProductDAO();

	public static int insert(Product t) {

		return productDAO.insert(t);
	}

	public static void update(Product t) {

		productDAO.update(t);
	}


	public static List<Product> findAll() {

		return productDAO.findAll();
	}


	public static Product findById(int id) {

		return productDAO.findById(id);
	}


	public static void deleteById(int id) {

		productDAO.deleteById(id);
	}
	

}
