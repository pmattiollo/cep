
package br.com.furb.pmattiollo.tcc.view;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.ProcessBC;
import br.com.furb.pmattiollo.tcc.domain.ProcessEntity;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./process_list.jsf")
public class ProcessEditMB extends AbstractEditPageBean<ProcessEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProcessBC processBC;
	

	
	@Override
	@Transactional
	public String delete() {
		this.processBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.processBC.insert(this.getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.processBC.update(this.getBean());
		return getPreviousView();
	}
	
	@Override
	protected ProcessEntity handleLoad(Long id) {
		return this.processBC.load(id);
	}	
}