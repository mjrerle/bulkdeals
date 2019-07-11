package p2.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import p2.model.Favorite;
import p2.model.User;
import p2.util.HibernateUtil;
import p2.dao.impl.FavoriteDAO;

public class FavoriteService{
	

	public static int insert(Favorite t) {
		FavoriteDAO f = new FavoriteDAO();
		return f.insert(t);
	}


	public static void update(Favorite t) {
		FavoriteDAO f = new FavoriteDAO();
		f.update(t);
	}


	public static List<Favorite> findAll() {
		FavoriteDAO f = new FavoriteDAO();
		return f.findAll();
	}


	public static Favorite findById(int id) {
		FavoriteDAO f = new FavoriteDAO();
		return f.findById(id);
	}


	public static void deleteById(int id) {
		FavoriteDAO f = new FavoriteDAO();
		f.deleteById(id);
	}
	

	public static List<Favorite> findByUser(User user){
		FavoriteDAO f = new FavoriteDAO();
		return f.findByUser(user);
	}

}
