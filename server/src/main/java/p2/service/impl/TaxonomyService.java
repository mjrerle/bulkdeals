package p2.service.impl;

import java.util.List;

import p2.dao.impl.TaxonomyDAO;
import p2.model.Taxonomy;
import p2.service.ITaxonomyService;

public class TaxonomyService implements ITaxonomyService {

  TaxonomyDAO taxonomyDAO = new TaxonomyDAO();

  @Override
  public int insert(Taxonomy taxonomy) {
    return taxonomyDAO.insert(taxonomy);
  }

  @Override
  public void update(Taxonomy taxonomy) {
    taxonomyDAO.update(taxonomy);
  }

  @Override
  public List<Taxonomy> findAll() {
    return taxonomyDAO.findAll();
  }

  @Override
  public Taxonomy findById(int id) {
    return taxonomyDAO.findById(id);
  }

  @Override
  public void deleteById(int id) {
    taxonomyDAO.deleteById(id);
  }
	

}
