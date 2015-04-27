
package br.com.furb.pmattiollo.tcc.view;

import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.ItemBC;
import br.com.furb.pmattiollo.tcc.business.ProcessBC;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.ProcessEntity;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./item_list.jsf")
public class ItemEditMB extends AbstractEditPageBean<ItemEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ItemBC itemBC;	

	@Inject
	private ProcessBC processEntityBC;
	
	public List<ProcessEntity> getProcessEntityList(){
		return processEntityBC.findAll();
	}			
	
	@Override
	@Transactional
	public String delete() {
		this.itemBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.itemBC.insert(this.getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.itemBC.update(this.getBean());
		return getPreviousView();
	}
	
	@Override
	protected ItemEntity handleLoad(Long id) {
		return this.itemBC.load(id);
	}	
}