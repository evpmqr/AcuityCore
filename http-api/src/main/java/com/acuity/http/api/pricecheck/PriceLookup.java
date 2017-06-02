package com.acuity.http.api.pricecheck;

/**
 * Created by Eclipseop.
 * Date: 6/1/2017.
 */
public class PriceLookup {

	private long overall;
	private long buying;
	private long buyingQuantity;
	private long selling;
	private long sellingQuantity;

	protected PriceLookup() {
		overall = -1;
		buying = -1;
		buyingQuantity = -1;
		selling = -1;
		sellingQuantity = -1;
	}

	public long getBuying() {
		return buying;
	}

	public long getBuyingQuantity() {
		return buyingQuantity;
	}

	public long getSellingQuantity() {
		return sellingQuantity;
	}

	public long getOverall() {
		return overall;
	}

	public long getSelling() {
		return selling;
	}

	@Override
	public String toString() {
		return "PriceLookup{" +
				"overall=" + overall +
				", buying=" + buying +
				", buyingQuantity=" + buyingQuantity +
				", selling=" + selling +
				", sellingQuantity=" + sellingQuantity +
				'}';
	}
}
