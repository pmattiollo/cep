
package br.com.furb.pmattiollo.tcc.business;

import br.com.furb.pmattiollo.tcc.domain.UnitEntity;
import br.com.furb.pmattiollo.tcc.persistence.UnitDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class UnitBC extends DelegateCrud<UnitEntity, Long, UnitDAO> {
	private static final long serialVersionUID = 1L;
	
	
}
