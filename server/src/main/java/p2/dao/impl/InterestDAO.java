package p2.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import p2.dao.IInterestDAO;
import p2.model.Interest;
import p2.util.HibernateUtil;
import p2.util.InterestStatus;

public class InterestDAO extends GenericDAO<Interest> implements IInterestDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Interest> findByProductId(int productId) {
		Session session = HibernateUtil.getSession();
		List<Interest> list = null;

		try {
			Query query = session.createQuery("FROM Interest WHERE product.id = :productId");
			query.setParameter("productId", productId);
			list = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public int changeStatus(int productId, int status) {
		Session session = HibernateUtil.getSession();
		int numberOfEntities = 0;

		try {
			Query query  = session.createQuery("UPDATE Interest SET Status = :statusValue" + "WHERE product.id = :productId");
			query.setParameter("statusValue", status);
			query.setParameter("productId", productId);
			numberOfEntities = query.executeUpdate();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return numberOfEntities;
	}

}
