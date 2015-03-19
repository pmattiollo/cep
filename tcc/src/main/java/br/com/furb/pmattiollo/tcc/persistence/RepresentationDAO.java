package br.com.furb.pmattiollo.tcc.persistence;

import br.com.furb.pmattiollo.tcc.domain.RepresentationEntity;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class RepresentationDAO extends JPACrud<RepresentationEntity, Long>{

	private static final long serialVersionUID = 1L;

}
