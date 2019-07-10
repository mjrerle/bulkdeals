package p2.model;

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
@Table(name = "Taxonomies")
public class Taxonomy {

  @Id
  @SequenceGenerator(sequenceName = "taxonomies_seq", name = "t_seq")
  @GeneratedValue(generator = "t_seq", strategy = GenerationType.SEQUENCE)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "type")
  private String type;

  @Column(name = "sub_type")
  private String sub_type;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  public Taxonomy() {
  }

  public Taxonomy(int id, String name, String type, String sub_type, Product product) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.sub_type = sub_type;
    this.product = product;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getSub_type() {
    return this.sub_type;
  }

  public void setSub_type(String sub_type) {
    this.sub_type = sub_type;
  }

  public Product getProduct() {
    return this.product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Taxonomy id(int id) {
    this.id = id;
    return this;
  }

  public Taxonomy name(String name) {
    this.name = name;
    return this;
  }

  public Taxonomy type(String type) {
    this.type = type;
    return this;
  }

  public Taxonomy sub_type(String sub_type) {
    this.sub_type = sub_type;
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
    return id == taxonomy.id && Objects.equals(name, taxonomy.name) && Objects.equals(type, taxonomy.type)
        && Objects.equals(sub_type, taxonomy.sub_type) && Objects.equals(product, taxonomy.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, type, sub_type, product);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", type='" + getType() + "'" + ", sub_type='"
        + getSub_type() + "'" + ", product='" + getProduct() + "'" + "}";
  }

}
