package br.com.furb.pmattiollo.tcc.util;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;

public class CalculationFactory {
	
	public static Calculation getCalculation(CalculationEnum type, ItemEntity item) {
		switch(type) {
		case XI:
			return new CalculationXI(item);
		case MMEP:
			return new CalculationMMEP(item);
		case DEF:
			return new CalculationDefects(item);
		default:
			return null;
		}
	}

}
