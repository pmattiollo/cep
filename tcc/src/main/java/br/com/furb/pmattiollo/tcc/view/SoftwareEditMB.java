
package br.com.furb.pmattiollo.tcc.view;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

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

	private DataModel<ItemEntity> itemList;
	
	public void addItem() {
		this.getBean().getItems().add(new ItemEntity());
	}
	public void deleteItem() {
	   this.getBean().getItems().remove(getItemList().getRowData());
	}
	public DataModel<ItemEntity> getItemList() {
	   if (itemList == null) {
		   itemList = new ListDataModel<ItemEntity>(this.getBean().getItems());
	   }
	   return itemList;
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