package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;

public interface Calculation {
	
	public static final int SCALE = 5;
	
	public static final RoundingMode ROUND = RoundingMode.HALF_UP;
	
	public CalculationEnum getType();
	
	public BigDecimal getLscResult();
	
	public BigDecimal getLcResult();
	
	public BigDecimal getLicResult();

}
