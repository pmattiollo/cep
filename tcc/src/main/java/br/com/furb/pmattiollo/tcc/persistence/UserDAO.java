package br.com.furb.pmattiollo.tcc.persistence;

import javax.persistence.Query;

import br.com.furb.pmattiollo.tcc.domain.UserEntity;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class UserDAO extends JPACrud<UserEntity, Long> {

	private static final long serialVersionUID = 1L;
	
	public UserEntity findById(Long id) throws Exception{		
		Query query = getEntityManager().createNamedQuery("UserEntity.findById");
		query.setParameter("id", id);
		
		UserEntity user = null;
		
		try {
			user = (UserEntity) query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public UserEntity findByLogin(String login) throws Exception{		
		Query query = getEntityManager().createNamedQuery("UserEntity.findByLogin");
		query.setParameter("login", login);
		
		UserEntity user = null;
		
		try {
			user = (UserEntity) query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}	

}
