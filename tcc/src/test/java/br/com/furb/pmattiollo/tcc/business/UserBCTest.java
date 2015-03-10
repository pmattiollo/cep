

package br.com.furb.pmattiollo.tcc.business;

import static org.junit.Assert.*;
import java.util.*;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.com.furb.pmattiollo.tcc.domain.User;
import br.com.furb.pmattiollo.tcc.business.UserBC;

@RunWith(DemoiselleRunner.class)
public class UserBCTest {

    @Inject
	private UserBC userBC;
	
	@Before
	public void before() {
		for (User user : userBC.findAll()) {
			userBC.delete(user.getId());
		}
	}	
	
	
	@Test
	public void testInsert() {
				
		// modifique para inserir dados conforme o construtor
		User user = new User("name","login","password","email");
		userBC.insert(user);
		List<User> listOfUser = userBC.findAll();
		assertNotNull(listOfUser);
		assertEquals(1, listOfUser.size());
	}	
	
	@Test
	public void testDelete() {
		
		// modifique para inserir dados conforme o construtor
		User user = new User("name","login","password","email");
		userBC.insert(user);
		
		List<User> listOfUser = userBC.findAll();
		assertNotNull(listOfUser);
		assertEquals(1, listOfUser.size());
		
		userBC.delete(user.getId());
		listOfUser = userBC.findAll();
		assertEquals(0, listOfUser.size());
	}
	
	@Test
	public void testUpdate() {
		// modifique para inserir dados conforme o construtor
		User user = new User("name","login","password","email");
		userBC.insert(user);
		
		List<User> listOfUser = userBC.findAll();
		User user2 = (User)listOfUser.get(0);
		assertNotNull(listOfUser);

		// alterar para tratar uma propriedade existente na Entidade User
		// user2.setUmaPropriedade("novo valor");
		userBC.update(user2);
		
		listOfUser = userBC.findAll();
		User user3 = (User)listOfUser.get(0);
		
		// alterar para tratar uma propriedade existente na Entidade User
		// assertEquals("novo valor", user3.getUmaPropriedade());
	}

}