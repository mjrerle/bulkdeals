package p2.service;

import java.util.List;

import p2.model.Interest;

public interface IInterestService {

	public int insert(Interest interest);

	public void update(Interest interest);

	public List<Interest> findAll();

	public Interest findById(int id);

	public void deleteById(int id);
	
}
