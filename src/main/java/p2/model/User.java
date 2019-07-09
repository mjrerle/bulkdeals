package p2.model;

import java.util.Date;

public class User extends BaseModel {

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String userRole;

	public User() {
		super();
	}

	public User(int insertId) {
		super(insertId);
	}

	public User(int id, String firstName, String lastName, String username, String password, String userRole,
			int insertId, Date insertDate, int updateId, Date updateDate, boolean isDeleted) {
		super(id, insertId, insertDate, updateId, updateDate, isDeleted);
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password="
				+ password + ", userRole=" + userRole + ", toString()=" + super.toString() + "]";
	}

}
