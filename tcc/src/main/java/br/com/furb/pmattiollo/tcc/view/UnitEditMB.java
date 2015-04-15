
package br.com.furb.pmattiollo.tcc.view;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.UnitBC;
import br.com.furb.pmattiollo.tcc.domain.UnitEntity;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./unit_list.jsf")
public class UnitEditMB extends AbstractEditPageBean<UnitEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UnitBC unitBC;
	
	@Override
	@Transactional
	public String delete() {
		this.unitBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.unitBC.insert(this.getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.unitBC.update(this.getBean());
		return getPreviousView();
	}
	
	@Override
	protected UnitEntity handleLoad(Long id) {
		return this.unitBC.load(id);
	}	
}