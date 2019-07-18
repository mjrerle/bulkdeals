package p2.dao;

import p2.model.Taxonomy;

public interface ITaxonomyDAO {
  public Taxonomy findByTaxonomy(Taxonomy taxonomy);
}