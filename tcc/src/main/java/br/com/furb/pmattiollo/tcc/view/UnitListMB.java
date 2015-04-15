package br.com.furb.pmattiollo.tcc.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.UnitBC;
import br.com.furb.pmattiollo.tcc.domain.UnitEntity;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@NextView("./unit_edit.jsf")
@PreviousView("./unit_list.jsf")
public class UnitListMB extends AbstractListPageBean<UnitEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UnitBC unitBC;
	
	@Override
	protected List<UnitEntity> handleResultList() {
		return this.unitBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				unitBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}