package p2.service.impl;

import java.util.List;

import p2.dao.impl.UserDAO;
import p2.model.User;

public class UserService {

	private static UserDAO userDAO = new UserDAO();

	public static int insert(User user) {
		return userDAO.insert(user);
	}

	public static boolean update(User user) {
		return userDAO.update(user);
	}

	public static List<User> findAll() {
		return userDAO.findAll();
	}

	public static User findById(int id) {
		return userDAO.findById(id);
	}

	public static boolean deleteById(int id) {
		return userDAO.deleteById(id);

	}

	public static User findByEmailANDPassword(String email, String password) {
		return userDAO.findByEmailANDPassword(email, password);
	}
}
