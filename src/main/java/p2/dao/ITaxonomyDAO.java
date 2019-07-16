package p2.dao;

import java.util.List;

import p2.model.Taxonomy;

public interface ITaxonomyDAO {
  public List<Taxonomy> findByTaxonomy(Taxonomy taxonomy);
}