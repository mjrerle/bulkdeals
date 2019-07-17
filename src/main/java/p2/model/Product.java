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
	@Column(name = "product_id", unique = true, nullable = false)
	private int productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "description")
	private String description;

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

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "date_listed_for_interest")
	private LocalDate dateListed;
	// note I pulled java.sql.Date for conversion purposes

	@Column(name = "status")
	private String status;

	@Column(name = "interest_threshold")
	private int interestThreshold;

	@ManyToOne
	@JoinColumn(name = "taxonomy_id")
	private Taxonomy taxonomy;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Product() {
	}

	public Product(int productId) {
		this.productId = productId;
	}

	public Product(String productName, String description, double price, double salePrice, int onSale,
			int generatedInterest, String imageUrl, LocalDate dateListed, String status, int interestThreshold,
			Taxonomy taxonomy, User user) {
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.salePrice = salePrice;
		this.onSale = onSale;
		this.generatedInterest = generatedInterest;
		this.imageUrl = imageUrl;
		this.dateListed = dateListed;
		this.status = status;
		this.interestThreshold = interestThreshold;
		this.taxonomy = taxonomy;
		this.user = user;
	}

	public Product(int productId, String productName, String description, double price, double salePrice, int onSale,
			int generatedInterest, String imageUrl, LocalDate dateListed, String status, int interestThreshold,
			Taxonomy taxonomy, User user) {
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.salePrice = salePrice;
		this.onSale = onSale;
		this.generatedInterest = generatedInterest;
		this.imageUrl = imageUrl;
		this.dateListed = dateListed;
		this.status = status;
		this.interestThreshold = interestThreshold;
		this.taxonomy = taxonomy;
		this.user = user;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public Taxonomy getTaxonomy() {
		return this.taxonomy;
	}

	public void setTaxonomy(Taxonomy taxonomy) {
		this.taxonomy = taxonomy;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product productId(int productId) {
		this.productId = productId;
		return this;
	}

	public Product productName(String productName) {
		this.productName = productName;
		return this;
	}

	public Product description(String description) {
		this.description = description;
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

	public Product imageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public Product taxonomy(Taxonomy taxonomy) {
		this.taxonomy = taxonomy;
		return this;
	}

	public Product user(User user) {
		this.user = user;
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
		return productId == product.productId && Objects.equals(productName, product.productName)
				&& Objects.equals(description, product.description) && price == product.price && salePrice == product.salePrice
				&& onSale == product.onSale && generatedInterest == product.generatedInterest
				&& Objects.equals(imageUrl, product.imageUrl) && Objects.equals(dateListed, product.dateListed)
				&& Objects.equals(status, product.status) && interestThreshold == product.interestThreshold
				&& Objects.equals(taxonomy, product.taxonomy) && Objects.equals(user, product.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, productName, description, price, salePrice, onSale, generatedInterest, imageUrl,
				dateListed, status, interestThreshold, taxonomy, user);
	}

	@Override
	public String toString() {
		return "{" + " productId='" + getProductId() + "'" + ", productName='" + getProductName() + "'" + ", description='"
				+ getDescription() + "'" + ", price='" + getPrice() + "'" + ", salePrice='" + getSalePrice() + "'"
				+ ", onSale='" + getOnSale() + "'" + ", generatedInterest='" + getGeneratedInterest() + "'" + ", imageUrl='"
				+ getImageUrl() + "'" + ", dateListed='" + getDateListed() + "'" + ", status='" + getStatus() + "'"
				+ ", interestThreshold='" + getInterestThreshold() + "'" + ", taxonomy='" + getTaxonomy() + "'" + ", user='"
				+ getUser() + "'" + "}";
	}

}
