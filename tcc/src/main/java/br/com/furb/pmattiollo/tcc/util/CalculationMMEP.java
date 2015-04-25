package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;

public class CalculationMMEP implements Calculation {
	
	private static final double L = 2.7;
	private static final double Y = 0.1;
	private List<CollectEntity> collectList;
	
	@SuppressWarnings("unused")
	private CalculationMMEP() {
	}
	
	public CalculationMMEP(List<CollectEntity> collectList) {
		this.collectList = collectList;
	}
	
	@Override
	public CalculationEnum getType() {
		return CalculationEnum.MMEP;
	}

	@Override
	public BigDecimal getUclResult() {		 
		BigDecimal u0 = getSum(collectList).divide(new BigDecimal(collectList.size()), SCALE, ROUND);
		BigDecimal staDev = new BigDecimal(getStandardDeviation(collectList));
		
		return u0.add(new BigDecimal(L).multiply(staDev.multiply(new BigDecimal(Math.sqrt(Y / (2 / Y))))));
	}

	@Override
	public BigDecimal getClResult() {		
		return getSum(collectList).divide(new BigDecimal(collectList.size()), SCALE, ROUND);
	}

	@Override
	public BigDecimal getLclResult() {		 
		BigDecimal u0 = getSum(collectList).divide(new BigDecimal(collectList.size()), SCALE, ROUND);
		BigDecimal staDev = new BigDecimal(getStandardDeviation(collectList));
		
		return u0.subtract(new BigDecimal(L).multiply(staDev.multiply(new BigDecimal(Math.sqrt(Y / (2 / Y))))));
	}
	
	private double getStandardDeviation(List<CollectEntity> collects) {
		return Math.sqrt(getVariancy(collects).doubleValue());
	}
	
	public BigDecimal getVariancy(List<CollectEntity> collects) {		
		BigDecimal p1 = new BigDecimal(1 / Double.valueOf(collects.size() - 1));		
		BigDecimal p2 = getSumOfSquared(collects).subtract(getSum(collects).pow(2).divide(new BigDecimal(collects.size()), SCALE, ROUND));
		
		return p1.multiply(p2);		
	}
	
	private BigDecimal getSumOfSquared(List<CollectEntity> collects) {
		BigDecimal total = new BigDecimal(0.0);
		
		for(CollectEntity collect : collects) {			
			total = total.add(collect.getValue().pow(2));
		}
		
		return total;
	}

	private BigDecimal getSum(List<CollectEntity> collects) {
		BigDecimal total = new BigDecimal(0.0);
		
		for(CollectEntity collect : collects) {			
			total = total.add(collect.getValue());
		}
		
		return total;
	}

}
