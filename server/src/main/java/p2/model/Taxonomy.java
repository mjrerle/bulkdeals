package p2.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="taxonomies")
public class Taxonomy extends BaseModel {

  @Column(name="name")
  private String name;

  @Column(name="type")
  private String type;

  @ManyToOne
  @JoinColumn(name="product_id")
  private Product product;


  public Taxonomy() {
  }

  public Taxonomy(String name, String type, Product product) {
    this.name = name;
    this.type = type;
    this.product = product;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Product getProduct() {
    return this.product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Taxonomy name(String name) {
    this.name = name;
    return this;
  }

  public Taxonomy type(String type) {
    this.type = type;
    return this;
  }

  public Taxonomy product(Product product) {
    this.product = product;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Taxonomy)) {
            return false;
        }
        Taxonomy taxonomy = (Taxonomy) o;
        return Objects.equals(name, taxonomy.name) && Objects.equals(type, taxonomy.type) && Objects.equals(product, taxonomy.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, product);
  }

  @Override
  public String toString() {
    return "{" +
      " name='" + getName() + "'" +
      ", type='" + getType() + "'" +
      ", product='" + getProduct() + "'" +
      "}";
  }

}
