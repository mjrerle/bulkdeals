package p2.model;
import java.util.Objects;


public class Role extends BaseModel{
	private String roleName;
	private int role;
	

	
	
	public Role() {
		super();
	}

	public Role(int id, String roleName, int role) {
		super(id);
		this.roleName = roleName;
		this.role = role;
	}

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}

}
