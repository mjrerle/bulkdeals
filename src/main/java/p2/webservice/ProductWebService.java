package p2.webservice;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.Product;
import p2.model.Taxonomy;
import p2.model.User;
import p2.service.ProductService;
import p2.service.TaxonomyService;
import p2.service.UserService;
import p2.util.Glogger;
import p2.util.ThresholdStatus;
import p2.util.ValidationUtilities;

public class ProductWebService {
	private static Logger logger = Glogger.logger;

	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		Taxonomy tax = null;
		User user = null;

		try {
			product = mapper.readValue(request.getInputStream(), Product.class);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		int productId = -1;
		if (product != null) {
			// check if product has a tax and a user
			if (product.getTaxonomy() != null && product.getUser() != null) {

				if (product.getTaxonomy().getTaxonomyId() != 0)
					tax = TaxonomyService.findById(product.getTaxonomy().getTaxonomyId());
				else if (product.getTaxonomy().getName() != null && product.getTaxonomy().getType() != null
						&& product.getTaxonomy().getSubType() != null)
					tax = TaxonomyService.findByTaxonomy(product.getTaxonomy());

				user = UserService.findById(product.getUser().getUserId());
				// if it does, then make sure that the user and the tax exist in the db
				if (tax != null && user != null) {
					product.setDateListed(LocalDate.now());
					productId = ProductService.insert(product);
				}
			}
		}
		try {
			response.setCharacterEncoding("UTF-8");
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(productId);
			response.setContentType("application/json");
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void update(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Product oldProduct = null;
		Product updatedProduct = null;
		try {
			updatedProduct = mapper.readValue(request.getInputStream(), Product.class);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		boolean success = false;
		if (updatedProduct != null) {
			if (updatedProduct.getProductId() >= 0) {
				// check if product has a tax and a user
				if (updatedProduct.getTaxonomy() != null && updatedProduct.getUser() != null) {
					Taxonomy tax = TaxonomyService.findByTaxonomy(updatedProduct.getTaxonomy());
					User user = UserService.findById(updatedProduct.getUser().getUserId());
					// if it does, then make sure that the user and the tax exist in the db
					if (tax != null && user != null) {
						oldProduct = ProductService.findById(updatedProduct.getProductId());
						if (oldProduct.getStatus().equals(updatedProduct.getStatus())) {
							updatedProduct.setDateListed(oldProduct.getDateListed());
						} else {
							updatedProduct.setDateListed(LocalDate.now());
						}
						success = ProductService.update(updatedProduct);
					}
				}
			}
		}
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (success) {
				response.getWriter().append('1');
			} else {
				response.getWriter().append('0');
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findAll(HttpServletRequest request, HttpServletResponse response) {

		List<Product> list = new ArrayList<>();
		List<Product> allProducts = ProductService.findAll();
		int maximumThresholdDays = 7;
		LocalDate today = LocalDate.now();

		if (allProducts != null) {
			for (Product product : allProducts) {
				if (product.getStatus().equals(ThresholdStatus.WITHIN_THRESHOLD.value)) {
					// update the status of product
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
				// check here because above code have changed the status
				if (!product.getStatus().equals(ThresholdStatus.NEVER_SURPASSED_THRESHOLD.value)
						&& !product.getStatus().equals(ThresholdStatus.CANCELLED_BY_SELLER.value)) {
					list.add(product);
				}
			}
		} else {
			allProducts = new ArrayList<>();
		}
		try {
			ObjectMapper om = new ObjectMapper();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String json = om.writeValueAsString(list);
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
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		try {
			product = mapper.readValue(request.getInputStream(), Product.class);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		boolean success = false;
		if (product != null) {
			if (product.getProductId() >= 0) {
				// check if product has a tax and a user
				if (product.getTaxonomy() != null && product.getUser() != null) {
					Taxonomy tax = TaxonomyService.findById(product.getTaxonomy().getTaxonomyId());
					User user = UserService.findById(product.getUser().getUserId());
					// if it does, then make sure that the user and the tax exist in the db
					if (tax != null && user != null) {
						success = ProductService.deleteById(product.getProductId());
					}
				}
			}
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
			if (product.getStatus().equals(ThresholdStatus.PRETTY.value)) {
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
		List<Product> penniesProducts = new ArrayList<>();

		for (int i = 0; i < allProducts.size(); i++) {
			Product product = allProducts.get(i);
			if (product.getStatus().equals(ThresholdStatus.WITHIN_THRESHOLD.value)
					|| product.getStatus().equals(ThresholdStatus.SURPASSED_THRESHOLD.value)) {
				penniesProducts.add(product);
			}
		}

		try {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(penniesProducts);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void removeFromSale(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		try {
			product = mapper.readValue(request.getInputStream(), Product.class);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		boolean success = false;
		if (product != null) {
			if (product.getProductId() >= 0) {
				// check if product has a tax and a user
				if (product.getTaxonomy() != null && product.getUser() != null) {
					Taxonomy tax = TaxonomyService.findById(product.getTaxonomy().getTaxonomyId());
					User user = UserService.findById(product.getUser().getUserId());
					// if it does, then make sure that the user and the tax exist in the db
					if (tax != null && user != null) {
						product.setStatus(ThresholdStatus.PRETTY.value);
						product.setDateListed(null);
						success = ProductService.update(product);
					}
				}
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

		int sellerId = Integer.parseInt(request.getParameter("sellerId"));
		List<Product> allProducts = ProductService.findAll();
		List<Product> standardProducts = new ArrayList<Product>();

		for (int i = 0; i < allProducts.size(); i++) {
			Product product = allProducts.get(i);
			if ((product.getUser().getUserId() == sellerId) && (product.getStatus().equals(ThresholdStatus.PRETTY.value))) {
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

		int sellerId = Integer.parseInt(request.getParameter("sellerId"));
		List<Product> allProducts = ProductService.findAll();
		List<Product> pennyProducts = new ArrayList<>();

		for (int i = 0; i < allProducts.size(); i++) {
			Product product = allProducts.get(i);
			if ((product.getUser().getUserId() == sellerId) && (!product.getStatus().equals(ThresholdStatus.PRETTY.value))) {
				pennyProducts.add(product);
			}
		}

		try {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(pennyProducts);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

}
