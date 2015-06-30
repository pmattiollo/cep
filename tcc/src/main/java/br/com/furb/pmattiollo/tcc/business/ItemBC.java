
package br.com.furb.pmattiollo.tcc.business;

import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.persistence.ItemDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ItemBC extends DelegateCrud<ItemEntity, Long, ItemDAO> {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void delete(Long id) {
		ItemDAO dao = new ItemDAO();
		dao.delete(id);
	}
	
}
