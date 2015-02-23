package br.com.furb.pmattiollo.tcc.domain;

public enum CalculationType {
	
	XI(1, "Gráfico X-Barra Individual"),
	MMEP(2, "Gráfico Média Móvel Exponencial Ponderada"),
	DEF(3, "Gráfico Número de Defeitos");
	
	private Integer code;
	private String description;
	
	private CalculationType(Integer code, String description) {
		this.code = code;
		this.description = description;
	}	
	
	public Integer getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static CalculationType getCalculationTypeByCode(Integer code) {
		switch (code) {
		case 1:
			return CalculationType.XI;
		case 2:
			return CalculationType.MMEP;
		case 3:
			return CalculationType.DEF;
		default:
			return null;
		}
	}
	
}
