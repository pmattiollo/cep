package br.com.furb.pmattiollo.tcc.constant;

public enum OperationsEnum {

	LIST("list"),
	UPDATE("update"),
	CLASSIFICATION("classification");
	
	private String description;
	
	private OperationsEnum(String nome) {
		this.description = nome;
	}

	public String getDescription() {
		return description;
	}
	
}
