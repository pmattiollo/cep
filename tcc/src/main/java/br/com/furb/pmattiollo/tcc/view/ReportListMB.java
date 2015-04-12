package br.com.furb.pmattiollo.tcc.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.com.furb.pmattiollo.tcc.persistence.SoftwareDAO;

@ManagedBean
public class ReportListMB {
	
	private SoftwareEntity software;
	private List<SoftwareEntity> softwares;
	
	private ItemEntity item;
	private List<ItemEntity> items;
	
	@PostConstruct
	public void init() {
		softwares = new ArrayList<SoftwareEntity>();
		items = new ArrayList<ItemEntity>();
		
		SoftwareDAO dao = new SoftwareDAO();
		softwares.addAll(dao.findAll());
	}
	
	public void loadItens() {
		if(software != null) {
			items.addAll(software.getItems());
		}
	}
	
	public void submit() {
		if(software == null || item == null) {
			if(software == null) {
				FacesContext.getCurrentInstance().addMessage("software_report", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Software is required"));
			}
			
			if(item == null) {
				FacesContext.getCurrentInstance().addMessage("item_report", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Item is required"));
			}
		} else {
			
		}
		
	}
	
	public SoftwareEntity getSoftware() {
		return software;
	}
	
	public void setSoftware(SoftwareEntity software) {
		this.software = software;
	}
	
	public List<SoftwareEntity> getSoftwares() {
		return softwares;
	}
	
	public void setSoftwares(List<SoftwareEntity> softwares) {
		this.softwares = softwares;
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
	
	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}

}