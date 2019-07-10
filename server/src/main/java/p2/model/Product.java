package p2.model;
import java.util.Objects;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@Column(name="status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="seller")
	private User seller;

	public Product() {
		super();
	}

	public Product(String productName, double price, double salePrice, int onSale, int generatedInterest,
			Date dateListed,String status, User seller) {
		super();
		this.productName = productName;
		this.price = price;
		this.salePrice = salePrice;
		this.onSale = onSale;
		this.generatedInterest = generatedInterest;
		this.dateListed = dateListed;
		this.seller = seller;
		this.status = status;
	}

	public Product(int id, String productName, double price, double salePrice, int onSale, int generatedInterest,
			Date dateListed, String status, User seller) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.salePrice = salePrice;
		this.onSale = onSale;
		this.generatedInterest = generatedInterest;
		this.dateListed = dateListed;
		this.seller = seller;
		this.status = status;
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
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateListed == null) ? 0 : dateListed.hashCode());
		result = prime * result + generatedInterest;
		result = prime * result + id;
		result = prime * result + onSale;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		temp = Double.doubleToLongBits(salePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (dateListed == null) {
			if (other.dateListed != null)
				return false;
		} else if (!dateListed.equals(other.dateListed))
			return false;
		if (generatedInterest != other.generatedInterest)
			return false;
		if (id != other.id)
			return false;
		if (onSale != other.onSale)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (Double.doubleToLongBits(salePrice) != Double.doubleToLongBits(other.salePrice))
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", price=" + price + ", salePrice=" + salePrice
				+ ", onSale=" + onSale + ", generatedInterest=" + generatedInterest + ", dateListed=" + dateListed
				+ ", status=" + status + ", seller=" + seller + "]";
	}
	
	
}
