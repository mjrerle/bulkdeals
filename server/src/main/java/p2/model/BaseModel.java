package p2.model;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public abstract class BaseModel {

	@Id
	@SequenceGenerator(sequenceName="seq", name="seq")
	@GeneratedValue(generator="seq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	public BaseModel() {
	}

	public BaseModel(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BaseModel id(int id) {
		this.id = id;
		return this;
	}

	@Override
		public boolean equals(Object o) {
				if (o == this)
						return true;
				if (!(o instanceof BaseModel)) {
						return false;
				}
				BaseModel baseModel = (BaseModel) o;
				return id == baseModel.id;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			"}";
	}
	
}
