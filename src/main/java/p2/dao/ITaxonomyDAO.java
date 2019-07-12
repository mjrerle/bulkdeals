package p2.dao;

import java.util.List;

import p2.model.Product;
import p2.model.Taxonomy;

public interface ITaxonomyDAO {
  public int insert(Taxonomy taxonomy);

	public boolean update(Taxonomy taxonomy);

	public List<Taxonomy> findAll();

	public Taxonomy findById(int id);

	public boolean deleteById(int id);

	public List<Product> findAllProductsByType(String type);

	public List<Product> findAllProductsBySubType(String subType);

	public List<Product> findAllProductsByTaxonomyName(String name);

}