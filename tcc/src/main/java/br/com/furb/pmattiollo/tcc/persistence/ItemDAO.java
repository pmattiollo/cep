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
		
		ItemEntity item = null;
		
		try {
			item = (ItemEntity) query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return item;
	}
	
	@Override
	public void delete(Long id) {
		Query query = getEntityManager().createNamedQuery("CalculationEntity.deleteByItem");
		query.setParameter("item", id);
		query.executeUpdate();
		
		query = getEntityManager().createNamedQuery("CollectEntity.deleteByItem");
		query.setParameter("item", id);
		query.executeUpdate();
		
		query = getEntityManager().createNamedQuery("ItemEntity.deleteSoftwareItemByItem");
		query.setParameter("item", id);
		query.executeUpdate();
		
		query = getEntityManager().createNamedQuery("ItemEntity.deleteById");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
}
