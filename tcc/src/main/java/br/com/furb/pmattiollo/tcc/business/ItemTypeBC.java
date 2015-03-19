
package br.com.furb.pmattiollo.tcc.business;

import br.com.furb.pmattiollo.tcc.domain.ItemTypeEntity;
import br.com.furb.pmattiollo.tcc.persistence.ItemTypeDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ItemTypeBC extends DelegateCrud<ItemTypeEntity, Long, ItemTypeDAO> {
	private static final long serialVersionUID = 1L;
	
	
}
