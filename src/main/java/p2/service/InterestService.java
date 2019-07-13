package p2.service;

import java.util.List;

import p2.dao.impl.InterestDAO;
import p2.model.Interest;

public class InterestService {

	private static InterestDAO interestDAO = new InterestDAO();

	public static int insert(Interest interest) {
		return interestDAO.insert(interest);
	}

	public static boolean update(Interest interest) {
		return interestDAO.update(interest);
	}

	public static List<Interest> findAll() {
		return interestDAO.findAll();
	}

	public static Interest findById(int id) {
		return interestDAO.findById(id);
	}

	public static boolean deleteById(int id) {
		return interestDAO.deleteById(id);
	}

	public static List<Interest> findByProductId(int productId) {
		return interestDAO.findByProductId(productId);
	}

	public static List<Interest> findByUserId(int userId) {
		return interestDAO.findByUserId(userId);
	}

	public static int getNumberOfInterestsByProductId(int productId) {
		int count = 0;
		for (Interest interest : findByProductId(productId))
			count += interest.getQuantity();
		return count;
	}

}
