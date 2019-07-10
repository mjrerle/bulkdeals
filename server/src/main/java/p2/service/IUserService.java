package p2.service;

import java.util.List;

import p2.model.User;

public interface IUserService {
	
	public int insert(User user);

	public void update(User user);

	public List<User> findAll();

	public User findById(int id);

	public void deleteById(int id);
	

}
