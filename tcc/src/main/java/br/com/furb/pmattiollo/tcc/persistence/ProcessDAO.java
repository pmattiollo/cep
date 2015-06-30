package br.com.furb.pmattiollo.tcc.persistence;

import java.util.List;

import javax.persistence.Query;

import br.com.furb.pmattiollo.tcc.domain.Item;
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
		
		ProcessEntity process = null;
		
		try {
			process = (ProcessEntity) query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return process;
	}
	
	@Override
	public void delete(Long id) {
		Query query = getEntityManager().createNamedQuery("ItemEntity.findByProcess");
		query.setParameter("process", id);
		
		List<Item> items = query.getResultList();
		ItemDAO dao = new ItemDAO();
		for(Item item : items) {
			dao.delete(item.getId());
		}
		
		query = getEntityManager().createNamedQuery("ProcessEntity.deleteById");
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
