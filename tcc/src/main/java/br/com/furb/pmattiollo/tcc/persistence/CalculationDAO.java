package br.com.furb.pmattiollo.tcc.persistence;

import javax.persistence.Query;

import br.com.furb.pmattiollo.tcc.domain.CalculationEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class CalculationDAO extends JPACrud<CalculationEntity, Long>{

	private static final long serialVersionUID = 1L;
	
	public CalculationEntity findById(Long id) throws Exception{
		return (CalculationEntity) getEntityManager().createNamedQuery("CalculationEntity.findById").setParameter("id", id).getSingleResult();
	}
	
	public CalculationEntity findLastByItem(ItemEntity item) {
		Query query = getEntityManager().createNamedQuery("CalculationEntity.findAllByItem").setParameter("item", item);
		query.setMaxResults(1);
		
		return (CalculationEntity) query.getSingleResult();
	}

}
