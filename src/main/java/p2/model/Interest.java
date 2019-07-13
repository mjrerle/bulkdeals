package p2.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Interests")
public class Interest {

	@Id
	@SequenceGenerator(sequenceName = "interests_seq", name = "i_seq")
	@GeneratedValue(generator = "i_seq", strategy = GenerationType.SEQUENCE)
	private int id;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "product_id")
	private Product product;

	private int quantity;

	public Interest() {
		super();
	}

	public Interest(int id) {
		super();
		this.id = id;
	}

	public Interest(User user, Product product, int quantity) {
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}

	public Interest(int id, User user, Product product, int quantity) {
		this.id = id;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Interest id(int id) {
		this.id = id;
		return this;
	}

	public Interest user(User user) {
		this.user = user;
		return this;
	}

	public Interest product(Product product) {
		this.product = product;
		return this;
	}

	public Interest quantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Interest)) {
			return false;
		}
		Interest interest = (Interest) o;
		return id == interest.id && Objects.equals(user, interest.user) && Objects.equals(product, interest.product)
				&& quantity == interest.quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user, product, quantity);
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", user='" + getUser() + "'" + ", product='" + getProduct() + "'"
				+ ", quantity='" + getQuantity() + "'" + "}";
	}

}
