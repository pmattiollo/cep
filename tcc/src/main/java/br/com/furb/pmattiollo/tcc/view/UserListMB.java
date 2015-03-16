package br.com.furb.pmattiollo.tcc.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.UserBC;
import br.com.furb.pmattiollo.tcc.domain.UserEntity;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@NextView("./user_edit.jsf")
@PreviousView("./user_list.jsf")
public class UserListMB extends AbstractListPageBean<UserEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserBC userBC;
	
	@Override
	protected List<UserEntity> handleResultList() {
		return this.userBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				userBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}