package p2.model;

import javax.persistence.CascadeType;
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
@Table(name = "Interests")
public class Interest {

	@Id
	@SequenceGenerator(sequenceName = "interests_seq", name = "i_seq")
	@GeneratedValue(generator = "i_seq", strategy = GenerationType.SEQUENCE)
	private int id;

	@ManyToOne (cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne (cascade = CascadeType.REMOVE)
	@JoinColumn(name = "product_id")
	private Product product;

	private int quantity;

	@Column(nullable = false, columnDefinition = "int default 0")
	private int Status;

	public Interest() {
		super();
	}

	public Interest(int id) {
		super();
		this.id = id;
	}

	public Interest(int id, User user, Product product, int status) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		Status = status;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "Interest [id=" + id + ", user=" + user + ", product=" + product + ", quantity=" + quantity + ", Status="
				+ Status + "]";
	}

}
