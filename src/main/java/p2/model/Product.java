package p2.model;

import java.time.LocalDate;
import java.util.Objects;

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
@Table(name = "Products")
public class Product {
	@Id
	@SequenceGenerator(sequenceName = "product_seq", name = "p_seq")
	@GeneratedValue(generator = "p_seq", strategy = GenerationType.SEQUENCE)
	private int producId;

	@Column(name = "productname")
	private String productName;

	@Column(name = "price")
	private double price;

	@Column(name = "sale_price")
	private double salePrice;

	@Column(name = "on_sale")
	private int onSale;
	// using 0 or 1 to indicate yes or no

	@Column(name = "generated_interest")
	private int generatedInterest;
	// I figure well set an integer limit that correlates to user quantity and set
	// an if threshold.

	@Column(name = "date_listed_for_interest")
	private LocalDate dateListed;
	// note I pulled java.sql.Date for conversion purposes

	@Column(name = "status")
	private String status;

	@Column(name = "interest_threshold")
	private int interestThreshold;

	@ManyToOne
	@JoinColumn(name = "seller")
	private User seller;

	public Product() {
	}
	
	public Product(int id) {
		this.producId = id;
	}

	public Product(String productName, double price, double salePrice, int onSale, int generatedInterest,
			LocalDate dateListed, String status, int interestThreshold, User seller) {
		this.productName = productName;
		this.price = price;
		this.salePrice = salePrice;
		this.onSale = onSale;
		this.generatedInterest = generatedInterest;
		this.dateListed = dateListed;
		this.status = status;
		this.interestThreshold = interestThreshold;
		this.seller = seller;
	}

	public Product(int id, String productName, double price, double salePrice, int onSale, int generatedInterest,
			LocalDate dateListed, String status, int interestThreshold, User seller) {
		this.producId = id;
		this.productName = productName;
		this.price = price;
		this.salePrice = salePrice;
		this.onSale = onSale;
		this.generatedInterest = generatedInterest;
		this.dateListed = dateListed;
		this.status = status;
		this.interestThreshold = interestThreshold;
		this.seller = seller;
	}

	public int getId() {
		return this.producId;
	}

	public void setId(int id) {
		this.producId = id;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public int getOnSale() {
		return this.onSale;
	}

	public void setOnSale(int onSale) {
		this.onSale = onSale;
	}

	public int getGeneratedInterest() {
		return this.generatedInterest;
	}

	public void setGeneratedInterest(int generatedInterest) {
		this.generatedInterest = generatedInterest;
	}

	public LocalDate getDateListed() {
		return this.dateListed;
	}

	public void setDateListed(LocalDate dateListed) {
		this.dateListed = dateListed;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getInterestThreshold() {
		return this.interestThreshold;
	}

	public void setInterestThreshold(int interestThreshold) {
		this.interestThreshold = interestThreshold;
	}

	public User getSeller() {
		return this.seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Product id(int id) {
		this.producId = id;
		return this;
	}

	public Product productName(String productName) {
		this.productName = productName;
		return this;
	}

	public Product price(double price) {
		this.price = price;
		return this;
	}

	public Product salePrice(double salePrice) {
		this.salePrice = salePrice;
		return this;
	}

	public Product onSale(int onSale) {
		this.onSale = onSale;
		return this;
	}

	public Product generatedInterest(int generatedInterest) {
		this.generatedInterest = generatedInterest;
		return this;
	}

	public Product dateListed(LocalDate dateListed) {
		this.dateListed = dateListed;
		return this;
	}

	public Product status(String status) {
		this.status = status;
		return this;
	}

	public Product interestThreshold(int interestThreshold) {
		this.interestThreshold = interestThreshold;
		return this;
	}

	public Product seller(User seller) {
		this.seller = seller;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Product)) {
			return false;
		}
		Product product = (Product) o;
		return producId == product.producId && Objects.equals(productName, product.productName) && price == product.price
				&& salePrice == product.salePrice && onSale == product.onSale && generatedInterest == product.generatedInterest
				&& Objects.equals(dateListed, product.dateListed) && Objects.equals(status, product.status)
				&& interestThreshold == product.interestThreshold && Objects.equals(seller, product.seller);
	}

	@Override
	public int hashCode() {
		return Objects.hash(producId, productName, price, salePrice, onSale, generatedInterest, dateListed, status,
				interestThreshold, seller);
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", productName='" + getProductName() + "'" + ", price='" + getPrice() + "'"
				+ ", salePrice='" + getSalePrice() + "'" + ", onSale='" + getOnSale() + "'" + ", generatedInterest='"
				+ getGeneratedInterest() + "'" + ", dateListed='" + getDateListed() + "'" + ", status='" + getStatus() + "'"
				+ ", interestThreshold='" + getInterestThreshold() + "'" + ", seller='" + getSeller() + "'" + "}";
	}

}
