package p2.dao;

import java.util.List;

import p2.model.Taxonomy;

public interface ITaxonomyDAO {
  public int insert(Taxonomy taxonomy);

	public void update(Taxonomy taxonomy);

	public List<Taxonomy> findAll();

	public Taxonomy findById(int id);

	public void deleteById(int id);

}