package p2.service;

import java.util.List;

import p2.model.Taxonomy;

public interface ITaxonomyService {
	
	public int insert(Taxonomy taxonomy);

	public void update(Taxonomy taxonomy);

	public List<Taxonomy> findAll();

	public Taxonomy findById(int id);

	public void deleteById(int id);
	

}
