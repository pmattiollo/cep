package br.com.furb.pmattiollo.tcc.persistence;

import br.com.furb.pmattiollo.tcc.domain.ProcessEntity;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class UnitDAO extends JPACrud<ProcessEntity, Long> {

	private static final long serialVersionUID = 1L;
	

}
