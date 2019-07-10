package p2.util;

public enum UserRoles {
	
	ADMIN("ADMIN"),
	USER("USER"),
	SELLER("SELLER");
	
	public final String value;
	
	private UserRoles( String value) {
		this.value = value;
	}


}
