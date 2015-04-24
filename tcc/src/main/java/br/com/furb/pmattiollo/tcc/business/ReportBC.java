
package br.com.furb.pmattiollo.tcc.business;

import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CalculationEntity;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.persistence.CollectDAO;
import br.com.furb.pmattiollo.tcc.util.Calculation;
import br.com.furb.pmattiollo.tcc.util.CalculationFactory;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;

@BusinessController
public class ReportBC {
	
	@Inject
	private CalculationBC calculationBC;
	
	public void generateCalcs(ItemEntity item) {
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findFinishedByItem(item);
		
		Calculation calcXI = CalculationFactory.getCalculation(CalculationEnum.XI, collectList);
		insertCalculationEntity(calcXI, item);
		
		Calculation calcMMEP = CalculationFactory.getCalculation(CalculationEnum.MMEP, collectList);
		insertCalculationEntity(calcMMEP, item);
		
		Calculation calcDEF = CalculationFactory.getCalculation(CalculationEnum.DEF, collectList);
		insertCalculationEntity(calcDEF, item);
	}
	
	private void insertCalculationEntity(Calculation calc, ItemEntity item) {
		CalculationEntity calcEntity = new CalculationEntity();
		calcEntity.setLc(calc.getLcResult());
		calcEntity.setLic(calc.getLicResult());
		calcEntity.setLsc(calc.getLscResult());
		calcEntity.setType(calc.getType());
		calcEntity.setItem(item);
		
		calculationBC.insert(calcEntity);
	}
	
}
