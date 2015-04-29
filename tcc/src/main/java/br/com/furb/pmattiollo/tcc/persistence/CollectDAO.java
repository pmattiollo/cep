package br.com.furb.pmattiollo.tcc.persistence;

import java.util.List;

import javax.persistence.Query;

import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class CollectDAO extends JPACrud<CollectEntity, Long> {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<CollectEntity> findAllBySoftwareAndItem(SoftwareEntity software, ItemEntity item) {
		Query query = getEntityManager().createNamedQuery("CollectEntity.findAllBySoftwareAndItem");
		query.setParameter("software", software);
		query.setParameter("item", item);
		
		return (List<CollectEntity>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<CollectEntity> findAllByItem(ItemEntity item) {
		Query query = getEntityManager().createNamedQuery("CollectEntity.findAllByItem");
		query.setParameter("item", item);
		
		return (List<CollectEntity>) query.getResultList();
	}

}
