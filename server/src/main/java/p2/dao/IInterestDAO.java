package p2.dao;

import java.util.List;

import p2.model.Interest;
import p2.model.Product;

public interface IInterestDAO {

	public int insert(Interest interest);

	public boolean update(Interest interest);

	public List<Interest> findAll();

	public Interest findById(int id);

	public boolean deleteById(int id);
	
	public List<Interest> findByProductId(int productId);

	public int changeStatus(int productId, int status);

}
