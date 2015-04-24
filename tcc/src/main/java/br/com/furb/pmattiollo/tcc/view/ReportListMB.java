package br.com.furb.pmattiollo.tcc.view;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;

import br.com.furb.pmattiollo.tcc.business.ReportBC;
import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CalculationEntity;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SampleEntity;
import br.com.furb.pmattiollo.tcc.persistence.CalculationDAO;
import br.com.furb.pmattiollo.tcc.persistence.CollectDAO;
import br.com.furb.pmattiollo.tcc.persistence.ItemDAO;

@ManagedBean
public class ReportListMB {	
    
    @Inject
	private ReportBC reportBC;
	
	private ItemEntity item;
	private List<ItemEntity> items;
	
	private LineChartModel lineModelXI;
	private LineChartModel lineModelMMEP;
	private LineChartModel lineModelDefects;
	
	@PostConstruct
	public void init() {
		ItemDAO dao = new ItemDAO();
		items = dao.findAll();
	}
    
    public void submit() {
    	if(item != null) {
			reportBC.generateCalcs(item);			
			generateGraphs();
		}
    }
    
    private void generateGraphs() {		
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findFinishedByItem(item);
		
		CalculationDAO calculationDao = new CalculationDAO();
		CalculationEntity calcXI = calculationDao.findLastByItemAndType(item, CalculationEnum.XI);		
		lineModelXI = initCategoryModel(collectList, calcXI);
        lineModelXI.setTitle("X-Bar Individial Graph");
        lineModelXI.setLegendPosition("s");
        lineModelXI.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        lineModelXI.setShowPointLabels(true);
        lineModelXI.getAxes().put(AxisType.X, new CategoryAxis("Samples for Collections"));
        lineModelXI.setMouseoverHighlight(true);
        Axis yAxisXI = lineModelXI.getAxis(AxisType.Y);
        yAxisXI.setLabel("Values");
        yAxisXI.setMin(getYAxisMin(collectList, calcXI));
        yAxisXI.setMax(getYAxisMax(collectList, calcXI));
        
        CalculationEntity calcMMEP = calculationDao.findLastByItemAndType(item, CalculationEnum.MMEP);		
        lineModelMMEP = initCategoryModel(collectList, calcMMEP);
        lineModelMMEP.setTitle("Exponentially Weighted Moving Average Graph");
        lineModelMMEP.setLegendPosition("s");
        lineModelMMEP.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        lineModelMMEP.setShowPointLabels(true);
        lineModelMMEP.getAxes().put(AxisType.X, new CategoryAxis("Samples for Collections"));
        Axis yAxisMMEP = lineModelMMEP.getAxis(AxisType.Y);
        yAxisMMEP.setLabel("Values");
        yAxisMMEP.setMin(getYAxisMin(collectList, calcMMEP));
        yAxisMMEP.setMax(getYAxisMax(collectList, calcMMEP));
        
        CalculationEntity calcDef = calculationDao.findLastByItemAndType(item, CalculationEnum.DEF);		
        lineModelDefects = initCategoryModel(collectList, calcDef);
        lineModelDefects.setTitle("Nonconformities Graph");
        lineModelDefects.setLegendPosition("s");
        lineModelDefects.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        lineModelDefects.setShowPointLabels(true);
        lineModelDefects.getAxes().put(AxisType.X, new CategoryAxis("Samples for Collections"));
        Axis yAxisDef = lineModelDefects.getAxis(AxisType.Y);
        yAxisDef.setLabel("Values");
        yAxisDef.setMin(getYAxisMin(collectList, calcDef));
        yAxisDef.setMax(getYAxisMax(collectList, calcDef));

	}
    
    private BigDecimal getYAxisMax(List<CollectEntity> collectList, CalculationEntity calc) {
    	BigDecimal maxValue = new BigDecimal(Integer.MIN_VALUE);
    	
        for(CollectEntity collect : collectList) {			
			for(SampleEntity sample : collect.getSamples()) {
				maxValue = maxValue.max(sample.getValue()).max(calc.getLsc());
			}
		}
        
        return maxValue.add(new BigDecimal(1));
    }
    
    private BigDecimal getYAxisMin(List<CollectEntity> collectList, CalculationEntity calc) {
    	BigDecimal minValue = new BigDecimal(Integer.MAX_VALUE);
    	
        for(CollectEntity collect : collectList) {			
			for(SampleEntity sample : collect.getSamples()) {
				minValue = minValue.min(sample.getValue()).min(calc.getLic());
			}
		}
        
        return minValue.subtract(new BigDecimal(1));
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
     
    private LineChartModel initCategoryModel(List<CollectEntity> collectList, CalculationEntity calc) {
        LineChartModel model = new LineChartModel();
 
        ChartSeries collectsItem = new ChartSeries();
        ChartSeries ucl = new ChartSeries();
        ChartSeries cl = new ChartSeries();        
        ChartSeries lcl = new ChartSeries();
        
        collectsItem.setLabel("Collects for item \"" + item.getDescription() + "\"");
        ucl.setLabel("UCL (Upper Control Limit)");
        cl.setLabel("CL (Control Limit)");
        lcl.setLabel("LCL (Lower Control Limit)");
        
        int collectCount = 1;
        
        for(CollectEntity collect : collectList) {			
        	String desc = "C" + collectCount;
			List<SampleEntity> samples = collect.getSamples();
			Collections.sort(samples, new SampleComparator());
			
			int sampleCount = 1;
			
			for(SampleEntity sample : samples) {
				collectsItem.set(desc + " - S" + sampleCount, sample.getValue());
				ucl.set(desc + " - S" + sampleCount, calc.getLsc());
				cl.set(desc + " - S" + sampleCount, calc.getLc());
				lcl.set(desc + " - S" + sampleCount, calc.getLic());
				
				sampleCount ++;
			}
			
			collectCount ++;
		}
 
        model.addSeries(collectsItem);
        model.addSeries(ucl);
        model.addSeries(cl);
        model.addSeries(lcl);
         
        return model;
    }
    
    public ItemEntity getItem() {
		return item;
	}
    
    public void setItem(ItemEntity item) {
		this.item = item;
	}
    
    public List<ItemEntity> getItems() {
		return items;
	}
    
    public LineChartModel getLineModelXI() {
		return lineModelXI;
	}
    
    public LineChartModel getLineModelMMEP() {
		return lineModelMMEP;
	}
    
    public LineChartModel getLineModelDefects() {
		return lineModelDefects;
	}

}
