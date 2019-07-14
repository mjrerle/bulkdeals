package p2.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

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
          .createQuery("FROM Product WHERE taxonomy = (SELECT taxonomyId FROM Taxonomy WHERE type = :type)");
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
          .createQuery("FROM Product WHERE taxonomy = (SELECT taxonomyId FROM Taxonomy WHERE subType = :subType)");
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
          .createQuery("FROM Product WHERE taxonomy = (SELECT taxonomyId FROM Taxonomy WHERE name = :name)");

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
      Criteria crit = session.createCriteria(Product.class);
      Criterion nameCrit = Restrictions.like("name", name);
      Criterion typeCrit = Restrictions.like("type", type);
      Criterion subTypeCrit = Restrictions.like("sub_type", subType);
      LogicalExpression and = Restrictions.and(typeCrit, nameCrit);
      crit.add(and);
      and = Restrictions.and(typeCrit, subTypeCrit);
      crit.add(and);
      and = Restrictions.and(nameCrit, subTypeCrit);
      crit.add(and);
      list = crit.list();
    } catch (HibernateException e) {
      logger.warn(e.getMessage());
      e.printStackTrace();
    } finally {
      session.close();
    }
    return list;
  }
}
