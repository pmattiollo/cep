package br.com.furb.pmattiollo.tcc.persistence;

import br.com.furb.pmattiollo.tcc.domain.UserEntity;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class UserDAO extends JPACrud<UserEntity, Long> {

	private static final long serialVersionUID = 1L;
	
	public UserEntity findById(Long id) throws Exception{
		return (UserEntity) getEntityManager().createNamedQuery("UserEntity.findById").setParameter("id", id).getSingleResult();
	}
	
	public UserEntity findByLogin(String login) throws Exception{
		return (UserEntity) getEntityManager().createNamedQuery("UserEntity.findByLogin").setParameter("login", login).getSingleResult();
	}	

}
