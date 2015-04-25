package br.com.furb.pmattiollo.tcc.constant;

public enum MeansEnum {

	UNIT("unit"),
	ITEM("item"),
	SOFTWARE("software"),
	COLLECT("collect"),
	GRAPH("graph"),
	REPORT("report"),
	USER("user");
	
	private String description;
	
	private MeansEnum(String nome) {
		this.description = nome;
	}

	public String getDescription() {
		return description;
	}
	
	public static MeansEnum getMeanByDescription(String description) {
		if(description.equals("unit")) {
			return UNIT;
		} else if(description.equals("item")) {			
			return ITEM;
		} else if(description.equals("software")) {
			return SOFTWARE;
		} else if(description.equals("collect")) {
			return COLLECT;
		} else if(description.equals("graph")) {
			return GRAPH;
		} else if(description.equals("report")) {
			return REPORT;
		} else if(description.equals("user")) {
			return USER;
		}
		
		return null;
	}
	
}
