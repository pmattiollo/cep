package br.com.furb.pmattiollo.tcc.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.com.furb.pmattiollo.tcc.domain.User;
import br.com.furb.pmattiollo.tcc.domain.UserEntity;

@PersistenceController
public class UserDAO extends JPACrud<User, Long> {

	private static final long serialVersionUID = 1L;
	
	public UserEntity findById(Long id) throws Exception{
		return (UserEntity) getEntityManager().createNamedQuery("UserEntity.findById").setParameter("id", id).getSingleResult();
	}
	
	public UserEntity findByLogin(String login) throws Exception{
		return (UserEntity) getEntityManager().createNamedQuery("UserEntity.findByLogin").setParameter("login", login).getSingleResult();
	}	

}
