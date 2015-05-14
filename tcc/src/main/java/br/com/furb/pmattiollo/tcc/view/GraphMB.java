package br.com.furb.pmattiollo.tcc.view;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;

import br.com.furb.pmattiollo.tcc.business.GraphBC;
import br.com.furb.pmattiollo.tcc.business.ItemBC;
import br.com.furb.pmattiollo.tcc.business.SoftwareBC;
import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CalculationEntity;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.com.furb.pmattiollo.tcc.persistence.CalculationDAO;
import br.com.furb.pmattiollo.tcc.persistence.CollectDAO;

@ManagedBean
public class GraphMB {
    
    @Inject
	private GraphBC graphBC;
    
    @Inject
	private SoftwareBC softwareEntityBC;
    
    @Inject
	private ItemBC itemBC;
    
    private SoftwareEntity software;
	
	private ItemEntity item;
	private List<ItemEntity> items;
	
	private LineChartModel lineModelXI;
	private LineChartModel lineModelMMEP;
	private LineChartModel lineModelDefects;	
	
	public List<SoftwareEntity> getSoftwareEntityList(){
		return softwareEntityBC.findAll();
	}
	
	public List<ItemEntity> getItemEntityList(){
		if(software!= null) {			
			return software.getItems();
		}
		
		return itemBC.findAll();
	}
    
    public void submit() {
    	if(software != null && item != null) {
			graphBC.generateCalcs(software, item);
			graphBC.classificate(item);
			generateGraphs();
		}
    }
    
    private void generateGraphs() {		
		CollectDAO collectDao = new CollectDAO();
		List<CollectEntity> collectList = collectDao.findAllBySoftwareAndItem(software, item);
		
		CalculationDAO calculationDao = new CalculationDAO();
		CalculationEntity calcXI = calculationDao.findLastBySoftwareAndItemAndType(software, item, CalculationEnum.XI);
		lineModelXI = initCategoryModel(collectList, calcXI);
        lineModelXI.setTitle("X-Bar Individial Graph");
        lineModelXI.setLegendPosition("s");
        lineModelXI.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        lineModelXI.setShowPointLabels(true);
        lineModelXI.getAxes().put(AxisType.X, new CategoryAxis("Samples for Collections"));
        lineModelXI.setMouseoverHighlight(true);
        Axis yAxisXI = lineModelXI.getAxis(AxisType.Y);
        yAxisXI.setLabel(item.getDescription());
        yAxisXI.setMin(getYAxisMin(collectList, calcXI));
        yAxisXI.setMax(getYAxisMax(collectList, calcXI));
        
        CalculationEntity calcMMEP = calculationDao.findLastBySoftwareAndItemAndType(software, item, CalculationEnum.MMEP);		
        lineModelMMEP = initCategoryModel(collectList, calcMMEP);
        lineModelMMEP.setTitle("Exponentially Weighted Moving Average Graph");
        lineModelMMEP.setLegendPosition("s");
        lineModelMMEP.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        lineModelMMEP.setShowPointLabels(true);
        lineModelMMEP.getAxes().put(AxisType.X, new CategoryAxis("Samples for Collections"));
        Axis yAxisMMEP = lineModelMMEP.getAxis(AxisType.Y);
        yAxisMMEP.setLabel(item.getDescription());
        yAxisMMEP.setMin(getYAxisMin(collectList, calcMMEP));
        yAxisMMEP.setMax(getYAxisMax(collectList, calcMMEP));
        
        CalculationEntity calcDef = calculationDao.findLastBySoftwareAndItemAndType(software, item, CalculationEnum.DEF);		
        lineModelDefects = initCategoryModel(collectList, calcDef);
        lineModelDefects.setTitle("Nonconformities Graph");
        lineModelDefects.setLegendPosition("s");
        lineModelDefects.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        lineModelDefects.setShowPointLabels(true);
        lineModelDefects.getAxes().put(AxisType.X, new CategoryAxis("Samples for Collections"));
        Axis yAxisDef = lineModelDefects.getAxis(AxisType.Y);
        yAxisDef.setLabel(item.getDescription());
        yAxisDef.setMin(getYAxisMin(collectList, calcDef));
        yAxisDef.setMax(getYAxisMax(collectList, calcDef));

	}
    
    private BigDecimal getYAxisMax(List<CollectEntity> collectList, CalculationEntity calc) {
    	BigDecimal maxValue = new BigDecimal(Integer.MIN_VALUE);
    	
        for(CollectEntity collect : collectList) {			
        	maxValue = maxValue.max(collect.getValue()).max(calc.getUcl());
		}
        
        return maxValue.add(new BigDecimal(1));
    }
    
    private BigDecimal getYAxisMin(List<CollectEntity> collectList, CalculationEntity calc) {
    	BigDecimal minValue = new BigDecimal(Integer.MAX_VALUE);
    	
        for(CollectEntity collect : collectList) {			
        	minValue = minValue.min(collect.getValue()).min(calc.getLcl());
		}
        
        return minValue.subtract(new BigDecimal(1));
    }
     
    private LineChartModel initCategoryModel(List<CollectEntity> collectList, CalculationEntity calc) {
        LineChartModel model = new LineChartModel();
 
        ChartSeries collectsItem = new ChartSeries();
        ChartSeries ucl = new ChartSeries();
        ChartSeries cl = new ChartSeries();        
        ChartSeries lcl = new ChartSeries();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        
        collectsItem.setLabel("Collects for item \"" + item.getDescription() + "\"");
        ucl.setLabel("UCL (Upper Control Limit)");
        cl.setLabel("CL (Control Limit)");
        lcl.setLabel("LCL (Lower Control Limit)");
        
        int collectCount = 1;
        
        for(CollectEntity collect : collectList) {			
        	String desc = "C" + collectCount + sdf.format(collect.getStart_date());
        	
        	collectsItem.set(desc, collect.getValue());
			ucl.set(desc, calc.getUcl());
			cl.set(desc, calc.getCl());
			lcl.set(desc, calc.getLcl());
			
			collectCount ++;
		}
 
        model.addSeries(collectsItem);
        model.addSeries(ucl);
        model.addSeries(cl);
        model.addSeries(lcl);
         
        return model;
    }
    
    public SoftwareEntity getSoftware() {
		return software;
	}
    
    public void setSoftware(SoftwareEntity software) {
		this.software = software;
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
