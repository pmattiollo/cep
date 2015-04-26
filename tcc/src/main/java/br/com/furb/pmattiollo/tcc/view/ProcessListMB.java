package br.com.furb.pmattiollo.tcc.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.ProcessBC;
import br.com.furb.pmattiollo.tcc.domain.ProcessEntity;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@NextView("./process_edit.jsf")
@PreviousView("./process_list.jsf")
public class ProcessListMB extends AbstractListPageBean<ProcessEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProcessBC processBC;
	
	@Override
	protected List<ProcessEntity> handleResultList() {
		return this.processBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				processBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}