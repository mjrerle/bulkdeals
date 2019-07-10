package p2.service.impl;

import java.util.List;

import p2.dao.impl.InteresrDAO;
import p2.model.Interest;
import p2.service.IInteresrService;

public class InterestService implements IInteresrService {

	private InteresrDAO interesrDAO = new InteresrDAO();

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
