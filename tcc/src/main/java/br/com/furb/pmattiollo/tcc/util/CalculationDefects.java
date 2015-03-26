package br.com.furb.pmattiollo.tcc.util;

import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SampleEntity;
import br.com.furb.pmattiollo.tcc.persistence.CollectDAO;

public class CalculationDefects implements Calculation {
	
	private ItemEntity item;
	
	public CalculationDefects(ItemEntity item) {
		this.item = item;
	}
	
	@SuppressWarnings("unused")
	private CalculationDefects() {
	}
	
	@Override
	public CalculationEnum getType() {
		return CalculationEnum.DEF;
	}

	@Override
	public Double getLscResult() {
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findLastByItem(item);
		
		int collectNumber = collectList.size();
		int sampleSize = item.getItemType().getType().getNum();
		Double sumDefects = 0.0;
		Double average = 0.0;
		
		for(CollectEntity collect : collectList) {
			for(SampleEntity sample : collect.getSamples()) {
				sumDefects += sample.getValue();
			}
		}
		
		average = (sumDefects / (collectNumber * sampleSize));
		
		return average + (3 * Math.sqrt(average / sampleSize));
	}

	@Override
	public Double getLcResult() {
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findLastByItem(item);
		
		int collectNumber = collectList.size();
		int sampleSize = item.getItemType().getType().getNum();
		Double sumDefects = 0.0;
		
		for(CollectEntity collect : collectList) {
			for(SampleEntity sample : collect.getSamples()) {
				sumDefects += sample.getValue();
			}
		}
		
		return (sumDefects / (collectNumber * sampleSize));		
	}

	@Override
	public Double getLicResult() {
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findLastByItem(item);
		
		int collectNumber = collectList.size();
		int sampleSize = item.getItemType().getType().getNum();
		Double sumDefects = 0.0;
		Double average = 0.0;
		
		for(CollectEntity collect : collectList) {
			for(SampleEntity sample : collect.getSamples()) {
				sumDefects += sample.getValue();
			}
		}
		
		average = (sumDefects / (collectNumber * sampleSize));
		
		return average - (3 * Math.sqrt(average / sampleSize));
	}

}
