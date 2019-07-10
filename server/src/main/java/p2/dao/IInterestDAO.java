package p2.dao;

import java.util.List;

import p2.model.Interest;

public interface IInterestDAO {

	public int insert(Interest interest);

	public void update(Interest interest);

	public List<Interest> findAll();

	public Interest findById(int id);

	public void deleteById(int id);

}
