package p2.service.impl;

import java.util.List;

import p2.dao.impl.InterestDAO;
import p2.model.Interest;
import p2.util.InterestStatus;

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

	public static int getNumberOfInterestByProductId(int productId) {
		int count = 0;
		for (Interest interest : findByProductId(productId))
			count += interest.getQuantity();
		return count;

	}

	public static int changeStatusToNotEnoughInterest(int productId) {
		return interestDAO.changeStatus(productId, InterestStatus.NOT_ENOUGH_INTEREST.value);
	}

	public static int changeStatusToCancelledBySeller(int productId) {
		return interestDAO.changeStatus(productId, InterestStatus.CANCELLED_BY_SELLER.value);
	}

	public static int changeStatusToDealCompleted(int productId) {
		return interestDAO.changeStatus(productId, InterestStatus.DEAL_COMPLETED.value);
	}
}
