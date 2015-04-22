package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.SampleEntity;

public class CalculationXI implements Calculation {
	
	private static final double D2 = 1.128;
	private List<CollectEntity> collectList;
	
	public CalculationXI(List<CollectEntity> collectList) {
		this.collectList = collectList;
	}
	
	@SuppressWarnings("unused")
	private CalculationXI() {
	}
	
	@Override
	public CalculationEnum getType() {
		return CalculationEnum.XI;
	}

	@Override
	public BigDecimal getLscResult() {		
		BigDecimal sumAverage = new BigDecimal(0.0);
		
		BigDecimal previousAverage = new BigDecimal(0.0);
		BigDecimal currentAverage = new BigDecimal(0.0);
		BigDecimal sumMobileAverage = new BigDecimal(0.0);
		int count = 0;
		
		for(CollectEntity collect : collectList) {
			for(SampleEntity sample : collect.getSamples()) {
				sumAverage = sumAverage.add(sample.getValue());
				currentAverage = currentAverage.add(sample.getValue());
			}
			
			// Caso haja mais de uma amostra tira a média sempre
			sumAverage = sumAverage.divide(new BigDecimal(collect.getSamples().size()), SCALE, ROUND);
			currentAverage = currentAverage.divide(new BigDecimal(collect.getSamples().size()), SCALE, ROUND);
			
			if(count == 0) {
				previousAverage = currentAverage;
			} else {
				previousAverage = previousAverage.add(currentAverage).divide(new BigDecimal(2), SCALE, ROUND);
			}
			
			sumMobileAverage = sumMobileAverage.add(previousAverage);
			count ++;
		}
		
		BigDecimal valuesAverage = sumAverage.divide(new BigDecimal(collectList.size()), SCALE, ROUND);
		BigDecimal mobileRangeAverage = sumMobileAverage.divide(new BigDecimal(collectList.size()), SCALE, ROUND);	
		
		return valuesAverage.add(mobileRangeAverage.divide(new BigDecimal(D2), SCALE, ROUND).multiply(new BigDecimal(3)));
	}

	@Override
	public BigDecimal getLcResult() {		
		BigDecimal sumAverage = new BigDecimal(0.0);
		
		for(CollectEntity collect : collectList) {
			for(SampleEntity sample : collect.getSamples()) {
				sumAverage = sumAverage.add(sample.getValue());
			}
			
			// Caso haja mais de uma amostra tira a média sempre
			sumAverage = sumAverage.divide(new BigDecimal(collect.getSamples().size()), SCALE, ROUND);
		}
		
		return sumAverage.divide(new BigDecimal(collectList.size()), SCALE, ROUND);
	}

	@Override
	public BigDecimal getLicResult() {		
		BigDecimal sumAverage = new BigDecimal(0.0);
		
		BigDecimal previousAverage = new BigDecimal(0.0);
		BigDecimal currentAverage = new BigDecimal(0.0);
		BigDecimal sumMobileAverage = new BigDecimal(0.0);
		int count = 0;
		
		for(CollectEntity collect : collectList) {
			for(SampleEntity sample : collect.getSamples()) {
				sumAverage = sumAverage.add(sample.getValue());
				currentAverage = currentAverage.add(sample.getValue());
			}
			
			// Caso haja mais de uma amostra tira a média sempre
			sumAverage = sumAverage.divide(new BigDecimal(collect.getSamples().size()), SCALE, ROUND);
			currentAverage = currentAverage.divide(new BigDecimal(collect.getSamples().size()), SCALE, ROUND);
			
			if(count == 0) {
				previousAverage = currentAverage;
			} else {
				previousAverage = previousAverage.add(currentAverage).divide(new BigDecimal(2), SCALE, ROUND);
			}
			
			sumMobileAverage = sumMobileAverage.add(previousAverage);
			count ++;
		}
		
		BigDecimal valuesAverage = sumAverage.divide(new BigDecimal(collectList.size()), SCALE, ROUND);
		BigDecimal mobileRangeAverage = sumMobileAverage.divide(new BigDecimal(collectList.size()), SCALE, ROUND);	
		
		return valuesAverage.subtract(mobileRangeAverage.divide(new BigDecimal(D2), SCALE, ROUND).multiply(new BigDecimal(3)));
	}
	
}
