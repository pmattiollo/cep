package br.com.furb.pmattiollo.tcc.integration.sonar.beans;

import java.math.BigDecimal;
import java.util.Date;

public class SonarCollect {

	private int id;
	private String softwareDesc;
	private String itemDesc;
	private BigDecimal value;
	private Date startDate;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSoftwareDesc() {
		return softwareDesc;
	}
	
	public void setSoftwareDesc(String softwareDesc) {
		this.softwareDesc = softwareDesc;
	}
	
	public String getItemDesc() {
		return itemDesc;
	}
	
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
