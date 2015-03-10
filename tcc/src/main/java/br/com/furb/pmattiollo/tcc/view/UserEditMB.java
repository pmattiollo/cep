
package br.com.furb.pmattiollo.tcc.view;

import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.com.furb.pmattiollo.tcc.business.*;
import br.com.furb.pmattiollo.tcc.domain.*;

@ViewController
@PreviousView("./user_list.jsf")
public class UserEditMB extends AbstractEditPageBean<User, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserBC userBC;
	

	
	@Override
	@Transactional
	public String delete() {
		this.userBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.userBC.insert(this.getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.userBC.update(this.getBean());
		return getPreviousView();
	}
	
	@Override
	protected User handleLoad(Long id) {
		return this.userBC.load(id);
	}	
}