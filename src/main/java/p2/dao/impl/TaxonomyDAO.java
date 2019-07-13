package p2.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import p2.dao.ITaxonomyDAO;
import p2.model.Product;
import p2.model.Taxonomy;
import p2.util.Glogger;
import p2.util.HibernateUtil;

public class TaxonomyDAO extends GenericDAO<Taxonomy> implements ITaxonomyDAO {
	private static Logger logger = Glogger.logger;

  @SuppressWarnings("unchecked")
  public List<Product> findAllProductsByType(String type) {
    Session session = HibernateUtil.getSession();
		List<Product> list = null;

		try {
      Query query = session.createQuery("FROM Products WHERE product_id = (SELECT product_id from Taxonomies WHERE type = :type)");
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
      Query query = session.createQuery("FROM Products WHERE product_id = (SELECT product_id from Taxonomies WHERE sub_type = :sub_type)");
      query.setParameter("sub_type", subType);
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
      Query query = session.createQuery("FROM Products WHERE product_id = (SELECT product_id from Taxonomies WHERE name = :name)");
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

}
