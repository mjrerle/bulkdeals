package p2.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public abstract class BaseModel {

	@Id
	@SequenceGenerator(sequenceName="seq", name="seq")
	@GeneratedValue(generator="seq", strategy=GenerationType.SEQUENCE)
	private int id;
	private int insertId;
	private Date insertDate;
	private int updateId;
	private Date updateDate; 
	private boolean isDeleted; // 0 - Not Deleted, 1 - DELETED

	public BaseModel() {
		super();
	}

	public BaseModel(int insertId) {
		super();
		this.insertId = insertId;
	}

	public BaseModel(int id, int insertId, Date insertDate, int updateId, Date updateDate, boolean isDeleted) {
		super();
		this.id = id;
		this.insertId = insertId;
		this.insertDate = insertDate;
		this.updateId = updateId;
		this.updateDate = updateDate;
		this.isDeleted = isDeleted;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInsertId() {
		return insertId;
	}

	public void setInsertId(int insertId) {
		this.insertId = insertId;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public int getUpdateId() {
		return updateId;
	}

	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "BaseModel [id=" + id + ", insertId=" + insertId + ", insertDate=" + insertDate + ", updateId="
				+ updateId + ", updateDate=" + updateDate + ", isDeleted=" + isDeleted + "]";
	}
	
	

}
