package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;

public class CalculationXI implements Calculation {
	
	private static final double D2 = 1.128;
	private static final int SIGMA = 3;
	
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
	public BigDecimal getUclResult() {		
		BigDecimal sumAverage = new BigDecimal(0.0);
		BigDecimal sumMobileAverage = new BigDecimal(0.0);
		
		for(CollectEntity collect : collectList) {
			sumAverage = sumAverage.add(collect.getValue());			
			sumMobileAverage = sumMobileAverage.add(sumAverage);
		}
		
		sumAverage = sumAverage.divide(new BigDecimal(collectList.size()), SCALE, ROUND);
		sumMobileAverage = sumMobileAverage.divide(new BigDecimal(collectList.size()), SCALE, ROUND);
		
		return sumAverage.add(sumMobileAverage.divide(new BigDecimal(D2), SCALE, ROUND).multiply(new BigDecimal(SIGMA)));
	}

	@Override
	public BigDecimal getClResult() {		
		BigDecimal sumAverage = new BigDecimal(0.0);
		
		for(CollectEntity collect : collectList) {
			sumAverage = sumAverage.add(collect.getValue());
		}
		
		return sumAverage.divide(new BigDecimal(collectList.size()), SCALE, ROUND);
	}

	@Override
	public BigDecimal getLclResult() {		
		BigDecimal sumAverage = new BigDecimal(0.0);
		BigDecimal sumMobileAverage = new BigDecimal(0.0);
		
		for(CollectEntity collect : collectList) {
			sumAverage = sumAverage.add(collect.getValue());
			sumMobileAverage = sumMobileAverage.add(sumAverage);
		}
		
		sumAverage = sumAverage.divide(new BigDecimal(collectList.size()), SCALE, ROUND);
		sumMobileAverage = sumMobileAverage.divide(new BigDecimal(collectList.size()), SCALE, ROUND);	
		
		return sumAverage.subtract(sumMobileAverage.divide(new BigDecimal(D2), SCALE, ROUND).multiply(new BigDecimal(SIGMA)));
	}
	
}
