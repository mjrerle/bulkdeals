package p2.webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.Interest;
import p2.model.Product;
import p2.model.User;
import p2.service.InterestService;
import p2.util.Glogger;
import p2.util.ValidationUtilities;

public class InterestWebService {

	private static Logger logger = Glogger.logger;

	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		String maybeUserId = request.getParameter("user_id");
		String maybeProductId = request.getParameter("product_id");
		String maybeQuantity = request.getParameter("quantity");
		Interest interest = null;
		int interestId = -1;
		if (ValidationUtilities.checkNullOrEmpty(maybeUserId) && ValidationUtilities.checkNullOrEmpty(maybeProductId)
				&& ValidationUtilities.checkNullOrEmpty(maybeQuantity)) {
			int userId = Integer.parseInt(maybeUserId);
			int productId = Integer.parseInt(maybeProductId);
			int quantity = Integer.parseInt(maybeQuantity);
			interest = new Interest(new User(userId), new Product(productId), quantity);
			interestId = InterestService.insert(interest);
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
		String maybeUserId = request.getParameter("user_id");
		String maybeProductId = request.getParameter("product_id");
		String maybeQuantity = request.getParameter("quantity");
		String maybeInterestId = request.getParameter("interest_id");
		Interest interest = null;
		boolean success = false;
		if (ValidationUtilities.checkNullOrEmpty(maybeInterestId)) {
			int interestId = Integer.parseInt(maybeInterestId);
			interest = InterestService.findById(interestId);
			if (interest != null) {
				if (ValidationUtilities.checkNullOrEmpty(maybeUserId)) {
					int userId = Integer.parseInt(maybeUserId);
					interest.setUser(new User(userId));
				}
				if (ValidationUtilities.checkNullOrEmpty(maybeProductId)) {
					int productId = Integer.parseInt(maybeProductId);
					interest.setProduct(new Product(productId));
				}

				if (ValidationUtilities.checkNullOrEmpty(maybeQuantity)) {
					int quantity = Integer.parseInt(maybeQuantity);
					interest.setQuantity(quantity);
				}
				success = InterestService.update(interest);
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
		String maybeProductId = request.getParameter("product_id");
		List<Interest> interests = null;
		if(ValidationUtilities.checkNullOrEmpty(maybeProductId)) {
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
		String maybeUserId = request.getParameter("user_id");
		List<Interest> interests = null;
		if(ValidationUtilities.checkNullOrEmpty(maybeUserId)) {
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
		String maybeInterestId = request.getParameter("interest_id");
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
		String maybeInterestId = request.getParameter("interest_id");
		boolean success = false;
		if (ValidationUtilities.checkNullOrEmpty(maybeInterestId)) {
			int interestId = Integer.parseInt(maybeInterestId);
			success = InterestService.deleteById(interestId);
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
