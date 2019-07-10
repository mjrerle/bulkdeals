package p2.model;
import java.util.Objects;

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

@Entity
public class Role{
	
	@Id
	@Column(name="r_id")
	@SequenceGenerator(sequenceName="role_seq", name="r_seq")
	@GeneratedValue(generator="r_seq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="rolename")
	private String rolename;
	
	@Column(name="rolenumber")
	private int rolenumber;
	
	@ManyToOne
	@JoinColumn(name="user")
	private User user;
	
	public Role() {
		super();
	}

	public Role(String rolename, int rolenumber, User user) {
		super();
		this.rolename = rolename;
		this.rolenumber = rolenumber;
		this.user = user;
	}

	public Role(int id, String rolename, int rolenumber, User user) {
		super();
		this.id = id;
		this.rolename = rolename;
		this.rolenumber = rolenumber;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public int getRolenumber() {
		return rolenumber;
	}

	public void setRolenumber(int rolenumber) {
		this.rolenumber = rolenumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", rolenumber=" + rolenumber + ", user=" + user + "]";
	}

	
}
