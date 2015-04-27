package br.com.furb.pmattiollo.tcc.util;

import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;

public class CalculationFactory {
	
	public static Calculation getCalculation(CalculationEnum type, List<CollectEntity> collects, ItemEntity item) {
		switch(type) {
		case XI:
			return new CalculationXI(collects);
		case MMEP:
			return new CalculationMMEP(collects, item);
		case DEF:
			return new CalculationDefects(collects);
		default:
			return null;
		}
	}

}
