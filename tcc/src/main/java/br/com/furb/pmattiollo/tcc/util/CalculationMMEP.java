package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;

public class CalculationMMEP extends CalculationAbstract {
	
	private static final double Y = 0.8;
	private List<CollectEntity> collectList;
	private ItemEntity item;
	
	@SuppressWarnings("unused")
	private CalculationMMEP() {
	}
	
	public CalculationMMEP(List<CollectEntity> collectList, ItemEntity item) {
		this.collectList = collectList;
		this.item = item;
	}
	
	@Override
	public CalculationEnum getType() {
		return CalculationEnum.MMEP;
	}

	@Override
	public BigDecimal getUclResult() {
		if(ucl == null) {			
			BigDecimal u0 = getU0();
			BigDecimal staDev = new BigDecimal(getStandardDeviation(collectList));
			
			ucl = u0.add(new BigDecimal(SIGMA).multiply(staDev.multiply(new BigDecimal(Math.sqrt(Y / (2 / Y))))));
		}
		
		return ucl;
	}

	@Override
	public BigDecimal getClResult() {
		if(cl == null) {
			cl = getU0();
		}
		
		return cl;
	}

	@Override
	public BigDecimal getLclResult() {
		if(lcl == null) {			
			BigDecimal u0 = getU0();
			BigDecimal staDev = new BigDecimal(getStandardDeviation(collectList));
			
			lcl = u0.subtract(new BigDecimal(SIGMA).multiply(staDev.multiply(new BigDecimal(Math.sqrt(Y / (2 / Y))))));
		}
		
		return lcl;
	}
	
	private BigDecimal getU0() {
		BigDecimal average = item.getUsl().subtract(item.getLsl());
		average = average.divide(new BigDecimal(2), SCALE, ROUND);
		
		return item.getLsl().add(average);
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
