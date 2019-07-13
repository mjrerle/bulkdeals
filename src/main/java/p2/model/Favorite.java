package p2.model;

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
	private int id;

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
		this.id = id;
	}

	public Favorite(User user, Product product) {
		super();
		this.user = user;
		this.product = product;
	}

	public Favorite(int id, User user, Product product) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "Favorite [id=" + id + ", user=" + user + ", product=" + product + "]";
	}

}
