package p2.service.impl;

import java.util.List;

import p2.dao.impl.InterestDAO;
import p2.model.Interest;
import p2.service.IInterestService;

public class InterestService implements IInterestService {

	private InterestDAO interestDAO = new InterestDAO();

	@Override
	public int insert(Interest interest) {
		return interestDAO.insert(interest);
	}

	@Override
	public void update(Interest interest) {
		interestDAO.update(interest);

	}

	@Override
	public List<Interest> findAll() {
		return interestDAO.findAll();
	}

	@Override
	public Interest findById(int id) {
		return interestDAO.findById(id);
	}

	@Override
	public void deleteById(int id) {
		interestDAO.deleteById(id);

	}

}
