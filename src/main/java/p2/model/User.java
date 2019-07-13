package p2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@SequenceGenerator(sequenceName = "user_seq", name = "u_seq")
	@GeneratedValue(generator = "u_seq", strategy = GenerationType.SEQUENCE)
	private int userId;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String role;
	private String address;
	private int creditCardNumber;
	private int cvv;

	public User() {
		super();
	}

	public User(int id) {
		super();
		this.userId = id;
	}

	public User(int id, String firstName, String lastName, String email, String password, String role, String address,
			int creditCardNumber, int cvv) {
		super();
		this.userId = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.address = address;
		this.creditCardNumber = creditCardNumber;
		this.cvv = cvv;
	}
	
	public User(String firstName, String lastName, String email, String password, String role, String address,
			int creditCardNumber, int cvv) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.address = address;
		this.creditCardNumber = creditCardNumber;
		this.cvv = cvv;
	}

	public int getId() {
		return userId;
	}

	public void setId(int id) {
		this.userId = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(int creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", role=" + role + ", address=" + address + ", creditCardNumber="
				+ creditCardNumber + ", cvv=" + cvv + "]";
	}

}
