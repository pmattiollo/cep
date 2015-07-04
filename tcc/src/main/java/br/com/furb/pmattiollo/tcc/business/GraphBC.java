
package br.com.furb.pmattiollo.tcc.business;

import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CalculationEntity;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.com.furb.pmattiollo.tcc.persistence.CollectDAO;
import br.com.furb.pmattiollo.tcc.util.Calculation;
import br.com.furb.pmattiollo.tcc.util.CalculationFactory;
import br.com.furb.pmattiollo.tcc.util.Classification;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;

@BusinessController
public class GraphBC {
	
	@Inject
	private CalculationBC calculationBC;
	
	@Inject
	private ItemBC itemBC;
	
	public void generateCalcsAndCalssificate(SoftwareEntity software, ItemEntity item) {
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findAllBySoftwareAndItem(software, item);
		
		Calculation calcXI = CalculationFactory.getCalculation(CalculationEnum.XI, collectList, item);
		insertCalculationEntity(calcXI, software, item);
		
		Calculation calcMMEP = CalculationFactory.getCalculation(CalculationEnum.MMEP, collectList, item);
		insertCalculationEntity(calcMMEP, software, item);
		
		Calculation calcDEF = CalculationFactory.getCalculation(CalculationEnum.DEF, collectList, item);
		insertCalculationEntity(calcDEF, software, item);
		
		Classification classification = new Classification(item, collectList, calcXI, calcMMEP, calcDEF);
		item.setStable(classification.isStable());
		item.setAble(classification.isAble(item.isStable()));		
		itemBC.update(item);
	}
	
	private void insertCalculationEntity(Calculation calc, SoftwareEntity software, ItemEntity item) {
		CalculationEntity calcEntity = new CalculationEntity();
		calcEntity.setCl(calc.getClResult());
		calcEntity.setLcl(calc.getLclResult());
		calcEntity.setUcl(calc.getUclResult());
		calcEntity.setType(calc.getType());
		calcEntity.setSoftware(software);
		calcEntity.setItem(item);
		
		calculationBC.insert(calcEntity);
	}
	
}
