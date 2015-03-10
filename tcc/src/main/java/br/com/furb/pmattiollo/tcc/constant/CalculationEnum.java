package br.com.furb.pmattiollo.tcc.constant;

public enum CalculationEnum {
	
	XI(1, "Gráfico X-Barra Individual"),
	MMEP(2, "Gráfico Média Móvel Exponencial Ponderada"),
	DEF(3, "Gráfico Número de Defeitos");
	
	private Integer code;
	private String description;
	
	private CalculationEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}	
	
	public Integer getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static CalculationEnum getCalculationTypeByCode(Integer code) {
		switch (code) {
		case 1:
			return CalculationEnum.XI;
		case 2:
			return CalculationEnum.MMEP;
		case 3:
			return CalculationEnum.DEF;
		default:
			return null;
		}
	}
	
}
