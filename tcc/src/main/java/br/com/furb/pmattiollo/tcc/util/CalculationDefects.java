package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;

public class CalculationDefects implements Calculation {
	
	private static final int SIZE = 5;
	
	private List<CollectEntity> collectList;
	
	public CalculationDefects(List<CollectEntity> collectList) {
		this.collectList = collectList;
	}
	
	@SuppressWarnings("unused")
	private CalculationDefects() {
	}
	
	@Override
	public CalculationEnum getType() {
		return CalculationEnum.DEF;
	}

	@Override
	public BigDecimal getUclResult() {		
		int collectNumber = collectList.size();
		BigDecimal sumDefects = new BigDecimal(0.0);
		BigDecimal average = new BigDecimal(0.0);
		
		for(CollectEntity collect : collectList) {
			sumDefects = sumDefects.add(collect.getValue());
		}
		
		average = sumDefects.divide(new BigDecimal(collectNumber * SIZE), SCALE, ROUND);
		
		return average.add(new BigDecimal(3 * Math.sqrt(average.divide(new BigDecimal(SIZE), SCALE, ROUND).doubleValue())));
	}

	@Override
	public BigDecimal getClResult() {		
		int collectNumber = collectList.size();
		BigDecimal sumDefects = new BigDecimal(0.0);
		
		for(CollectEntity collect : collectList) {
			sumDefects = sumDefects.add(collect.getValue());
		}
		
		return sumDefects.divide(new BigDecimal(collectNumber * SIZE), SCALE, ROUND);	
	}

	@Override
	public BigDecimal getLclResult() {		
		int collectNumber = collectList.size();
		BigDecimal sumDefects = new BigDecimal(0.0);
		BigDecimal average = new BigDecimal(0.0);
		
		for(CollectEntity collect : collectList) {
			sumDefects = sumDefects.add(collect.getValue());
		}
		
		average = sumDefects.divide(new BigDecimal(collectNumber * SIZE), SCALE, ROUND);
		
		return average.subtract(new BigDecimal(3 * Math.sqrt(average.divide(new BigDecimal(SIZE), SCALE, ROUND).doubleValue())));
	}

}
