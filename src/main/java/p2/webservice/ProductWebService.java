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

import p2.model.Interest;
import p2.model.Product;
import p2.model.Purchase;
import p2.model.Taxonomy;
import p2.model.User;
import p2.service.InterestService;
import p2.service.ProductService;
import p2.service.PurchaseService;
import p2.util.Glogger;
import p2.util.ThresholdStatus;
import p2.util.ValidationUtilities;

public class ProductWebService {
	private static Logger logger = Glogger.logger;
	private static final int maximumThresholdDays = 7;

	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		String maybeUserId = request.getParameter("userId");
		String maybeName = request.getParameter("name");
		String maybePrice = request.getParameter("price");
		String maybeSalePrice = request.getParameter("salePrice");
		String maybeOnSale = request.getParameter("onSale");
		String maybeDescription = request.getParameter("description");
		String maybeStatus = request.getParameter("status");
		String maybeInterestThreshold = request.getParameter("interestThreshold");
		String maybeTaxonomy = request.getParameter("taxonomyId");
		String maybeImageUrl = request.getParameter("imageUrl");
		int productId = -1;

		if (ValidationUtilities.checkNullOrEmpty(maybeUserId) && ValidationUtilities.checkNullOrEmpty(maybeName)
				&& ValidationUtilities.checkNullOrEmpty(maybePrice)
				&& ValidationUtilities.checkNullOrEmpty(maybeTaxonomy)
				&& ValidationUtilities.checkNullOrEmpty(maybeImageUrl)) {
			int uId = Integer.parseInt(maybeUserId);
			double price = Double.parseDouble(maybePrice);
			User seller = new User(uId);
			int tId = Integer.parseInt(maybeTaxonomy);

			Product product = new Product(maybeName, maybeDescription, price, price, 0, 0, maybeImageUrl, null,
					maybeStatus, 0, new Taxonomy(tId), seller);

			if (ValidationUtilities.checkNullOrEmpty(maybeSalePrice)
					&& ValidationUtilities.checkNullOrEmpty(maybeOnSale)) {
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
			response.setCharacterEncoding("UTF-8");
			if (productId >= 0) {
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(productId);
				response.setContentType("application/json");
				response.getWriter().append(json).close();
			} else {
				response.setContentType("text/html");
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
		String maybeSalePrice = request.getParameter("salePrice");
		String maybeOnSale = request.getParameter("onSale");
		String maybeStatus = request.getParameter("status");
		String maybeProductId = request.getParameter("productId");
		String maybeUserId = request.getParameter("userId");

		String maybeGeneratedInterest = request.getParameter("generatedInterest");
		String maybeInterestThreshold = request.getParameter("interestThreshold");
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
		String maybeProductId = request.getParameter("productId");
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
		String maybeProductId = request.getParameter("productId");
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
			if (product.getStatus().equals(ThresholdStatus.ON_SALE.value)) {
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

	public static void findPennies(HttpServletRequest request, HttpServletResponse response) {

		List<Product> allProducts = ProductService.findAll();
		List<Product> pennisProducts = new ArrayList<>();
		LocalDate today = LocalDate.now();
		// Populating list of those gaining interest
		// this can be optimized by having a sql/hql query to search by "Within
		// Threshold"
		for (int i = 0; i < allProducts.size(); i++) {
			Product product = allProducts.get(i);
			if (product.getStatus().equals(ThresholdStatus.WITHIN_THRESHOLD.value)
					|| product.getStatus().equals(ThresholdStatus.SURPASSED_THRESHOLD.value)) {

				LocalDate dayMade = product.getDateListed();
				long difference = ChronoUnit.DAYS.between(dayMade, today);

				if (difference <= maximumThresholdDays) {
					if (product.getGeneratedInterest() >= product.getInterestThreshold()) {
						product.setStatus(ThresholdStatus.SURPASSED_THRESHOLD.value);
						ProductService.update(product);
					}
					pennisProducts.add(product);
				} else {
					if (product.getGeneratedInterest() < product.getInterestThreshold()) {
						product.setStatus(ThresholdStatus.NEVER_SURPASSED_THRESHOLD.value);
						ProductService.update(product);
					} else {
						pennisProducts.add(product);
					}

				}

			}
		}

		try {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(pennisProducts);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * public static void findPretties(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * List<Product> allProducts = ProductService.findAll(); List<Product>
	 * standardProducts = new ArrayList<Product>(); // populating list of standard
	 * priced items being sold for (int i = 0; i < allProducts.size(); i++) {
	 * Product product = allProducts.get(i); if
	 * (product.getStatus().equals(ThresholdStatus.STANDARD.value)) {
	 * standardProducts.add(product); } }
	 * 
	 * try { ObjectMapper om = new ObjectMapper();
	 * response.setContentType("application/json");
	 * response.setCharacterEncoding("UTF-8"); String json =
	 * om.writeValueAsString(standardProducts);
	 * response.getWriter().append(json).close(); } catch (IOException e) {
	 * logger.warn(e.getMessage()); e.printStackTrace(); } }
	 */

	/*
	 * public static void findAllOnSale(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * List<Product> allProducts = ProductService.findAll(); List<Product>
	 * onSaleProducts = new ArrayList<Product>(); // populating return list of those
	 * on sale for (int i = 0; i < allProducts.size(); i++) { Product product =
	 * allProducts.get(i); if
	 * (product.getStatus().equals(ThresholdStatus.ON_SALE.value)) {
	 * onSaleProducts.add(product); } }
	 * 
	 * try { ObjectMapper om = new ObjectMapper();
	 * response.setContentType("application/json");
	 * response.setCharacterEncoding("UTF-8"); String json =
	 * om.writeValueAsString(onSaleProducts);
	 * response.getWriter().append(json).close(); } catch (IOException e) {
	 * logger.warn(e.getMessage()); e.printStackTrace(); } }
	 */

	/*
	 * public static void findPennies(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * List<Product> allProducts = ProductService.findAll(); List<Product>
	 * withinThresholdProducts = new ArrayList<>(); LocalDate today =
	 * LocalDate.now(); // Populating list of those gaining interest // this can be
	 * optimized by having a sql/hql query to search by "Within // Threshold" for
	 * (int i = 0; i < allProducts.size(); i++) { Product product =
	 * allProducts.get(i); if
	 * (product.getStatus().equals(ThresholdStatus.WITHIN_THRESHOLD.value)) {
	 * withinThresholdProducts.add(product); } } // removing from list if interest
	 * not gained in one week and updating back to // standard // this can be
	 * optimized with another custom DAO for (int i = 0; i <
	 * withinThresholdProducts.size(); i++) { Product product =
	 * withinThresholdProducts.get(i); LocalDate dayMade = product.getDateListed();
	 * long difference = ChronoUnit.DAYS.between(dayMade, today); if (difference >
	 * 7) { product.setStatus(ThresholdStatus.STANDARD.value);
	 * product.setDateListed(null); product.setInterestThreshold(0);
	 * ProductService.update(product); withinThresholdProducts.remove(i); } }
	 * 
	 * // removing from list if interest gained and setting on sale for (int i = 0;
	 * i < withinThresholdProducts.size(); i++) { Product product =
	 * withinThresholdProducts.get(i); int quantityOfInterests =
	 * InterestService.getNumberOfInterestsByProductId(product.getId()); if
	 * (quantityOfInterests >= product.getInterestThreshold()) {
	 * product.setStatus(ThresholdStatus.ON_SALE.value);
	 * ProductService.update(product); List<Interest> interests =
	 * InterestService.findByProductId(product.getId()); for (Interest interest :
	 * interests) { Purchase purchase = new Purchase(LocalDate.now(),
	 * interest.getUser(), product); PurchaseService.insert(purchase); }
	 * withinThresholdProducts.remove(i); } }
	 * 
	 * try { ObjectMapper om = new ObjectMapper(); String json =
	 * om.writeValueAsString(withinThresholdProducts);
	 * response.setContentType("application/json");
	 * response.setCharacterEncoding("UTF-8");
	 * response.getWriter().append(json).close(); } catch (IOException e) {
	 * logger.warn(e.getMessage()); e.printStackTrace(); } }
	 */

	/*
	 * public static void removeFromSale(HttpServletRequest request,
	 * HttpServletResponse response) { String maybeProductId =
	 * request.getParameter("productId"); int productId = -1; boolean success =
	 * false; Product product = null; if
	 * (ValidationUtilities.checkNullOrEmpty(maybeProductId)) { productId =
	 * Integer.parseInt(maybeProductId); product =
	 * ProductService.findById(productId); if (product != null) {
	 * product.setStatus(ThresholdStatus.STANDARD.value);
	 * product.setDateListed(null); success = ProductService.update(product); } }
	 * 
	 * try { response.setContentType("text/html");
	 * response.setCharacterEncoding("UTF-8"); if (success) {
	 * response.getWriter().append("Removed Product From Sale").close(); } else {
	 * response.getWriter().append("Failed to Remove Product From Sale").close(); }
	 * } catch (IOException e) { logger.warn(e.getMessage()); e.printStackTrace(); }
	 * }
	 */

	public static void findAllProductsByType(HttpServletRequest request, HttpServletResponse response) {

		String maybeType = request.getParameter("type");
		List<Product> products = null;

		if (ValidationUtilities.checkNullOrEmpty(maybeType)) {
			products = ProductService.findAllProductsByType(maybeType);
		}

		try {
			response.setCharacterEncoding("UTF-8");
			if (products != null) {
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(products);
				response.setContentType("application/json");
				response.getWriter().append(json).close();
			} else {
				response.setContentType("text/html");
				response.getWriter().append("Products Not Found").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findAllProductsBySubType(HttpServletRequest request, HttpServletResponse response) {

		String maybeSubType = request.getParameter("subType");
		List<Product> products = null;

		if (ValidationUtilities.checkNullOrEmpty(maybeSubType)) {
			products = ProductService.findAllProductsBySubType(maybeSubType);
		}

		try {
			response.setCharacterEncoding("UTF-8");
			if (products != null) {
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(products);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).close();
			} else {
				response.setContentType("text/html");
				response.getWriter().append("Products Not Found").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findAllProductsByTaxonomyName(HttpServletRequest request, HttpServletResponse response) {

		String maybeName = request.getParameter("name");
		List<Product> products = null;

		if (ValidationUtilities.checkNullOrEmpty(maybeName)) {
			products = ProductService.findAllProductsByTaxonomyName(maybeName);
		}

		try {
			response.setCharacterEncoding("UTF-8");
			if (products != null) {
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(products);
				response.setContentType("application/json");
				response.getWriter().append(json).close();
			} else {
				response.setContentType("text/html");
				response.getWriter().append("Products Not Found").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findAllProductsByTaxonomy(HttpServletRequest request, HttpServletResponse response) {

		String maybeName = request.getParameter("name");
		String maybeType = request.getParameter("type");
		String maybeSubType = request.getParameter("subType");

		List<Product> products = null;

		// replace the empty fields with wildcards
		if (!ValidationUtilities.checkNullOrEmpty(maybeName)) {
			maybeName = "%";
		}

		if (!ValidationUtilities.checkNullOrEmpty(maybeType)) {
			maybeType = "%";
		}

		if (!ValidationUtilities.checkNullOrEmpty(maybeSubType)) {
			maybeSubType = "%";
		}
		products = ProductService.findAllProductsByTaxonomy(maybeName, maybeType, maybeSubType);

		try {
			response.setCharacterEncoding("UTF-8");
			if (products != null) {
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(products);
				response.setContentType("application/json");
				response.getWriter().append(json).close();
			} else {
				response.setContentType("text/html");
				response.getWriter().append("Products Not Found").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findPrettiesBySeller(HttpServletRequest request, HttpServletResponse response) {

		int sellerID = Integer.parseInt(request.getParameter("sellerId"));

		List<Product> allProducts = ProductService.findAll();
		List<Product> standardProducts = new ArrayList<Product>();

		for (int i = 0; i < allProducts.size(); i++) {
			Product product = allProducts.get(i);
			if ((product.getSeller().getId() == sellerID)
					&& (product.getStatus().equals(ThresholdStatus.ON_SALE.value))) {
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

	public static void findPenniesBySeller(HttpServletRequest request, HttpServletResponse response) {

		int sellerID = Integer.parseInt(request.getParameter("sellerId"));
		List<Product> allProducts = ProductService.findAll();
		List<Product> pennisProducts = new ArrayList<>();
		LocalDate today = LocalDate.now();

		for (int i = 0; i < allProducts.size(); i++) {
			Product product = allProducts.get(i);
			if (product.getSeller().getId() == sellerID) {

				if (product.getStatus().equals(ThresholdStatus.WITHIN_THRESHOLD.value)
						|| product.getStatus().equals(ThresholdStatus.SURPASSED_THRESHOLD.value)) {

					LocalDate dayMade = product.getDateListed();
					long difference = ChronoUnit.DAYS.between(dayMade, today);

					if (difference <= maximumThresholdDays) {
						if (product.getGeneratedInterest() >= product.getInterestThreshold()) {
							product.setStatus(ThresholdStatus.SURPASSED_THRESHOLD.value);
							ProductService.update(product);
						}
						pennisProducts.add(product);
					} else {
						if (product.getGeneratedInterest() < product.getInterestThreshold()) {
							product.setStatus(ThresholdStatus.NEVER_SURPASSED_THRESHOLD.value);
							ProductService.update(product);
						} else {
							pennisProducts.add(product);
						}

					}

				}
			}
		}

		try {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(pennisProducts);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

}
