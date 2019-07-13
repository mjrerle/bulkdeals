package p2.webservice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import p2.model.User;
import p2.service.UserService;
import p2.util.Glogger;
import p2.util.SessionVariableManager;
import p2.util.ValidationUtilities;

public class UserWebService {

	private static Logger logger = Glogger.logger;

	public static void login(HttpServletRequest request, HttpServletResponse response) {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = null;
		if (ValidationUtilities.checkNullOrEmpty(email) && ValidationUtilities.checkNullOrEmpty(password)) {
			user = UserService.findByEmailAndPassword(email, password);
		}

		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (user != null) {
				SessionVariableManager.addLoggedInUser(request, user);
				logger.info("User " + user.getEmail() + " logged into the system");
				response.getWriter().append("Successfully Logged in").close();
			} else {
				response.getWriter().append("Failed to log in due to incorrect email/password").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void getLoggedInUser(HttpServletRequest request, HttpServletResponse response) {

		ObjectMapper om = new ObjectMapper();

		try {
			String json = om.writeValueAsString(SessionVariableManager.getLoggedInUser());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			SessionVariableManager.removeLoggedInUser();
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append("User Logged Out").close();
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		String maybeFirstName = request.getParameter("firstName");
		String maybeLastName = request.getParameter("lastName");
		String maybeEmail = request.getParameter("email");
		String maybePassword = request.getParameter("password");
		String maybeRole = request.getParameter("role");
		String maybeAddress = request.getParameter("address");
		String maybeCreditCardNumber = request.getParameter("creditCardNumber");
		String maybeCvv = request.getParameter("cvv");
		User user = null;
		int userId = -1;
		if (ValidationUtilities.checkNullOrEmpty(maybeFirstName) && ValidationUtilities.checkNullOrEmpty(maybeLastName)
				&& ValidationUtilities.checkNullOrEmpty(maybeEmail) && ValidationUtilities.checkNullOrEmpty(maybePassword)
				&& ValidationUtilities.checkNullOrEmpty(maybeRole) && ValidationUtilities.checkNullOrEmpty(maybeAddress)
				&& ValidationUtilities.checkNullOrEmpty(maybeCreditCardNumber)
				&& ValidationUtilities.checkNullOrEmpty(maybeCvv)) {
			int creditCardNumber = Integer.parseInt(maybeCreditCardNumber);
			int cvv = Integer.parseInt(maybeCvv);
			user = new User(maybeFirstName, maybeLastName, maybeEmail, maybePassword, maybeRole, maybeAddress,
					creditCardNumber, cvv);
			userId = UserService.insert(user);
		}
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (userId >= 0) {
				logger.info("User " + user.getEmail() + " Inserted");				
				response.getWriter().append("User Inserted").close();
			} else {
				response.getWriter().append("User Insert Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void update(HttpServletRequest request, HttpServletResponse response) {
		String maybeFirstName = request.getParameter("firstName");
		String maybeLastName = request.getParameter("lastName");
		String maybeEmail = request.getParameter("email");
		String maybePassword = request.getParameter("password");
		String maybeRole = request.getParameter("role");
		String maybeAddress = request.getParameter("address");
		String maybeCreditCardNumber = request.getParameter("creditCardNumber");
		String maybeCvv = request.getParameter("cvv");
		String maybeUserId = request.getParameter("userId");
		User user = null;

		boolean success = false;
		if (ValidationUtilities.checkNullOrEmpty(maybeUserId)) {
			user = UserService.findById(Integer.parseInt(maybeUserId));
			if (user != null) {
				if (ValidationUtilities.checkNullOrEmpty(maybeFirstName)) {
					user.setFirstName(maybeFirstName);
				}

				if (ValidationUtilities.checkNullOrEmpty(maybeLastName)) {
					user.setLastName(maybeLastName);
				}

				if (ValidationUtilities.checkNullOrEmpty(maybeEmail)) {
					user.setLastName(maybeEmail);
				}

				if (ValidationUtilities.checkNullOrEmpty(maybePassword)) {
					user.setPassword(maybePassword);
				}

				if (ValidationUtilities.checkNullOrEmpty(maybeRole)) {
					user.setRole(maybeRole);
				}

				if (ValidationUtilities.checkNullOrEmpty(maybeAddress)) {
					user.setAddress(maybeAddress);
				}

				if (ValidationUtilities.checkNullOrEmpty(maybeCreditCardNumber)) {
					user.setCreditCardNumber(Integer.parseInt(maybeCreditCardNumber));
				}

				if (ValidationUtilities.checkNullOrEmpty(maybeCvv)) {
					user.setCvv(Integer.parseInt(maybeCvv));
				}
				success = UserService.update(user);
			}
		}

		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			if (success) {
				logger.info("User " + user.getEmail() + " Updated");
				response.getWriter().append("User Updated").close();
			} else {
				response.getWriter().append("User Update Failed").close();
			}
		} catch (IOException e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}
}
