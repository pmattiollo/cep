
package br.com.furb.pmattiollo.tcc.business;

import br.com.furb.pmattiollo.tcc.domain.User;
import br.com.furb.pmattiollo.tcc.persistence.UserDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class UserBC extends DelegateCrud<User, Long, UserDAO> {
	private static final long serialVersionUID = 1L;
	
	
}