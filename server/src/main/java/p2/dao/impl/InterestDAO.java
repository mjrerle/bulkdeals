package p2.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import p2.dao.IInterestDAO;
import p2.model.Interest;
import p2.model.Product;
import p2.util.HibernateUtil;

public class InterestDAO extends GenericDAO<Interest> implements IInterestDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByProductId(int productId) {
		Session session = HibernateUtil.getSession();
		List<Product> list = null;

		try {
			list = session.createQuery("FROM Product WHERE product_id = " + productId).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

}
