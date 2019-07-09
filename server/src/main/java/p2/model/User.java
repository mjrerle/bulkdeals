package p2.model;

import java.util.Objects;

public class User extends BaseModel {

	private String firstName;
	private String lastName;
	private String username;
	private String password;

	public User() {
		super();
	}

	public User(int insertId) {
		super(insertId);
	}

	public User(int id, String firstName, String lastName, String username, String password) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public User(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public User lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public User username(String username) {
		this.username = username;
		return this;
	}

	public User password(String password) {
		this.password = password;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof User)) {
			return false;
		}
		User user = (User) o;
		return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName)
				&& Objects.equals(username, user.username) && Objects.equals(password, user.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, username, password);
	}

	@Override
	public String toString() {
		return "{" + " firstName='" + getFirstName() + "'" + ", lastName='" + getLastName() + "'" + ", username='"
				+ getUsername() + "'" + ", password='" + getPassword() + "'" + "}";
	}

}
