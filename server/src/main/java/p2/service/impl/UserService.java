package p2.service.impl;

import java.util.List;

import p2.dao.impl.UserDAO;
import p2.model.User;
import p2.service.IUserService;

public class UserService implements IUserService {

	private UserDAO userDAO = new UserDAO();

	@Override
	public int insert(User user) {
		return userDAO.insert(user);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);

	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public User findById(int id) {
		return userDAO.findById(id);
	}

	@Override
	public void deleteById(int id) {
		userDAO.deleteById(id);

	}

}
