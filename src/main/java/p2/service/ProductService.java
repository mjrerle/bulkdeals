package p2.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import p2.dao.impl.ProductDAO;
import p2.model.Interest;
import p2.model.Product;
import p2.util.ThresholdStatus;

public class ProductService {
	private static ProductDAO productDAO = new ProductDAO();

	public static int insert(Product t) {

		return productDAO.insert(t);
	}

	public static boolean update(Product t) {

		return productDAO.update(t);
	}

	public static List<Product> findAll() {
		List<Product> allProducts = productDAO.findAll();
		LocalDate today = LocalDate.now();
		int maximumThresholdDays = 7;

		if (allProducts != null) {
			for (Product product : allProducts) {
				// update the number of interest
				List<Interest> interestsForProduct = InterestService.findByProductId(product.getProductId());
				int sum = 0;
				if (interestsForProduct != null) {
					for (Interest interest : interestsForProduct) {
						sum += interest.getQuantity();
					}
					product.setGeneratedInterest(sum);
				}

				// update the status of product
				if (product.getStatus().equals(ThresholdStatus.WITHIN_THRESHOLD.value)
						|| product.getStatus().equals(ThresholdStatus.SURPASSED_THRESHOLD.value)) {

					LocalDate dayMade = product.getDateListed();
					long difference = ChronoUnit.DAYS.between(dayMade, today);

					if (difference <= maximumThresholdDays) {
						if (product.getGeneratedInterest() >= product.getInterestThreshold()) {
							product.setStatus(ThresholdStatus.SURPASSED_THRESHOLD.value);
							ProductService.update(product);
						}
					} else {
						if (product.getGeneratedInterest() < product.getInterestThreshold()) {
							product.setStatus(ThresholdStatus.NEVER_SURPASSED_THRESHOLD.value);
							ProductService.update(product);
						}

					}
				}
			}
		} else {
			allProducts = new ArrayList<>();
		}
		return allProducts;
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
