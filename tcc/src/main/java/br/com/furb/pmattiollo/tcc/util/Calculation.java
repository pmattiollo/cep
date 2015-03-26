package br.com.furb.pmattiollo.tcc.util;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;

public interface Calculation {
	
	public CalculationEnum getType();
	
	public Double getLscResult();
	
	public Double getLcResult();
	
	public Double getLicResult();

}
