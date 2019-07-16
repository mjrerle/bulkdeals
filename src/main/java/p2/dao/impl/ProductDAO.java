package p2.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import p2.dao.IProductDAO;
import p2.model.Product;
import p2.util.Glogger;
import p2.util.HibernateUtil;

public class ProductDAO extends GenericDAO<Product> implements IProductDAO {
  private static Logger logger = Glogger.logger;

  @SuppressWarnings("unchecked")
  public List<Product> findAllProductsByType(String type) {
    Session session = HibernateUtil.getSession();
    List<Product> list = null;

    try {
      Query query = session
          .createQuery("FROM Product p, Taxonomy t where p.taxonomy = t.taxonomyId and t.type = :type");
      query.setParameter("type", type);
      list = query.list();
    } catch (HibernateException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    } finally {
      session.close();
    }
    return list;
  }

  @SuppressWarnings("unchecked")
  public List<Product> findAllProductsBySubType(String subType) {
    Session session = HibernateUtil.getSession();
    List<Product> list = null;

    try {
      Query query = session
          .createQuery("FROM Product p, Taxonomy t where p.taxonomy = t.taxonomyId and t.subType = :subType");
      // FROM Product p Where p.taxonomy
      query.setParameter("subType", subType);
      list = query.list();
    } catch (HibernateException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    } finally {
      session.close();
    }
    return list;
  }

  @SuppressWarnings("unchecked")
  public List<Product> findAllProductsByTaxonomyName(String name) {
    Session session = HibernateUtil.getSession();
    List<Product> list = null;

    try {
      Query query = session
          .createQuery("FROM Product p, Taxonomy t where p.taxonomy = t.taxonomyId and t.name = :name");

      query.setParameter("name", name);
      list = query.list();
    } catch (HibernateException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    } finally {
      session.close();
    }
    return list;
  }

  @SuppressWarnings("unchecked")
  public List<Product> findAllProductsByTaxonomy(String name, String type, String subType) {
    Session session = HibernateUtil.getSession();
    List<Product> list = null;

    try {
      Query query = session
          .createQuery("FROM Product p, Taxonomy t where p.taxonomy = t.taxonomyId and t.name like :name and t.type like :type and t.subType like :subType");

      query.setParameter("name", name);
      query.setParameter("type", type);
      query.setParameter("subType", subType);

      list = query.list();
    } catch (HibernateException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    } finally {
      session.close();
    }
    return list;
  }
}
