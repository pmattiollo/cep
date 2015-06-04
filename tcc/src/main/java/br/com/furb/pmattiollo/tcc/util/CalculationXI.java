package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;

public class CalculationXI extends CalculationAbstract {
	
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
	public BigDecimal getUclResult() {
		if(ucl == null) {
			BigDecimal sumAverage = new BigDecimal(0.0);
			BigDecimal previousAverage = new BigDecimal(0.0);
			BigDecimal currentAverage = new BigDecimal(0.0);
			BigDecimal sumMobileAverage = new BigDecimal(0.0);
			BigDecimal divisor = new BigDecimal(0.0);
			
			int count = 0;
			
			for(CollectEntity collect : collectList) {
				sumAverage = sumAverage.add(collect.getValue());
				currentAverage = collect.getValue();
				
				if(count == 0) {
					previousAverage = collect.getValue();
				} else {
					sumMobileAverage = sumMobileAverage.add(currentAverage.subtract(previousAverage).abs());				
					previousAverage = currentAverage;
				}
				
				count ++;
			}
			
			if(collectList.size() > 1) {
				divisor = new BigDecimal(collectList.size() - 1);
			} else {
				divisor = new BigDecimal(collectList.size());
			}
			
			
			sumAverage = sumAverage.divide(new BigDecimal(collectList.size()), SCALE, ROUND);
			sumMobileAverage = sumMobileAverage.divide(divisor, SCALE, ROUND);
			
			ucl = sumAverage.add(new BigDecimal(SIGMA).multiply(sumMobileAverage.divide(new BigDecimal(D2), SCALE, ROUND)));
		}
		
		return ucl;
	}

	@Override
	public BigDecimal getClResult() {
		if(cl == null) {
			BigDecimal sumAverage = new BigDecimal(0.0);
			
			for(CollectEntity collect : collectList) {
				sumAverage = sumAverage.add(collect.getValue());
			}
			
			cl = sumAverage.divide(new BigDecimal(collectList.size()), SCALE, ROUND);
		}	
		
		return cl;
	}

	@Override
	public BigDecimal getLclResult() {
		if(lcl == null) {
			BigDecimal sumAverage = new BigDecimal(0.0);
			BigDecimal previousAverage = new BigDecimal(0.0);
			BigDecimal currentAverage = new BigDecimal(0.0);
			BigDecimal sumMobileAverage = new BigDecimal(0.0);
			BigDecimal divisor = new BigDecimal(0.0);
			
			int count = 0;
			
			for(CollectEntity collect : collectList) {
				sumAverage = sumAverage.add(collect.getValue());
				currentAverage = collect.getValue();
				
				if(count == 0) {
					previousAverage = collect.getValue();
				} else {
					sumMobileAverage = sumMobileAverage.add(currentAverage.subtract(previousAverage).abs());				
					previousAverage = currentAverage;
				}
				
				count ++;
			}
			
			if(collectList.size() > 1) {
				divisor = new BigDecimal(collectList.size() - 1);
			} else {
				divisor = new BigDecimal(collectList.size());
			}
			
			sumAverage = sumAverage.divide(new BigDecimal(collectList.size()), SCALE, ROUND);
			sumMobileAverage = sumMobileAverage.divide(divisor, SCALE, ROUND);
			
			lcl = sumAverage.subtract(new BigDecimal(SIGMA).multiply(sumMobileAverage.divide(new BigDecimal(D2), SCALE, ROUND)));			
		}
		
		return lcl;
	}
	
}
