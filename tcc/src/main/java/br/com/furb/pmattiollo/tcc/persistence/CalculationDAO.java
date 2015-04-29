package br.com.furb.pmattiollo.tcc.persistence;

import javax.persistence.Query;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;
import br.com.furb.pmattiollo.tcc.domain.CalculationEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class CalculationDAO extends JPACrud<CalculationEntity, Long>{

	private static final long serialVersionUID = 1L;
	
	public CalculationEntity findById(Long id) throws Exception{
		return (CalculationEntity) getEntityManager().createNamedQuery("CalculationEntity.findById").setParameter("id", id).getSingleResult();
	}
	
	public CalculationEntity findLastBySoftwareAndItemAndType(SoftwareEntity software, ItemEntity item, CalculationEnum type) {
		Query query = getEntityManager().createNamedQuery("CalculationEntity.findAllBySoftwareAndItemAndType");
		query.setParameter("software", software);
		query.setParameter("item", item);
		query.setParameter("type", type);
		query.setMaxResults(1);
		
		return (CalculationEntity) query.getSingleResult();
	}

}
