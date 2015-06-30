
package br.com.furb.pmattiollo.tcc.business;

import br.com.furb.pmattiollo.tcc.domain.ProcessEntity;
import br.com.furb.pmattiollo.tcc.persistence.ProcessDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ProcessBC extends DelegateCrud<ProcessEntity, Long, ProcessDAO> {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void delete(Long id) {
		ProcessDAO dao = new ProcessDAO();
		dao.delete(id);
	}	
	
}
