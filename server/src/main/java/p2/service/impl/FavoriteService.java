package p2.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import p2.service.IFavoriteService;
import p2.model.Favorite;
import p2.model.User;
import p2.util.HibernateUtil;
import p2.dao.impl.FavoriteDAO;

public class FavoriteService implements IFavoriteService{
	
	@Override
	public int insert(Favorite t) {
		FavoriteDAO f = new FavoriteDAO();
		return f.insert(t);
	}

	@Override
	public void update(Favorite t) {
		FavoriteDAO f = new FavoriteDAO();
		f.update(t);
	}

	@Override
	public List<Favorite> findAll() {
		FavoriteDAO f = new FavoriteDAO();
		return f.findAll();
	}

	@Override
	public Favorite findById(int id) {
		FavoriteDAO f = new FavoriteDAO();
		return f.findById(id);
	}

	@Override
	public void deleteById(int id) {
		FavoriteDAO f = new FavoriteDAO();
		f.deleteById(id);
	}
	
	@Override
	public List<Favorite> findByUser(User user){
		FavoriteDAO f = new FavoriteDAO();
		return f.findByUser(user);
	}

}
