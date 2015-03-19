
package br.com.furb.pmattiollo.tcc.business;

import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.com.furb.pmattiollo.tcc.persistence.SoftwareDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class SoftwareBC extends DelegateCrud<SoftwareEntity, Long, SoftwareDAO> {
	private static final long serialVersionUID = 1L;
	
	
}
