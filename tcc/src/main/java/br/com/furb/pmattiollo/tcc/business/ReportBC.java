
package br.com.furb.pmattiollo.tcc.business;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CalculationEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.util.Calculation;
import br.com.furb.pmattiollo.tcc.util.CalculationFactory;
import br.com.furb.pmattiollo.tcc.util.ChartDefinition;
import br.com.furb.pmattiollo.tcc.util.ChartType;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;

@BusinessController
public class ReportBC {
	
	@Inject
	private CalculationBC calculationBC;
	
	public String generateGraph(CalculationEnum type, ItemEntity item) {
		Calculation calc = CalculationFactory.getCalculation(type, item);
		insertCalculationEntity(calc, item);
		ChartDefinition chart = new ChartDefinition();
		
		switch(type) {
		case DEF:
//			chart.setTitle("NONCONFORMITIES");
//			chart.setCategories(categories);
//			chart.setNamesSeries(namesSeries);
//			chart.setValuesSeries(valuesSeries);
//			chart.setTypeEnum(ChartType.LINE);
//			chart.setType(type);
//			chart.setStacked(ChartType.LINE.isStacked());
//			chart.setShowToolTip(true);
			
			break;
		case MMEP:
			chart.setTitle("EWMA");
			break;
		case XI:
			chart.setTitle("XI");
			break;
		}
		
		return chart.toJson();
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
