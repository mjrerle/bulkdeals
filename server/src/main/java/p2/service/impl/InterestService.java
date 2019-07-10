package p2.service.impl;

import java.util.List;

import p2.dao.impl.InterestDAO;
import p2.model.Interest;
import p2.service.IInterestService;

public class InterestService implements IInterestService {

	private InterestDAO interesrDAO = new InterestDAO();

	@Override
	public int insert(Interest interest) {
		return interesrDAO.insert(interest);
	}

	@Override
	public void update(Interest interest) {
		interesrDAO.update(interest);

	}

	@Override
	public List<Interest> findAll() {
		return interesrDAO.findAll();
	}

	@Override
	public Interest findById(int id) {
		return interesrDAO.findById(id);
	}

	@Override
	public void deleteById(int id) {
		interesrDAO.deleteById(id);

	}

}
