package br.com.furb.pmattiollo.tcc.persistence;

import br.com.furb.pmattiollo.tcc.domain.ItemTypeEntity;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class ItemTypeDAO extends JPACrud<ItemTypeEntity, Long>{

	private static final long serialVersionUID = 1L;

}
