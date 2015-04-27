package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;

public interface Calculation {
	
	public static final int SCALE = 5;
	
	public static final RoundingMode ROUND = RoundingMode.HALF_UP;
	
	public static final int SIGMA = 3;
	
	public CalculationEnum getType();
	
	public BigDecimal getUclResult();
	
	public BigDecimal getClResult();
	
	public BigDecimal getLclResult();

}
