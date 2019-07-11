package p2.dao;

import java.util.List;

import p2.model.Favorite;
import p2.model.User;

public interface IFavoriteDAO {

	List<Favorite> findByUser(User user);

}
