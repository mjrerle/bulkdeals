package p2.util;

public enum InterestStatus {
	
	DEAL_PENDING(0),
	NOT_ENOUGH_INTEREST(1),
	CANCELLED_BY_SELLER(2),
	DEAL_COMPLETED(3);
	
	public final int value;
	
	private InterestStatus( int value) {
		this.value = value;
	}


}
