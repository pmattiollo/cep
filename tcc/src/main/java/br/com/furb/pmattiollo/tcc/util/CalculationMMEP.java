package br.com.furb.pmattiollo.tcc.util;

import java.util.ArrayList;
import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SampleEntity;
import br.com.furb.pmattiollo.tcc.persistence.CollectDAO;

public class CalculationMMEP implements Calculation {
	
	private static final double L = 2.7;
	private static final double Y = 0.1;
	private ItemEntity item;
	
	@SuppressWarnings("unused")
	private CalculationMMEP() {
	}
	
	public CalculationMMEP(ItemEntity item) {
		this.item = item;
	}
	
	@Override
	public CalculationEnum getType() {
		return CalculationEnum.MMEP;
	}

	@Override
	public Double getLscResult() {		
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findFinishedByItem(item);
		List<SampleEntity> samples = new ArrayList<SampleEntity>();
		
		for(CollectEntity collect : collectList) {
			samples.addAll(collect.getSamples());
		}
		
		double u0 = getSum(samples) / samples.size();
		double staDev = getStandardDeviation(samples);
		
		return u0 + (L * staDev * Math.sqrt(Y / (2 / Y)));
	}

	@Override
	public Double getLcResult() {
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findFinishedByItem(item);
		List<SampleEntity> samples = new ArrayList<SampleEntity>();
		
		for(CollectEntity collect : collectList) {
			samples.addAll(collect.getSamples());
		}
		
		return getSum(samples) / samples.size();
	}

	@Override
	public Double getLicResult() {
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findFinishedByItem(item);
		List<SampleEntity> samples = new ArrayList<SampleEntity>();
		
		for(CollectEntity collect : collectList) {
			samples.addAll(collect.getSamples());
		}
		
		double u0 = getSum(samples) / samples.size();
		double staDev = getStandardDeviation(samples);
		
		return u0 - (L * staDev * Math.sqrt(Y / (2 / Y)));
	}
	
	private double getStandardDeviation(List<SampleEntity> samples) {
		return Math.sqrt(getVariancy(samples));
	}
	
	public double getVariancy(List<SampleEntity> samples) {		
		double p1 = 1 / Double.valueOf(samples.size() - 1);		
		double p2 = getSumOfSquared(samples) - (Math.pow(getSum(samples), 2) / Double.valueOf(samples.size()));
		
		return p1 * p2;		
	}
	
	private double getSumOfSquared(List<SampleEntity> samples) {
		double total = 0.0;
		
		for(SampleEntity sample : samples) {			
			total += Math.pow(sample.getValue(), 2);
		}
		
		return total;
	}

	private double getSum(List<SampleEntity> samples) {
		double total = 0.0;
		
		for(SampleEntity sample : samples) {			
			total += sample.getValue();
		}
		
		return total;
	}


}
