package p2.util;

public enum InterestStatus {
	
	PENDING(0),
	NOR_ENOUGH_INTEREST(1),
	CANCELED_BY_SELLER(2),
	ACCEPTED(3);
	
	public final int value;
	
	private InterestStatus( int value) {
		this.value = value;
	}


}
