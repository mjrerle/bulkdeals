package p2.webservice;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.Product;
import p2.model.User;
import p2.service.InterestService;
import p2.service.ProductService;
import p2.util.Glogger;
import p2.util.ThresholdStatus;
import p2.util.ValidationUtilities;

public class ProductWebService {
	private static Logger logger = Glogger.logger;

	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		String maybeUserId = request.getParameter("user_id");
		String maybeName = request.getParameter("name");
		String maybePrice = request.getParameter("price");
		String maybeSalePrice = request.getParameter("sale_price");
		String maybeOnSale = request.getParameter("on_sale");

		String maybeStatus = request.getParameter("status");
		String maybeInterestThreshold = request.getParameter("interest_threshold");
		int productId = -1;

		if (ValidationUtilities.checkNullOrEmpty(maybeUserId) && ValidationUtilities.checkNullOrEmpty(maybeName)
				&& ValidationUtilities.checkNullOrEmpty(maybePrice)) {
			int uId = Integer.parseInt(maybeUserId);
			double price = Double.parseDouble(maybePrice);
			User seller = new User(uId);

			Product product = new Product(maybeName, price, price, 0, 0, null, maybeStatus, 0, seller);

			if (ValidationUtilities.checkNullOrEmpty(maybeSalePrice) && ValidationUtilities.checkNullOrEmpty(maybeOnSale)) {
				product.setSalePrice(Double.parseDouble(maybeSalePrice));
				product.setOnSale(Integer.parseInt(maybeOnSale));
			}

			if (ValidationUtilities.checkNullOrEmpty(maybeStatus)) {
				product.setStatus(maybeStatus);
				if (ValidationUtilities.checkNullOrEmpty(maybeInterestThreshold)) {
					product.setInterestThreshold(Integer.parseInt(maybeInterestThreshold));
				}
				LocalDate dateListed = LocalDate.now();
				product.setDateListed(dateListed);
			}

			productId = ProductService.insert(product);
		}
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (productId >= 0) {
				response.getWriter().append("Product Added").close();
			} else {
				response.getWriter().append("Product Add Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void update(HttpServletRequest request, HttpServletResponse response) {
		String maybeName = request.getParameter("name");
		String maybePrice = request.getParameter("price");
		String maybeSalePrice = request.getParameter("sale_price");
		String maybeOnSale = request.getParameter("on_sale");
		String maybeStatus = request.getParameter("status");
		String maybeProductId = request.getParameter("product_id");
		String maybeUserId = request.getParameter("user_id");

		String maybeGeneratedInterest = request.getParameter("generated_interest");
		String maybeInterestThreshold = request.getParameter("interest_threshold");
		Product product = null;

		int productId = -1;
		boolean success = false;
		if (ValidationUtilities.checkNullOrEmpty(maybeProductId)) {
			productId = Integer.parseInt(maybeProductId);
			product = ProductService.findById(productId);
			double price = 0;
			double saleprice = 0;
			if (product != null) {
				if (ValidationUtilities.checkNullOrEmpty(maybeStatus)) {
					product.setStatus(maybeStatus);
					// updates date listed based on status
					if (maybeStatus.equals(ThresholdStatus.WITHIN_THRESHOLD.value) && product.getDateListed() != null) {
						LocalDate day = LocalDate.now();
						product.setDateListed(day);
					}
				}
				if (ValidationUtilities.checkNullOrEmpty(maybeName)) {
					product.setProductName(maybeName);
				}
				if (ValidationUtilities.checkNullOrEmpty(maybePrice)) {
					price = Double.parseDouble(maybePrice);
					product.setPrice(price);
				}
				if (ValidationUtilities.checkNullOrEmpty(maybeSalePrice)) {
					saleprice = Double.parseDouble(maybeSalePrice);
					product.setSalePrice(saleprice);
				}

				if (ValidationUtilities.checkNullOrEmpty(maybeOnSale)) {
					int onSale = Integer.parseInt(maybeOnSale);
					product.setOnSale(onSale);
				}

				if (ValidationUtilities.checkNullOrEmpty(maybeUserId)) {
					int userId = Integer.parseInt(maybeUserId);
					product.setSeller(new User(userId));
				}
				if (ValidationUtilities.checkNullOrEmpty(maybeGeneratedInterest)) {
					int generatedInterest = Integer.parseInt(maybeGeneratedInterest);
					product.setGeneratedInterest(generatedInterest);
				}

				if (ValidationUtilities.checkNullOrEmpty(maybeInterestThreshold)) {
					int interestThreshold = Integer.parseInt(maybeInterestThreshold);
					product.setInterestThreshold(interestThreshold);
				}
				success = ProductService.update(product);
			}
		}
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (success) {
				response.getWriter().append("Product Updated");
			} else {
				response.getWriter().append("Product Update Failed");
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findAll(HttpServletRequest request, HttpServletResponse response) {

		List<Product> product = ProductService.findAll();
		try {
			ObjectMapper om = new ObjectMapper();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String json = om.writeValueAsString(product);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findById(HttpServletRequest request, HttpServletResponse response) {
		String maybeProductId = request.getParameter("product_id");
		int productId = -1;
		Product product = null;
		if (ValidationUtilities.checkNullOrEmpty(maybeProductId)) {
			productId = Integer.parseInt(maybeProductId);
			product = ProductService.findById(productId);
		}

		try {
			response.setCharacterEncoding("UTF-8");
			if (product != null) {
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(product);
				response.setContentType("application/json");
				response.getWriter().append(json).close();
			} else {
				response.setContentType("text/html");
				response.getWriter().append("Failed to Find Product");
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}

	}

	public static void deleteById(HttpServletRequest request, HttpServletResponse response) {
		String maybeProductId = request.getParameter("product_id");
		int productId = -1;
		boolean success = false;
		if (ValidationUtilities.checkNullOrEmpty(maybeProductId)) {
			productId = Integer.parseInt(maybeProductId);
			success = ProductService.deleteById(productId);
		}

		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (success) {
				response.getWriter().append("Product Deleted").close();
			} else {
				response.getWriter().append("Product Delete Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}

	}

	public static void findPretties(HttpServletRequest request, HttpServletResponse response) {

		List<Product> allProducts = ProductService.findAll();
		List<Product> standardProducts = new ArrayList<Product>();
		// populating list of standard priced items being sold
		for (int i = 0; i < allProducts.size(); i++) {
			Product product = allProducts.get(i);
			if (product.getStatus().equals(ThresholdStatus.STANDARD.value)) {
				standardProducts.add(product);
			}
		}

		try {
			ObjectMapper om = new ObjectMapper();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String json = om.writeValueAsString(standardProducts);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findAllOnSale(HttpServletRequest request, HttpServletResponse response) {

		List<Product> allProducts = ProductService.findAll();
		List<Product> onSaleProducts = new ArrayList<Product>();
		// populating return list of those on sale
		for (int i = 0; i < allProducts.size(); i++) {
			Product product = allProducts.get(i);
			if (product.getStatus().equals(ThresholdStatus.ON_SALE.value)) {
				onSaleProducts.add(product);
			}
		}

		try {
			ObjectMapper om = new ObjectMapper();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String json = om.writeValueAsString(onSaleProducts);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findPennies(HttpServletRequest request, HttpServletResponse response) {

		List<Product> allProducts = ProductService.findAll();
		List<Product> withinThresholdProducts = new ArrayList<>();
		LocalDate today = LocalDate.now();
		// Populating list of those gaining interest
		// this can be optimized by having a sql/hql query to search by "Within
		// Threshold"
		for (int i = 0; i < allProducts.size(); i++) {
			Product product = allProducts.get(i);
			if (product.getStatus().equals(ThresholdStatus.WITHIN_THRESHOLD.value)) {
				withinThresholdProducts.add(product);
			}
		}
		// removing from list if interest not gained in one week and updating back to
		// standard
		// this can be optimized with another custom DAO
		for (int i = 0; i < withinThresholdProducts.size(); i++) {
			Product product = withinThresholdProducts.get(i);
			LocalDate dayMade = product.getDateListed();
			long difference = ChronoUnit.DAYS.between(dayMade, today);
			if (difference > 7) {
				product.setStatus(ThresholdStatus.STANDARD.value);
				product.setDateListed(null);
				product.setInterestThreshold(0);
				ProductService.update(product);
				withinThresholdProducts.remove(i);
			}
		}

		// removing from list if interest gained and setting on sale
		for (int i = 0; i < withinThresholdProducts.size(); i++) {
			Product product = withinThresholdProducts.get(i);
			int quantityOfInterests = InterestService.getNumberOfInterestsByProductId(product.getId());
			if (quantityOfInterests >= product.getInterestThreshold()) {
				product.setStatus(ThresholdStatus.ON_SALE.value);
				ProductService.update(product);
				withinThresholdProducts.remove(i);
			}
		}

		try {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(withinThresholdProducts);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void removeFromSale(HttpServletRequest request, HttpServletResponse response) {
		String maybeProductId = request.getParameter("product_id");
		int productId = -1;
		boolean success = false;
		Product product = null;
		if (ValidationUtilities.checkNullOrEmpty(maybeProductId)) {
			productId = Integer.parseInt(maybeProductId);
			product = ProductService.findById(productId);
			if (product != null) {
				product.setStatus(ThresholdStatus.STANDARD.value);
				product.setDateListed(null);
				success = ProductService.update(product);
			}
		}

		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (success) {
				response.getWriter().append("Removed Product From Sale").close();
			} else {
				response.getWriter().append("Failed to Remove Product From Sale").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}
}
