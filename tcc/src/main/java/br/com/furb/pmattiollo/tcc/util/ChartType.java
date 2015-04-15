package br.com.furb.pmattiollo.tcc.util;

public enum ChartType {

	LINE("line", false, "Line");
	
	private final String type;
	private final Boolean stacked;
	private final String name;
	
	private ChartType(String type, Boolean stacked, String name) {
		this.type = type;
		this.stacked = stacked;
		this.name = name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public Boolean isStacked() {
		return this.stacked;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getType();
	}
}
