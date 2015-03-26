package br.com.furb.pmattiollo.tcc.util;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;

public class CalculationMMEP implements Calculation {
	
	@Override
	public CalculationEnum getType() {
		return CalculationEnum.MMEP;
	}

	@Override
	public Double getLscResult() {		
		return null;
	}

	@Override
	public Double getLcResult() {
		return null;
	}

	@Override
	public Double getLicResult() {
		return null;
	}

}
