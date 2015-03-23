
package br.com.furb.pmattiollo.tcc.view;

import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.UserBC;
import br.com.furb.pmattiollo.tcc.domain.UserEntity;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./user_list.jsf")
public class UserEditMB extends AbstractEditPageBean<UserEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserBC userBC;
	

	public List<SelectItem> getPaper() {
		return userBC.getUserEnum();
	}
	
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
	protected UserEntity handleLoad(Long id) {
		return this.userBC.load(id);
	}	
}