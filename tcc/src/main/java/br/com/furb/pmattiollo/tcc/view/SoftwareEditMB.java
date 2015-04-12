
package br.com.furb.pmattiollo.tcc.view;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import br.com.furb.pmattiollo.tcc.business.ItemBC;
import br.com.furb.pmattiollo.tcc.business.SoftwareBC;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./software_list.jsf")
public class SoftwareEditMB extends AbstractEditPageBean<SoftwareEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SoftwareBC softwareBC;

	private DualListModel<ItemEntity> itemEntityList;
	
	@Inject
	private ItemBC itemBC;

	public void setItemEntityList(DualListModel<ItemEntity> itemEntityList) {
		this.itemEntityList = itemEntityList;
	}
		
	public void addItemEntityList(List<ItemEntity> itemEntityList) {
		if(this.getBean().getItems() != null) {			
			this.getBean().getItems().addAll(itemEntityList);
		} else {
			this.getBean().setItems(itemEntityList);
		}
	}

	public void deleteItemEntityList(List<ItemEntity> itemEntityList) {
		if(this.getBean().getItems() != null) {			
			this.getBean().getItems().removeAll(itemEntityList);
		}
	}
	
	
	public DualListModel<ItemEntity> getItemEntityList() {
		if (this.itemEntityList == null) {
			List<ItemEntity> source = itemBC.findAll();
			List<ItemEntity> target = this.getBean().getItems();

			if (source == null) {
				source = new ArrayList<ItemEntity>();
			}
			if (target == null) {
				target = new ArrayList<ItemEntity>();
			}else{
				source.removeAll(target);
			}
			this.itemEntityList = new DualListModel<ItemEntity>(source, target);

		}
		return this.itemEntityList;
	}
	
	@SuppressWarnings("unchecked")
	public void onTransfer(TransferEvent event) {
		if (event.isAdd()){
			this.addItemEntityList((List<ItemEntity>) event.getItems());
		}
		if (event.isRemove()) {
			this.deleteItemEntityList((List<ItemEntity>) event.getItems());
		 }
	} 
	
	@Override
	@Transactional
	public String delete() {
		this.softwareBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.softwareBC.insert(this.getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.softwareBC.update(this.getBean());
		return getPreviousView();
	}
	
	@Override
	protected SoftwareEntity handleLoad(Long id) {
		return this.softwareBC.load(id);
	}	
}