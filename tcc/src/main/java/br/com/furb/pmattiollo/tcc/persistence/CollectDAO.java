package br.com.furb.pmattiollo.tcc.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class CollectDAO extends JPACrud<CollectEntity, Long> {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<CollectEntity> findFinishedByItem(ItemEntity item) {
		Query query = getEntityManager().createNamedQuery("CollectEntity.findListByItem");
		query.setParameter("item", item);
		
		List<CollectEntity> collects = (List<CollectEntity>) query.getResultList();
		List<CollectEntity> collectsFinished = new ArrayList<CollectEntity>();
		
		for(CollectEntity collect : collects) {
			if(collect.isFinished()) {				
				collectsFinished.add(collect);
			}
		}
		
		return collectsFinished;
	}
	
	@SuppressWarnings("unchecked")
	public List<CollectEntity> findNotFinished() {
		List<CollectEntity> collects = (List<CollectEntity>) getEntityManager().createNamedQuery("CollectEntity.findAll").getResultList();
		List<CollectEntity> collectsNotFinished = new ArrayList<CollectEntity>();
		
		for(CollectEntity collect : collects) {
			if(!collect.isFinished()) {				
				collectsNotFinished.add(collect);
			}
		}
		
		return collectsNotFinished;
	}

}
