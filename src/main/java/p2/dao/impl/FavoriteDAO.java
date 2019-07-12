package p2.dao.impl;

import p2.model.Favorite;
import p2.model.User;
import p2.util.HibernateUtil;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import p2.dao.IFavoriteDAO;

public class FavoriteDAO extends GenericDAO<Favorite> implements IFavoriteDAO {
	
	@SuppressWarnings("unchecked")
	public List<Favorite> findByUser(User user){
		Session session = HibernateUtil.getSession();
		List<Favorite> f = null;

		try {
			f = session.createQuery("FROM favorites WHERE user.id = " + user.getId()).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return f;
	}
}
