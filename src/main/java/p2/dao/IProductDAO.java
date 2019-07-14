package p2.dao;

import java.util.List;

import p2.model.Product;

public interface IProductDAO {

  public List<Product> findAllProductsByTaxonomy(String name, String type, String subType);
  public List<Product> findAllProductsByType(String type);

  public List<Product> findAllProductsBySubType(String subType);

  public List<Product> findAllProductsByTaxonomyName(String name);
}
