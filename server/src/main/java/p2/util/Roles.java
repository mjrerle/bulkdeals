package p2.util;

public enum Roles {
	
	ADMIN("ADMIN"),
	USER("USER"),
	SELLER("SELLER");
	
	public final String value;
	
	private Roles( String value) {
		this.value = value;
	}


}
