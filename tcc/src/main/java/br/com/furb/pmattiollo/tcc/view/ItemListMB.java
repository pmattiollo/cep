package br.com.furb.pmattiollo.tcc.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.ItemBC;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@NextView("./item_edit.jsf")
@PreviousView("./item_list.jsf")
public class ItemListMB extends AbstractListPageBean<ItemEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ItemBC itemBC;
	
	@Override
	protected List<ItemEntity> handleResultList() {
		return this.itemBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				itemBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}