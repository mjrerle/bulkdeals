package p2.service.impl;

import java.util.List;

import p2.dao.impl.UserDAO;
import p2.model.User;

public class UserService {

	private static UserDAO userDAO = new UserDAO();

	public static int insert(User user) {
		return userDAO.insert(user);
	}

	public static void update(User user) {
		userDAO.update(user);
	}

	public static List<User> findAll() {
		return userDAO.findAll();
	}

	public static User findById(int id) {
		return userDAO.findById(id);
	}

	public static void deleteById(int id) {
		userDAO.deleteById(id);

	}

}
