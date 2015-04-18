package br.com.furb.pmattiollo.tcc.util;

import java.util.List;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;

public class CalculationFactory {
	
	public static Calculation getCalculation(CalculationEnum type, List<CollectEntity> collects) {
		switch(type) {
		case XI:
			return new CalculationXI(collects);
		case MMEP:
			return new CalculationMMEP(collects);
		case DEF:
			return new CalculationDefects(collects);
		default:
			return null;
		}
	}

}
