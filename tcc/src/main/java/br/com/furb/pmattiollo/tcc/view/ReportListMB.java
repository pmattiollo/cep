package br.com.furb.pmattiollo.tcc.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.ReportBC;
import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.persistence.ItemDAO;

@RequestScoped
@ManagedBean
public class ReportListMB {
	
	@Inject
	private ReportBC reportBC;
	
	private ItemEntity item;
	private List<ItemEntity> items;
	
	@PostConstruct
	public void init() {
		ItemDAO dao = new ItemDAO();
		items = dao.findAll();
	}
	
	public void calc() {
		if(item != null) {
			reportBC.generateCalcs(item);
		}
	}
	
	public String submit(Integer code) {
		if(item != null) {			
			return reportBC.generateGraph(CalculationEnum.getCalculationTypeByCode(code), item);		
		}
		
		return null;
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