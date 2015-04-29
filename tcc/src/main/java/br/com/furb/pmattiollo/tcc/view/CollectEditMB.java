
package br.com.furb.pmattiollo.tcc.view;

import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.CollectBC;
import br.com.furb.pmattiollo.tcc.business.ItemBC;
import br.com.furb.pmattiollo.tcc.business.SoftwareBC;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./collect_list.jsf")
public class CollectEditMB extends AbstractEditPageBean<CollectEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private CollectBC collectBC;

	@Inject
	private SoftwareBC softwareEntityBC;
	
	@Inject
	private ItemBC itemEntityBC;
	
	public List<SoftwareEntity> getSoftwareEntityList(){
		return softwareEntityBC.findAll();
	}
	
	public List<ItemEntity> getItemEntityList(){
		if(this.getBean().getSoftware() != null) {
			return this.getBean().getSoftware().getItems();
		}
		
		return itemEntityBC.findAll();
	}
	
	@Override
	@Transactional
	public String delete() {
		this.collectBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.collectBC.insert(this.getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.collectBC.update(this.getBean());
		return getPreviousView();
	}
	
	@Override
	protected CollectEntity handleLoad(Long id) {
		return this.collectBC.load(id);
	}	
}