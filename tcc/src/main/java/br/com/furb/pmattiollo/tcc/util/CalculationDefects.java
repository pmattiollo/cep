package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;

public class CalculationDefects extends CalculationAbstract {
	
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
		if(ucl == null) {
			BigDecimal sumDefects = new BigDecimal(0.0);
			BigDecimal average = new BigDecimal(0.0);
			
			for(CollectEntity collect : collectList) {
				sumDefects = sumDefects.add(collect.getValue());
			}
			
			average = sumDefects.divide(new BigDecimal(collectList.size()), SCALE, ROUND);		
			BigDecimal sqrtValue = new BigDecimal(Math.sqrt(average.doubleValue()));		
			
			ucl = average.add(new BigDecimal(SIGMA).multiply(sqrtValue));
		}
		
		return ucl;
	}

	@Override
	public BigDecimal getClResult() {
		if(cl == null) {
			BigDecimal sumDefects = new BigDecimal(0.0);
			
			for(CollectEntity collect : collectList) {
				sumDefects = sumDefects.add(collect.getValue());
			}
			
			cl = sumDefects.divide(new BigDecimal(collectList.size()), SCALE, ROUND);
		}
		
		return cl;
	}

	@Override
	public BigDecimal getLclResult() {
		if(lcl == null) {
			BigDecimal sumDefects = new BigDecimal(0.0);
			BigDecimal average = new BigDecimal(0.0);
			
			for(CollectEntity collect : collectList) {
				sumDefects = sumDefects.add(collect.getValue());
			}
			
			average = sumDefects.divide(new BigDecimal(collectList.size()), SCALE, ROUND);		
			BigDecimal sqrtValue = new BigDecimal(Math.sqrt(average.doubleValue()));		
			
			lcl = average.subtract(new BigDecimal(SIGMA).multiply(sqrtValue));
		}
		
		return lcl;		
	}

}
