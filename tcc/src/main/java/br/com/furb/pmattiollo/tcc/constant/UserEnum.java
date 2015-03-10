package br.com.furb.pmattiollo.tcc.constant;

public enum UserEnum {
	
	ADM(1, "Administrador"),
	AVALIADOR(2, "Avaliador"),
	OPERADOR(3, "Operador");
	
	private Integer code;
	private String description;
	
	private UserEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}	
	
}
