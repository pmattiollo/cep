package br.com.furb.pmattiollo.tcc.constant;

public enum MeansEnum {

	ITEM("item"),
	ITEM_TYPE("item_type"),
	SOFTWARE("software"),
	COLLECT("collect"),
	REPORT("report"),
	CALCULATION("calculation"),
	USER("user");
	
	private String description;
	
	private MeansEnum(String nome) {
		this.description = nome;
	}

	public String getDescription() {
		return description;
	}
	
	public static MeansEnum getMeanByDescription(String description) {
		if(description.equals("item")) {			
			return ITEM;
		} else if(description.equals("item_type")) {
			return ITEM_TYPE;
		} else if(description.equals("software")) {
			return SOFTWARE;
		} else if(description.equals("collect")) {
			return COLLECT;
		} else if(description.equals("report")) {
			return REPORT;
		} else if(description.equals("calculation")) {
			return CALCULATION;
		} else if(description.equals("user")) {
			return USER;
		}
		
		return null;
	}
	
}
