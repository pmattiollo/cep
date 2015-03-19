package br.com.furb.pmattiollo.tcc.persistence;

import br.com.furb.pmattiollo.tcc.domain.CalculationEntity;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class CalculationDAO extends JPACrud<CalculationEntity, Long>{

	private static final long serialVersionUID = 1L;

}
