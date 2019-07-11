package p2.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import p2.model.User;

public class SessionVariableManager {

	private static Logger logger = Logger.getLogger(SessionVariableManager.class);
	private static HttpSession session;
	private static int sessionInterval = 600;

	public static void addLoggedInUser(HttpServletRequest request, User user) {
		try {
			removeLoggedInUser();
			session = request.getSession();
			session.setAttribute(SessionKey.LOGGEDIN_USER.toString(), user);
			session.setMaxInactiveInterval(sessionInterval);
			logger.info("User ID: " + user.getId() + " Logged into the System");
		} catch (Exception e) {

		}

	}

	public static void removeLoggedInUser() {
		try {
			session.invalidate();
		} catch (Exception e) {

		}
	}

	public static User getLoggedInUser() {
		try {
			return (User) session.getAttribute(SessionKey.LOGGEDIN_USER.toString());
		} catch (Exception e) {
		}
		return null;
	}

	public static boolean verifyLoggedInUser(int id) {
		try {
			User user = getLoggedInUser();
			if ((user != null) && (id == user.getId())) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {

		}
		return false;
	}

}
