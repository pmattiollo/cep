package br.com.furb.pmattiollo.tcc.persistence;

import javax.persistence.Query;

import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class SoftwareDAO extends JPACrud<SoftwareEntity, Long> {

	private static final long serialVersionUID = 1L;
	
	public SoftwareEntity findByDescription(String description) {
		Query query = getEntityManager().createNamedQuery("SoftwareEntity.findByDescription");
		query.setParameter("description", description);
		
		SoftwareEntity software = null;
		
		try {
			software = (SoftwareEntity) query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return software;
	}
	
	@Override
	public void delete(Long id) {
		Query query = getEntityManager().createNamedQuery("CalculationEntity.deleteBySoftware");
		query.setParameter("software", id);
		query.executeUpdate();
		
		query = getEntityManager().createNamedQuery("CollectEntity.deleteBySoftware");
		query.setParameter("software", id);
		query.executeUpdate();
		
		query = getEntityManager().createNamedQuery("SoftwareEntity.deleteSoftwareItemBySoftware");
		query.setParameter("software", id);
		query.executeUpdate();
		
		query = getEntityManager().createNamedQuery("SoftwareEntity.deleteById");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
}
