package p2.service;

import java.util.List;

import p2.dao.impl.ProductDAO;
import p2.model.Product;

public class ProductService {
	private static ProductDAO productDAO = new ProductDAO();

	public static int insert(Product t) {

		return productDAO.insert(t);
	}

	public static boolean update(Product t) {

		return productDAO.update(t);
	}

	public static List<Product> findAll() {
	
		return productDAO.findAll();
	}

	public static Product findById(int id) {

		return productDAO.findById(id);
	}

	public static boolean deleteById(int id) {

		return productDAO.deleteById(id);
	}

	public static List<Product> findAllProductsByTaxonomy(String name, String type, String subType) {
		return productDAO.findAllProductsByTaxonomy(name, type, subType);
	}

	public static List<Product> findAllProductsByType(String type) {
		return productDAO.findAllProductsByType(type);
	}

	public static List<Product> findAllProductsBySubType(String subType) {
		return productDAO.findAllProductsBySubType(subType);
	}

	public static List<Product> findAllProductsByTaxonomyName(String name) {
		return productDAO.findAllProductsByTaxonomyName(name);
	}

}
