package br.com.furb.pmattiollo.tcc.util;

import java.math.BigDecimal;
import java.util.List;

import com.google.gson.GsonBuilder;

public class ChartDefinition {
	
	private List<String> categories;
	private List<String> namesSeries;
	private List<List<BigDecimal>> valuesSeries;
	
	private String title;
	private ChartType typeEnum;
	private String type;
	
	private String yAxisTitle;
	private String xAxisTitle;
	private List<String> yAxisLabels;

	private boolean showValueSeries;
	private boolean showToolTip;
	private boolean stacked;

	public ChartDefinition() {
	}
			
//	public ChartDefinition(List<String> categories, List<String> namesSeries, List<List<BigDecimal>> valuesSeries, ChartType typeEnum, String title) {
//		this.categories = categories;
//		this.namesSeries = namesSeries;
//		this.valuesSeries = valuesSeries;
//		this.typeEnum = typeEnum;
//		this.type = typeEnum.getType();
//		this.stacked = typeEnum.isStacked();
//		this.title = title;
//		this.showToolTip = true;
//	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getyAxisTitle() {
		return yAxisTitle;
	}
	
	public void setyAxisTitle(String yAxisTitle) {
		this.yAxisTitle = yAxisTitle;
	}
	
	public String getxAxisTitle() {
		return xAxisTitle;
	}
	
	public void setxAxisTitle(String xAxisTitle) {
		this.xAxisTitle = xAxisTitle;
	}
	
	public List<String> getCategories() {
		return categories;
	}
	
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getNamesSeries() {
		return namesSeries;
	}
	
	public void setNamesSeries(List<String> namesSeries) {
		this.namesSeries = namesSeries;
	}
	
	public List<List<BigDecimal>> getValuesSeries() {
		return valuesSeries;
	}
	
	public void setValuesSeries(List<List<BigDecimal>> valuesSeries) {
		this.valuesSeries = valuesSeries;
	}

	public ChartType getTypeEnum() {
		return typeEnum;
	}
	
	public void setTypeEnum(ChartType typeEnum) {
		this.typeEnum = typeEnum;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public List<String> getyAxisLabels() {
		return yAxisLabels;
	}

	public void setyAxisLabels(List<String> yAxisLabels) {
		this.yAxisLabels = yAxisLabels;
	}
	
	public boolean isShowValueSeries() {
		return showValueSeries;
	}

	public void setShowValueSeries(boolean showValueSeries) {
		this.showValueSeries = showValueSeries;
	}

	public boolean isShowToolTip() {
		return showToolTip;
	}

	public void setShowToolTip(boolean showToolTip) {
		this.showToolTip = showToolTip;
	}

	public boolean isStacked() {
		return stacked;
	}
	
	public void setStacked(boolean stacked) {
		this.stacked = stacked;
	}

	public String toJson() {
		GsonBuilder gb = new GsonBuilder(); 
		return gb.create().toJson(this);
	}
}