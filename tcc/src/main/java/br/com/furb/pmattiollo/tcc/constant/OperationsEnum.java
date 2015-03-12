package br.com.furb.pmattiollo.tcc.constant;

public enum OperationsEnum {

	LIST("list"),
	UPDATE("update"),
	INSERT("insert"),
	DELETE("delete");
	
	private String nome;
	
	/**
	 * Construtor do enum, com parametro nomeExtenso.
	 * 
	 * @param nome
	 *            String - O nome por extenso do enum
	 */
	private OperationsEnum(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o nome em extenso do enum.
	 * 
	 * @return String - O nome por extenso
	 */
	public String getNome() {
		return nome;
	}
	
}
