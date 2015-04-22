
package br.com.furb.pmattiollo.tcc.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CalculationEntity;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SampleEntity;
import br.com.furb.pmattiollo.tcc.persistence.CalculationDAO;
import br.com.furb.pmattiollo.tcc.persistence.CollectDAO;
import br.com.furb.pmattiollo.tcc.util.Calculation;
import br.com.furb.pmattiollo.tcc.util.CalculationFactory;
import br.com.furb.pmattiollo.tcc.util.ChartDefinition;
import br.com.furb.pmattiollo.tcc.util.ChartType;
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
	
	public String generateGraph(CalculationEnum type, ItemEntity item) {		
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findFinishedByItem(item);
		
		CalculationDAO calculationDao = new CalculationDAO();
		CalculationEntity calc = calculationDao.findLastByItem(item);
		
		ChartDefinition chart = new ChartDefinition();
		chart.setCategories(getCategories(collectList));
		chart.setNamesSeries(getNamesSeries(collectList, item));
		chart.setValuesSeries(getValuesSeries(collectList, calc));
		chart.setTypeEnum(ChartType.LINE);
		chart.setType(ChartType.LINE.getType());
		chart.setShowToolTip(true);
		chart.setStacked(ChartType.LINE.isStacked());
		chart.setxAxisTitle("Collects/Samples List");
		chart.setyAxisTitle("Collected Values");
		
		switch(type) {
		case DEF:
			chart.setTitle("NONCONFORMITIES");
			chart.setSubtitle("Nonconformities Graph");
			break;
		case MMEP:
			chart.setTitle("EWMA");
			chart.setSubtitle("Exponentially Weighted Moving Average Graph");
			break;
		case XI:
			chart.setTitle("XI");
			chart.setSubtitle("X-Bar Individial Graph");
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
	
	private List<String> getCategories(List<CollectEntity> collectList) {
		List<String> categories = new ArrayList<String>();
		int collectCount = 1;
		
		for(CollectEntity collect : collectList) {
			String desc = "C" + collectCount;
			
			for(int i=1;i<=collect.getSamples().size();i++) {
				categories.add(desc + " - S" + i);
			}
			
			collectCount ++;
		}
		
		return categories;
	}
	
	private List<String> getNamesSeries(List<CollectEntity> collectList, ItemEntity item) {
		List<String> namesSeries = new ArrayList<String>();
		namesSeries.add("Collects for item \"" + item.getDescription() + "\"");		
		namesSeries.add("UCL (Upper Control Limit)");
		namesSeries.add("CL (Control Limit)");
		namesSeries.add("LCL (Lower Control Limit)");
		
		return namesSeries;
	}
	
	private List<List<BigDecimal>> getValuesSeries(List<CollectEntity> collectList, CalculationEntity calc) {
		List<List<BigDecimal>> valuesSeries = new ArrayList<List<BigDecimal>>();
		
		List<BigDecimal> valuesSamples = new ArrayList<BigDecimal>();
		List<BigDecimal> valuesUCL = new ArrayList<BigDecimal>();
		List<BigDecimal> valuesCL = new ArrayList<BigDecimal>();
		List<BigDecimal> valuesLCL = new ArrayList<BigDecimal>();
		
		for(CollectEntity collect : collectList) {			
			List<SampleEntity> samples = collect.getSamples();
			Collections.sort(samples, new SampleComparator());
			
			for(SampleEntity sample : samples) {
				valuesSamples.add(sample.getValue());
				
				valuesUCL.add(calc.getLsc());
				valuesCL.add(calc.getLc());
				valuesLCL.add(calc.getLic());
			}
		}
		
		valuesSeries.add(valuesSamples);
		valuesSeries.add(valuesUCL);
		valuesSeries.add(valuesCL);
		valuesSeries.add(valuesLCL);
		
		return valuesSeries;
	}
	
	private class SampleComparator implements Comparator<SampleEntity> {

		@Override
		public int compare(SampleEntity o1, SampleEntity o2) {
			if(o1.getId() < o2.getId()) {
				return -1;
			}
			
			if(o1.getId() > o2.getId()) {
				return 1;
			}
			
			return 0;
		}
		
	}
	
}
