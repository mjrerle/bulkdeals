package p2.dao;

import java.util.List;

import p2.model.User;

public interface IUserDAO {

	public int insert(User user);

	public boolean update(User user);

	public List<User> findAll();

	public User findById(int id);

	public boolean deleteById(int id);

	public User findByEmailAndPassword(String email, String password);
}
