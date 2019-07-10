package p2.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import p2.model.User;

public class SessionVariableManager {

	private static Logger logger = Logger.getLogger(SessionVariableManager.class);
	private static final String SESSION_NOT_EXIST = "Session doesn't exist";
	private static int sessionInterval = 600;
	private List<HttpSession> list = new ArrayList<HttpSession>();

	public void addUserVariable(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		session.setAttribute(SessionKey.SESSION_USER.toString(), user);
		session.setMaxInactiveInterval(sessionInterval);
		list.add(session);
		logger.info("User ID: " + user.getId() + " Logged into the System");
	}

	private HttpSession findUserSession(User user) {

		for (HttpSession httpSession : list) {
			if (((User) httpSession.getAttribute(SessionKey.SESSION_USER.toString())).getId() == user.getId()) {
				return httpSession;
			}
		}
		return null;
	}

	public void removeUserVariable(User user) {
		HttpSession httpSession = findUserSession(user);
		if (httpSession != null) {
			list.remove(list.indexOf(httpSession));
		} else {
			logger.error(SESSION_NOT_EXIST);
		}
	}

	public boolean verifySessionUser(User user) {
		HttpSession httpSession = findUserSession(user);
		if (httpSession != null) {
			return true;
		} else {
			logger.error(SESSION_NOT_EXIST);
			return false;
		}
	}

}
