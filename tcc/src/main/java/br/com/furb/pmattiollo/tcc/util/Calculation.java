package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;

public interface Calculation {
	
	public CalculationEnum getType();
	
	public BigDecimal getLscResult();
	
	public BigDecimal getLcResult();
	
	public BigDecimal getLicResult();

}
