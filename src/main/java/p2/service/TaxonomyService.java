package p2.service;

import java.util.List;

import p2.dao.impl.TaxonomyDAO;
import p2.model.Product;
import p2.model.Taxonomy;

public class TaxonomyService {

  private static TaxonomyDAO taxonomyDAO = new TaxonomyDAO();

  public static int insert(Taxonomy taxonomy) {
    return taxonomyDAO.insert(taxonomy);
  }

  public static boolean update(Taxonomy taxonomy) {
    return taxonomyDAO.update(taxonomy);
  }

  public static List<Taxonomy> findAll() {
    return taxonomyDAO.findAll();
  }

  public static Taxonomy findById(int id) {
    return taxonomyDAO.findById(id);
  }

  public static boolean deleteById(int id) {
    return taxonomyDAO.deleteById(id);
  }

  public static List<Product> findAllProductsByType(String type) {
    return taxonomyDAO.findAllProductsByType(type);
  }

  public static List<Product> findAllProductsBySubType(String subType) {
    return taxonomyDAO.findAllProductsBySubType(subType);
  }

  public static List<Product> findAllProductsByTaxonomyName(String name) {
    return taxonomyDAO.findAllProductsByTaxonomyName(name);
  }
}
