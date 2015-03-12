package br.com.furb.pmattiollo.tcc.constant;

public enum MeansEnum {

	ATTACH("anexo"),
	SOFTWARE("software"),
	USER("usuario");
	
	private String nome;
	
	private MeansEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
}
