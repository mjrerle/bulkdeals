package p2.dao;

import java.util.List;

import p2.model.Product;

public interface ITaxonomyDAO {

	public List<Product> findAllProductsByType(String type);

	public List<Product> findAllProductsBySubType(String subType);

	public List<Product> findAllProductsByTaxonomyName(String name);

}