package p2.service.impl;


import java.util.List;

import p2.dao.impl.InterestDAO;
import p2.model.Interest;

public class InterestService {

	private static InterestDAO interestDAO = new InterestDAO();

	public static int insert(Interest interest) {
		return interestDAO.insert(interest);
	}

	public static void update(Interest interest) {
		interestDAO.update(interest);
	}

	public static List<Interest> findAll() {
		return interestDAO.findAll();
	}

	public static Interest findById(int id) {
		return interestDAO.findById(id);
	}

	public static void deleteById(int id) {
		interestDAO.deleteById(id);
	}

	public static List<Interest> findByProductId(int productId) {
		return interestDAO.findByProductId(productId);
	}

	public static int getNumberOfInterest_ByProductId(int productId) {
		int count = 0;
		for (Interest interest : findByProductId(productId))
			count += interest.getQuantity();
		return count;

	}

}
