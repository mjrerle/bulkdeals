package p2.model;

import java.sql.Date;

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
	@Column(name="id")
	@SequenceGenerator(sequenceName="product_seq", name="p_seq")
	@GeneratedValue(generator="p_seq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="productname")
	private String productName;
	
	@Column(name="price")
	private double price;
	
	@Column(name="sale_price")
	private double salePrice;
	
	@Column(name="on_sale")
	private int onSale;//using 0 or 1 to indicate yes or no
	
	@Column(name="generated_interest")
	private int generatedInterest;//I figure well set an integer limit that correlates to user quantity and set an if threshold.
	
	@Column(name="date_listed_for_interest")
	private Date dateListed;//note I pulled java.sql.Date for conversion purposes
	
	@ManyToOne
	@JoinColumn(name="seller")
	private User seller;

	public Product() {
		super();
	}

	public Product(String productName, double price, double salePrice, int onSale, int generatedInterest,
			Date dateListed, User seller) {
		super();
		this.productName = productName;
		this.price = price;
		this.salePrice = salePrice;
		this.onSale = onSale;
		this.generatedInterest = generatedInterest;
		this.dateListed = dateListed;
		this.seller = seller;
	}

	public Product(int id, String productName, double price, double salePrice, int onSale, int generatedInterest,
			Date dateListed, User seller) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.salePrice = salePrice;
		this.onSale = onSale;
		this.generatedInterest = generatedInterest;
		this.dateListed = dateListed;
		this.seller = seller;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public int getOnSale() {
		return onSale;
	}

	public void setOnSale(int onSale) {
		this.onSale = onSale;
	}

	public int getGeneratedInterest() {
		return generatedInterest;
	}

	public void setGeneratedInterest(int generatedInterest) {
		this.generatedInterest = generatedInterest;
	}

	public Date getDateListed() {
		return dateListed;
	}

	public void setDateListed(Date dateListed) {
		this.dateListed = dateListed;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", price=" + price + ", salePrice=" + salePrice
				+ ", onSale=" + onSale + ", generatedInterest=" + generatedInterest + ", dateListed=" + dateListed
				+ ", seller=" + seller + "]";
	}
	
	
}
