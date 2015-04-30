package br.com.furb.pmattiollo.tcc.persistence;

import javax.persistence.Query;

import br.com.furb.pmattiollo.tcc.domain.ProcessEntity;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class ProcessDAO extends JPACrud<ProcessEntity, Long> {

	private static final long serialVersionUID = 1L;
	
	public boolean existsProcess(String description) {
		return findByDescription(description) != null;
	}
	
	public ProcessEntity findByDescription(String description) {
		Query query = getEntityManager().createNamedQuery("ProcessEntity.findByDescription");
		query.setParameter("description", description);
		
		return (ProcessEntity) query.getSingleResult();
	}

}
