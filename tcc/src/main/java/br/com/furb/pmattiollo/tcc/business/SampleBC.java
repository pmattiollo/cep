
package br.com.furb.pmattiollo.tcc.business;

import br.com.furb.pmattiollo.tcc.domain.SampleEntity;
import br.com.furb.pmattiollo.tcc.persistence.SampleDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class SampleBC extends DelegateCrud<SampleEntity, Long, SampleDAO> {
	private static final long serialVersionUID = 1L;
	
	
}
