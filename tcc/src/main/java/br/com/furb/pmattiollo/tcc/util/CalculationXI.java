package br.com.furb.pmattiollo.tcc.util;

import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SampleEntity;
import br.com.furb.pmattiollo.tcc.persistence.CollectDAO;

public class CalculationXI implements Calculation {
	
	private static final double D2 = 1.128;
	private ItemEntity item;
	
	public CalculationXI(ItemEntity item) {
		this.item = item;
	}
	
	@SuppressWarnings("unused")
	private CalculationXI() {
	}
	
	@Override
	public CalculationEnum getType() {
		return CalculationEnum.XI;
	}

	@Override
	public Double getLscResult() {		
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findFinishedByItem(item);
		
		Double sumAverage = 0.0;
		
		Double previousAverage = 0.0;
		Double currentAverage = 0.0;
		Double sumMobileAverage = 0.0;
		int count = 0;
		
		for(CollectEntity collect : collectList) {
			for(SampleEntity sample : collect.getSamples()) {
				sumAverage += sample.getValue();
				currentAverage += sample.getValue();
			}
			
			// Caso haja mais de uma amostra tira a média sempre
			sumAverage = sumAverage / collect.getSamples().size();
			currentAverage = currentAverage / collect.getSamples().size();
			
			if(count == 0) {
				previousAverage = currentAverage;
			} else {
				previousAverage = (previousAverage + currentAverage) / 2;
			}
			
			sumMobileAverage += previousAverage;
			count ++;
		}
		
		Double valuesAverage = sumAverage / collectList.size();
		Double mobileRangeAverage = sumMobileAverage / collectList.size();		
		
		return valuesAverage + (3 * (mobileRangeAverage / D2));
	}

	@Override
	public Double getLcResult() {
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findFinishedByItem(item);
		
		Double sumAverage = 0.0;
		
		for(CollectEntity collect : collectList) {
			for(SampleEntity sample : collect.getSamples()) {
				sumAverage += sample.getValue();
			}
			
			// Caso haja mais de uma amostra tira a média sempre
			sumAverage = sumAverage / collect.getSamples().size();
		}
		
		return sumAverage / collectList.size();
	}

	@Override
	public Double getLicResult() {
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findFinishedByItem(item);
		
		Double sumAverage = 0.0;
		
		Double previousAverage = 0.0;
		Double currentAverage = 0.0;
		Double sumMobileAverage = 0.0;
		int count = 0;
		
		for(CollectEntity collect : collectList) {
			for(SampleEntity sample : collect.getSamples()) {
				sumAverage += sample.getValue();
				currentAverage += sample.getValue();
			}
			
			// Caso haja mais de uma amostra tira a média sempre
			sumAverage = sumAverage / collect.getSamples().size();
			currentAverage = currentAverage / collect.getSamples().size();
			
			if(count == 0) {
				previousAverage = currentAverage;
			} else {
				previousAverage = (previousAverage + currentAverage) / 2;
			}
			
			sumMobileAverage += previousAverage;
			count ++;
		}
		
		Double valuesAverage = sumAverage / collectList.size();
		Double mobileRangeAverage = sumMobileAverage / collectList.size();		
		
		return valuesAverage - (3 * (mobileRangeAverage / D2));
	}

}
