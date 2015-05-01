package br.com.furb.pmattiollo.tcc.persistence;

import javax.persistence.Query;

import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class ItemDAO extends JPACrud<ItemEntity, Long> {

	private static final long serialVersionUID = 1L;
	
	public ItemEntity findByDescription(String description) {
		Query query = getEntityManager().createNamedQuery("ItemEntity.findByDescription");
		query.setParameter("description", description);
		
		return (ItemEntity) query.getSingleResult();
	}
}
