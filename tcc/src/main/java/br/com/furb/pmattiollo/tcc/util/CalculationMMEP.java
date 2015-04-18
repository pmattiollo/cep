package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.SampleEntity;

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
	public BigDecimal getLscResult() {
		List<SampleEntity> samples = new ArrayList<SampleEntity>();
		
		for(CollectEntity collect : collectList) {
			samples.addAll(collect.getSamples());
		}
		 
		BigDecimal u0 = getSum(samples).divide(new BigDecimal(samples.size()));
		BigDecimal staDev = new BigDecimal(getStandardDeviation(samples));
		
		return u0.add(new BigDecimal(L).multiply(staDev.multiply(new BigDecimal(Math.sqrt(Y / (2 / Y))))));
	}

	@Override
	public BigDecimal getLcResult() {
		List<SampleEntity> samples = new ArrayList<SampleEntity>();
		
		for(CollectEntity collect : collectList) {
			samples.addAll(collect.getSamples());
		}
		
		return getSum(samples).divide(new BigDecimal(samples.size()));
	}

	@Override
	public BigDecimal getLicResult() {
		List<SampleEntity> samples = new ArrayList<SampleEntity>();
		
		for(CollectEntity collect : collectList) {
			samples.addAll(collect.getSamples());
		}
		 
		BigDecimal u0 = getSum(samples).divide(new BigDecimal(samples.size()));
		BigDecimal staDev = new BigDecimal(getStandardDeviation(samples));
		
		return u0.subtract(new BigDecimal(L).multiply(staDev.multiply(new BigDecimal(Math.sqrt(Y / (2 / Y))))));
	}
	
	private double getStandardDeviation(List<SampleEntity> samples) {
		return Math.sqrt(getVariancy(samples).doubleValue());
	}
	
	public BigDecimal getVariancy(List<SampleEntity> samples) {		
		BigDecimal p1 = new BigDecimal(1 / Double.valueOf(samples.size() - 1));		
		BigDecimal p2 = getSumOfSquared(samples).subtract(getSum(samples).pow(2).divide(new BigDecimal(samples.size())));
		
		return p1.multiply(p2);		
	}
	
	private BigDecimal getSumOfSquared(List<SampleEntity> samples) {
		BigDecimal total = new BigDecimal(0.0);
		
		for(SampleEntity sample : samples) {			
			total.add(sample.getValue().pow(2));
		}
		
		return total;
	}

	private BigDecimal getSum(List<SampleEntity> samples) {
		BigDecimal total = new BigDecimal(0.0);
		
		for(SampleEntity sample : samples) {			
			total.add(sample.getValue());
		}
		
		return total;
	}


}
