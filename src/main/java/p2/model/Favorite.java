package p2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Favorites")
public class Favorite {

	@Id
	@SequenceGenerator(sequenceName = "favorites_seq", name = "f_seq")
	@GeneratedValue(generator = "f_seq", strategy = GenerationType.SEQUENCE)
	@Column(name = "favorite_id", unique = true, nullable = false)
	private int favoriteId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public Favorite() {
		super();
	}

	public Favorite(int id) {
		super();
		this.favoriteId = id;
	}

	public Favorite(User user, Product product) {
		super();
		this.user = user;
		this.product = product;
	}

	public Favorite(int id, User user, Product product) {
		super();
		this.favoriteId = id;
		this.user = user;
		this.product = product;
	}

	public int getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Favorite [id=" + favoriteId + ", user=" + user + ", product=" + product + "]";
	}

}
