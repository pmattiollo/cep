
package br.com.furb.pmattiollo.tcc.business;

import br.com.furb.pmattiollo.tcc.domain.Item;
import br.com.furb.pmattiollo.tcc.persistence.ItemDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ItemBC extends DelegateCrud<Item, Long, ItemDAO> {
	private static final long serialVersionUID = 1L;
	
	
}
