
package br.com.furb.pmattiollo.tcc.business;

import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.persistence.CollectDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class CollectBC extends DelegateCrud<CollectEntity, Long, CollectDAO> {
	private static final long serialVersionUID = 1L;
	
	
}
