package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;

public class CalculationDefects implements Calculation {
	
	private static final int SAMPLE_SIZE = 1;
	
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
		BigDecimal sumDefects = new BigDecimal(0.0);
		BigDecimal average = new BigDecimal(0.0);
		
		for(CollectEntity collect : collectList) {
			sumDefects = sumDefects.add(collect.getValue());
		}
		
		average = sumDefects.divide(new BigDecimal(collectList.size() * SAMPLE_SIZE), SCALE, ROUND);		
		BigDecimal sqrtValue = new BigDecimal(Math.sqrt(average.divide(new BigDecimal(collectList.size()), SCALE, ROUND).doubleValue()));		
		
		return average.add(new BigDecimal(SIGMA).multiply(sqrtValue));
	}

	@Override
	public BigDecimal getClResult() {
		BigDecimal sumDefects = new BigDecimal(0.0);
		
		for(CollectEntity collect : collectList) {
			sumDefects = sumDefects.add(collect.getValue());
		}
		
		return sumDefects.divide(new BigDecimal(collectList.size() * SAMPLE_SIZE), SCALE, ROUND);	
	}

	@Override
	public BigDecimal getLclResult() {		
		BigDecimal sumDefects = new BigDecimal(0.0);
		BigDecimal average = new BigDecimal(0.0);
		
		for(CollectEntity collect : collectList) {
			sumDefects = sumDefects.add(collect.getValue());
		}
		
		average = sumDefects.divide(new BigDecimal(collectList.size() * SAMPLE_SIZE), SCALE, ROUND);		
		BigDecimal sqrtValue = new BigDecimal(Math.sqrt(average.divide(new BigDecimal(collectList.size()), SCALE, ROUND).doubleValue()));		
		
		return average.subtract(new BigDecimal(SIGMA).multiply(sqrtValue));
	}

}
