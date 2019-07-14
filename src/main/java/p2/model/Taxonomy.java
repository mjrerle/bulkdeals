package p2.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Taxonomies")
public class Taxonomy {

  @Id
  @SequenceGenerator(sequenceName = "taxonomies_seq", name = "t_seq")
  @GeneratedValue(generator = "t_seq", strategy = GenerationType.SEQUENCE)
  @Column(name = "taxonomy_id", unique = true, nullable = false)
  private int taxonomyId;

  @Column(name = "name")
  private String name;

  @Column(name = "type")
  private String type;

  @Column(name = "sub_type")
  private String subType;

  public Taxonomy() {
  }

  public Taxonomy(int id) {
    this.taxonomyId = id;
  }

  public Taxonomy(String name, String type, String subType) {
    this.name = name;
    this.type = type;
    this.subType = subType;
  }

  public Taxonomy(int taxonomyId, String name, String type, String subType) {
    this.taxonomyId = taxonomyId;
    this.name = name;
    this.type = type;
    this.subType = subType;
  }

  public int getId() {
    return this.taxonomyId;
  }

  public void setId(int taxonomyId) {
    this.taxonomyId = taxonomyId;
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

  public String getSubType() {
    return this.subType;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public Taxonomy taxonomyId(int taxonomyId) {
    this.taxonomyId = taxonomyId;
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

  public Taxonomy subType(String subType) {
    this.subType = subType;
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
    return taxonomyId == taxonomy.taxonomyId && Objects.equals(name, taxonomy.name)
        && Objects.equals(type, taxonomy.type) && Objects.equals(subType, taxonomy.subType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taxonomyId, name, type, subType);
  }

  @Override
  public String toString() {
    return "{" + " taxonomyId='" + getId() + "'" + ", name='" + getName() + "'" + ", type='" + getType() + "'"
        + ", subType='" + getSubType() + "'" + "}";
  }

}
