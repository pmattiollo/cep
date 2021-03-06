package br.com.furb.pmattiollo.tcc.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.CollectBC;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@NextView("./collect_edit.jsf")
@PreviousView("./collect_list.jsf")
public class CollectListMB extends AbstractListPageBean<CollectEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private CollectBC collectBC;
	
	@Override
	protected List<CollectEntity> handleResultList() {
		return this.collectBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				collectBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}