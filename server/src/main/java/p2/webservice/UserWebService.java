package p2.webservice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import p2.model.User;
import p2.service.impl.UserService;
import p2.util.SessionVariableManager;

public class UserWebService {

	private static Logger logger = Logger.getLogger(UserWebService.class);

	public static void login(HttpServletRequest request, HttpServletResponse response) {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = UserService.findByEmailANDPassword(email, password);
		try {
			if (user != null) {
				SessionVariableManager.addLoggedInUser(request, user);
				logger.info("User Id: " + user.getId() + " Logged to the sysytem");
				response.getWriter().append("Successfully Logged to the system").close();

			} else {
				response.getWriter().close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static  void getLoggedInUser(HttpServletRequest request, HttpServletResponse response) {

		ObjectMapper om = new ObjectMapper();

		try {
			String json = om.writeValueAsString(SessionVariableManager.getLoggedInUser());
			response.getWriter().append(json).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			SessionVariableManager.removeLoggedInUser();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void insert(HttpServletRequest request, HttpServletResponse response) {
		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		String role = request.getParameter("role").trim();
		String address = request.getParameter("address").trim();
		int creditCardNumber = Integer.parseInt(request.getParameter("creditCardNumber").trim());
		int cvv = Integer.parseInt(request.getParameter("cvv").trim());

		User user = new User(firstName, lastName, email, password, role, address, creditCardNumber, cvv);
		try {
			if (UserService.insert(user) != -1) {
				response.getWriter().append("Suceessfully Added").close();
				logger.info("User, ID: " + user.getId() + " added to the System");
			} else {
				response.getWriter().close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void update(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id").trim());
		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		String password = request.getParameter("password").trim();
		String role = request.getParameter("role").trim();
		String address = request.getParameter("address").trim();
		int creditCardNumber = Integer.parseInt(request.getParameter("creditCardNumber").trim());
		int cvv = Integer.parseInt(request.getParameter("cvv").trim());

		User user = UserService.findById(id);

		if (!user.getFirstName().equals(firstName))
			user.setFirstName(firstName);

		if (!user.getLastName().equals(lastName))
			user.setLastName(lastName);

		if (!user.getPassword().equals(password))
			user.setPassword(password);

		if (!user.getRole().equals(role))
			user.setRole(role);

		if (!user.getAddress().equals(address))
			user.setAddress(address);

		if (!(user.getCreditCardNumber() == creditCardNumber))
			user.setCreditCardNumber(creditCardNumber);

		if (!(user.getCvv() == cvv))
			user.setCvv(cvv);

		try {
			if (UserService.update(user)) {
				response.getWriter().append("Suceessfully Updated").close();
				logger.info("User, ID: " + user.getId() + " Updated");
			} else {
				response.getWriter().close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
