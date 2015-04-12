package br.com.furb.pmattiollo.tcc.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.SoftwareBC;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@NextView("./software_edit.jsf")
@PreviousView("./software_list.jsf")
public class SoftwareListMB extends AbstractListPageBean<SoftwareEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SoftwareBC softwareBC;
	
	@Override
	protected List<SoftwareEntity> handleResultList() {
		return this.softwareBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				softwareBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}