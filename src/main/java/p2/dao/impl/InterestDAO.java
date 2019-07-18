package p2.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import p2.dao.IInterestDAO;
import p2.model.Interest;
import p2.util.HibernateUtil;

public class InterestDAO extends GenericDAO<Interest> implements IInterestDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Interest> findByProductId(int productId) {
		Session session = HibernateUtil.getSession();
		List<Interest> list = null;

		try {
			Query query = session.createQuery("FROM Interest WHERE product.productId = :productId");
			query.setParameter("productId", productId);
			list = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Interest> findByUserId(int userId) {
		Session session = HibernateUtil.getSession();
		List<Interest> list = null;

		try {
			Query query = session.createQuery("FROM Interest WHERE user.userId = :userId");
			query.setParameter("user_id", userId);
			list = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}
