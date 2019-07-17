package p2.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import p2.dao.IFavoriteDAO;
import p2.model.Favorite;
import p2.model.User;
import p2.util.HibernateUtil;

public class FavoriteDAO extends GenericDAO<Favorite> implements IFavoriteDAO {
	
	@SuppressWarnings("unchecked")
	public List<Favorite> findByUser(User user){
		Session session = HibernateUtil.getSession();
		List<Favorite> favorites = null;

		try {
			Query query = session.createQuery("FROM Favorites WHERE user_id = :user_id");
			query.setParameter("user_id", user.getUserId());
			favorites = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return favorites;
	}
}
