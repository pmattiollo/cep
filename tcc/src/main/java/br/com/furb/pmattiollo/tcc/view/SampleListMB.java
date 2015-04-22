package br.com.furb.pmattiollo.tcc.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.SampleBC;
import br.com.furb.pmattiollo.tcc.domain.SampleEntity;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@NextView("./collect_edit.jsf")
@PreviousView("./collect_list.jsf")
public class SampleListMB extends AbstractListPageBean<SampleEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SampleBC sampleBC;
	
	@Override
	protected List<SampleEntity> handleResultList() {
		return this.sampleBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				sampleBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}