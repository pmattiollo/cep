
package br.com.furb.pmattiollo.tcc.view;

import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.ItemTypeBC;
import br.com.furb.pmattiollo.tcc.domain.ItemTypeEntity;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./itemType_list.jsf")
public class ItemTypeEditMB extends AbstractEditPageBean<ItemTypeEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ItemTypeBC itemTypeBC;	

	public List<SelectItem> getType() {
		return itemTypeBC.getItemTypeEnum();
	}
	
	@Override
	@Transactional
	public String delete() {
		this.itemTypeBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.itemTypeBC.insert(this.getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.itemTypeBC.update(this.getBean());
		return getPreviousView();
	}
	
	@Override
	protected ItemTypeEntity handleLoad(Long id) {
		return this.itemTypeBC.load(id);
	}	
}