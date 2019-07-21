package p2.webservice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.Interest;
import p2.model.Product;
import p2.model.Purchase;
import p2.model.User;
import p2.service.InterestService;
import p2.service.ProductService;
import p2.service.PurchaseService;
import p2.service.UserService;
import p2.util.Glogger;
import p2.util.ThresholdStatus;
import p2.util.ValidationUtilities;

public class InterestWebService {

	private static Logger logger = Glogger.logger;

	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Interest interest = null;
		try {
			interest = mapper.readValue(request.getInputStream(), Interest.class);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		int interestId = -1;
		if (interest != null) {
			// check if interest has a product and a user
			if (interest.getProduct() != null && interest.getUser() != null) {

				Product product = ProductService.findById(interest.getProduct().getProductId());
				User user = UserService.findById(interest.getUser().getUserId());

				if (product != null && user != null) {
					if (!product.getStatus().equals(ThresholdStatus.PRETTY.value)) {
						// if the product is a penny
						if (product.getStatus().equals(ThresholdStatus.WITHIN_THRESHOLD.value)) {
							// and the products current generated interest is below the threshold
							interest.setProduct(product);
							interest.setUser(user);
							interestId = InterestService.insert(interest);
							if (product.getGeneratedInterest() < product.getInterestThreshold()) {
								// and the new quantity will make the generated interest surpass the threshold
								if (product.getGeneratedInterest() + interest.getQuantity() >= product.getInterestThreshold()) {

									// make purchase histories for all interested customers
									List<Interest> interests = InterestService.findByProductId(product.getProductId());

									for (Interest i : interests) {
										Purchase purchase = new Purchase(LocalDate.now(),
												(long) Math.floor(product.getSalePrice() * i.getQuantity()), i.getUser(), product);
										PurchaseService.insert(purchase);
									}
								}
							}
							// buy outright if the product surpassed threshold
						} else if (product.getStatus().equals(ThresholdStatus.SURPASSED_THRESHOLD.value)) {
							interest.setProduct(product);
							interest.setUser(user);
							interestId = InterestService.insert(interest);
							Purchase purchase = new Purchase(LocalDate.now(), (long) Math.floor(product.getSalePrice()),
									interest.getUser(), product);
							PurchaseService.insert(purchase);
						}
						product.setGeneratedInterest(product.getGeneratedInterest() + interest.getQuantity());

						ProductService.update(product);
						// simple pretty product
					} else {
						product.setInterestThreshold(product.getInterestThreshold() - interest.getQuantity());
						ProductService.update(product);
						interest.setProduct(product);
						interest.setUser(user);
						interestId = InterestService.insert(interest);
					}
				}

			}
		}

		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (interestId >= 0) {
				response.getWriter().append("Interest Inserted").close();
			} else {
				response.getWriter().append("Interest Insert Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void update(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Interest interest = null;
		try {
			interest = mapper.readValue(request.getInputStream(), Interest.class);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		boolean success = false;
		if (interest != null) {
			if (interest.getInterestId() >= 0) {
				// check if interest has a product and a user
				if (interest.getProduct() != null && interest.getUser() != null) {
					Product product = ProductService.findById(interest.getProduct().getProductId());
					User user = UserService.findById(interest.getUser().getUserId());
					// if it does, then make sure that the user and the tax exist in the db
					if (product != null && user != null) {
						success = InterestService.update(interest);
					}
				}
			}
		}

		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (success) {
				response.getWriter().append("Interest Updated").close();
			} else {
				response.getWriter().append("Interest Update Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findAll(HttpServletRequest request, HttpServletResponse response) {
		List<Interest> interests = InterestService.findAll();

		try {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(interests);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findByProductId(HttpServletRequest request, HttpServletResponse response) {
		String maybeProductId = request.getParameter("productId");
		List<Interest> interests = null;
		if (ValidationUtilities.checkNullOrEmpty(maybeProductId)) {
			interests = InterestService.findByProductId(Integer.parseInt(maybeProductId));
		}
		try {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(interests);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findByUserId(HttpServletRequest request, HttpServletResponse response) {
		String maybeUserId = request.getParameter("userId");
		List<Interest> interests = null;
		if (ValidationUtilities.checkNullOrEmpty(maybeUserId)) {
			interests = InterestService.findByUserId(Integer.parseInt(maybeUserId));
		}
		try {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(interests);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void findById(HttpServletRequest request, HttpServletResponse response) {
		String maybeInterestId = request.getParameter("interestId");
		Interest interest = null;
		if (ValidationUtilities.checkNullOrEmpty(maybeInterestId)) {
			int interestId = Integer.parseInt(maybeInterestId);
			interest = InterestService.findById(interestId);
		}

		try {
			response.setCharacterEncoding("UTF-8");
			if (interest != null) {
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(interest);
				response.setContentType("application/json");
				response.getWriter().append(json).close();
			} else {
				response.setContentType("text/html");
				response.getWriter().append("Find By Interest Id Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void deleteById(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Interest interest = null;
		try {
			interest = mapper.readValue(request.getInputStream(), Interest.class);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		boolean success = false;
		if (interest != null) {
			if (interest.getInterestId() >= 0) {
				// check if interest has a product and a user
				if (interest.getProduct() != null && interest.getUser() != null) {
					Product product = ProductService.findById(interest.getProduct().getProductId());
					User user = UserService.findById(interest.getUser().getUserId());
					// if it does, then make sure that the user and the tax exist in the db
					if (product != null && user != null) {
						success = InterestService.deleteById(interest.getInterestId());
					}
				}
			}

		}

		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (success) {
				response.getWriter().append("Interest Deleted").close();
			} else {
				response.getWriter().append("Interest Delete Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}
}
