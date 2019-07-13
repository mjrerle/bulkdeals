package p2.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import p2.model.User;

public class SessionVariableManager {

	private static Logger logger = Glogger.logger;
	private static HttpSession session;
	private static int sessionInterval = 600;

	public static void addLoggedInUser(HttpServletRequest request, User user) {
		try {
			removeLoggedInUser();
			session = request.getSession();
			session.setAttribute(SessionKey.LOGGED_IN_USER.toString(), user);
			session.setMaxInactiveInterval(sessionInterval);
			logger.info("User " + user.getEmail() + " logged in");
		} catch (Exception e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}

	}

	public static void removeLoggedInUser() {
		try {
			session.invalidate();
		} catch (Exception e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
	}

	public static User getLoggedInUser() {
		try {
			return (User) session.getAttribute(SessionKey.LOGGED_IN_USER.toString());
		} catch (Exception e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static boolean verifyLoggedInUser(int id) {
		try {
			User user = getLoggedInUser();
			if ((user != null) && (id == user.getId())) {
				return true;
			} 
		} catch (Exception e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

}
