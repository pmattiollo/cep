package br.com.furb.pmattiollo.tcc.constant;

public enum ItemTypeEnum {
	
	VARIABLE(1, "Variable", 1),	
	ATTRIBUTE(1, "Attribute", 5);
	
	private int code;
	private String description;
	private int num;
	
	private ItemTypeEnum(int code, String description, int num) {
		this.code = code;
		this.description = description;
		this.num = num;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getNum() {
		return num;
	}

}
