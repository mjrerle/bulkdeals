package p2.util;

public enum ThresholdStatus {
	
	WITHIN_THRESHOLD("Within Threshold"),
	NEVER_SURPASSED_THRESHOLD("Never Surpassed Threshold"),
	SURPASSED_THRESHOLD("Surpassed Threshold"),
	CANCELLED_BY_SELLER("Cancelled By Seller"),
	PRETTY("Pretty");

	public final String value;
	
	private ThresholdStatus( String value) {
		this.value = value;
	}


}

