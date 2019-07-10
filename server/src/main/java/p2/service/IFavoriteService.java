package p2.service;

import java.util.List;

import p2.model.Favorite;
import p2.model.User;

public interface IFavoriteService {

	int insert(Favorite t);

	void update(Favorite t);

	List<Favorite> findAll();

	Favorite findById(int id);

	void deleteById(int id);

	List<Favorite> findByUser(User user);

}
