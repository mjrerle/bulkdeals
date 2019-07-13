package p2.dao;

import java.util.List;

import p2.model.Interest;

public interface IInterestDAO {
	
	public List<Interest> findByProductId(int productId);
	public List<Interest> findByUserId(int userId);

}
