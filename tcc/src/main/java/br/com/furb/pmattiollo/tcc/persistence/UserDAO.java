package br.com.furb.pmattiollo.tcc.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import br.com.furb.pmattiollo.tcc.domain.User;

@PersistenceController
public class UserDAO extends JPACrud<User, Long> {

	private static final long serialVersionUID = 1L;
	

}
