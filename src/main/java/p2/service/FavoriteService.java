package p2.service;

import java.util.List;

import p2.model.Favorite;
import p2.model.User;

import p2.dao.impl.FavoriteDAO;

public class FavoriteService {
	private static FavoriteDAO favoriteDAO = new FavoriteDAO();

	public static int insert(Favorite t) {

		return favoriteDAO.insert(t);
	}

	public static boolean update(Favorite t) {

		return favoriteDAO.update(t);
	}

	public static List<Favorite> findAll() {

		return favoriteDAO.findAll();
	}

	public static Favorite findById(int id) {

		return favoriteDAO.findById(id);
	}

	public static boolean deleteById(int id) {

		return favoriteDAO.deleteById(id);
	}

	public static List<Favorite> findByUser(User user) {

		return favoriteDAO.findByUser(user);
	}

}
