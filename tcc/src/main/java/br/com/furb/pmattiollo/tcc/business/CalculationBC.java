package br.com.furb.pmattiollo.tcc.business;

import br.com.furb.pmattiollo.tcc.domain.CalculationEntity;
import br.com.furb.pmattiollo.tcc.persistence.CalculationDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class CalculationBC extends DelegateCrud<CalculationEntity, Long, CalculationDAO> {
	private static final long serialVersionUID = 1L;

}
