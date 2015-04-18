package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.SampleEntity;

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
	public BigDecimal getLscResult() {		
		int collectNumber = collectList.size();
		BigDecimal sumDefects = new BigDecimal(0.0);
		BigDecimal average = new BigDecimal(0.0);
		
		for(CollectEntity collect : collectList) {
			for(SampleEntity sample : collect.getSamples()) {
				sumDefects.add(sample.getValue());
			}
		}
		
		average = (sumDefects.divide(new BigDecimal(collectNumber * SIZE)));
		
		return average.add(new BigDecimal(3 * Math.sqrt(average.divide(new BigDecimal(SIZE)).doubleValue())));
	}

	@Override
	public BigDecimal getLcResult() {		
		int collectNumber = collectList.size();
		BigDecimal sumDefects = new BigDecimal(0.0);
		
		for(CollectEntity collect : collectList) {
			for(SampleEntity sample : collect.getSamples()) {
				sumDefects.add(sample.getValue());
			}
		}
		
		return (sumDefects.divide(new BigDecimal(collectNumber * SIZE)));		
	}

	@Override
	public BigDecimal getLicResult() {		
		int collectNumber = collectList.size();
		BigDecimal sumDefects = new BigDecimal(0.0);
		BigDecimal average = new BigDecimal(0.0);
		
		for(CollectEntity collect : collectList) {
			for(SampleEntity sample : collect.getSamples()) {
				sumDefects.add(sample.getValue());
			}
		}
		
		average = (sumDefects.divide(new BigDecimal(collectNumber * SIZE)));
		
		return average.subtract(new BigDecimal(3 * Math.sqrt(average.divide(new BigDecimal(SIZE)).doubleValue())));
	}

}
