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
@Table(name = "Purchases")
public class Purchase {

  @Id
  @SequenceGenerator(sequenceName = "purchases_seq", name = "s_seq")
  @GeneratedValue(generator = "s_seq", strategy = GenerationType.SEQUENCE)
  @Column(name = "purchase_id", unique = true, nullable = false)
  private int purchaseId;

  @Column(name = "date_purchased")
  private LocalDate datePurchased;

  @Column(name = "cost")
  private long cost;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  public Purchase() {
  }

  public Purchase(int id) {
    this.purchaseId = id;
  }

  public Purchase(LocalDate datePurchased, long cost, User user, Product product) {
    this.datePurchased = datePurchased;
    this.cost = cost;
    this.user = user;
    this.product = product;
  }

  public Purchase(int purchaseId, LocalDate datePurchased, long cost, User user, Product product) {
    this.purchaseId = purchaseId;
    this.datePurchased = datePurchased;
    this.cost = cost;
    this.user = user;
    this.product = product;
  }

  public int getPurchaseId() {
    return this.purchaseId;
  }

  public void setPurchaseId(int purchaseId) {
    this.purchaseId = purchaseId;
  }

  public LocalDate getDatePurchased() {
    return this.datePurchased;
  }

  public void setDatePurchased(LocalDate datePurchased) {
    this.datePurchased = datePurchased;
  }

  public long getCost() {
    return this.cost;
  }

  public void setCost(long cost) {
    this.cost = cost;
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

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Purchase)) {
      return false;
    }
    Purchase purchase = (Purchase) o;
    return purchaseId == purchase.purchaseId && Objects.equals(datePurchased, purchase.datePurchased)
        && cost == purchase.cost && Objects.equals(user, purchase.user) && Objects.equals(product, purchase.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(purchaseId, datePurchased, cost, user, product);
  }

  @Override
  public String toString() {
    return "{" + " purchaseId='" + getPurchaseId() + "'" + ", datePurchased='" + getDatePurchased() + "'" + ", cost='"
        + getCost() + "'" + ", user='" + getUser() + "'" + ", product='" + getProduct() + "'" + "}";
  }

}
