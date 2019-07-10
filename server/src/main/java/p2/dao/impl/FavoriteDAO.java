package p2.dao.impl;

import p2.model.Favorite;
import p2.model.User;
import p2.util.HibernateUtil;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import p2.dao.IFavoriteDAO;

public class FavoriteDAO extends GenericDAO<Favorite> implements IFavoriteDAO {
	private Class<Favorite> persistentClass;
	@Override
	public int insert(Favorite t) {
		// TODO Auto-generated method stub
		return super.insert(t);
	}

	@Override
	public void update(Favorite t) {
		// TODO Auto-generated method stub
		super.update(t);
	}

	@Override
	public List<Favorite> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public Favorite findById(int id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		super.deleteById(id);
	}
	
	@Override
	public List<Favorite> findByUser(User user){
		Session session = HibernateUtil.getSession();
		List<Favorite> f = null;

		try {
			f = session.createQuery("FROM " + persistentClass.getSimpleName()).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return f;
	}
}
