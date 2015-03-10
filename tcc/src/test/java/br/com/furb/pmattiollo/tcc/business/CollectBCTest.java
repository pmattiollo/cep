

package br.com.furb.pmattiollo.tcc.business;

import static org.junit.Assert.*;
import java.util.*;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.com.furb.pmattiollo.tcc.domain.Collect;
import br.com.furb.pmattiollo.tcc.business.CollectBC;

@RunWith(DemoiselleRunner.class)
public class CollectBCTest {

    @Inject
	private CollectBC collectBC;
	
	@Before
	public void before() {
		for (Collect collect : collectBC.findAll()) {
			collectBC.delete(collect.getId());
		}
	}	
	
	
	@Test
	public void testInsert() {
				
		// modifique para inserir dados conforme o construtor
		Collect collect = new Collect(new Date(),Integer.valueOf(1),null,null);
		collectBC.insert(collect);
		List<Collect> listOfCollect = collectBC.findAll();
		assertNotNull(listOfCollect);
		assertEquals(1, listOfCollect.size());
	}	
	
	@Test
	public void testDelete() {
		
		// modifique para inserir dados conforme o construtor
		Collect collect = new Collect(new Date(),Integer.valueOf(1),null,null);
		collectBC.insert(collect);
		
		List<Collect> listOfCollect = collectBC.findAll();
		assertNotNull(listOfCollect);
		assertEquals(1, listOfCollect.size());
		
		collectBC.delete(collect.getId());
		listOfCollect = collectBC.findAll();
		assertEquals(0, listOfCollect.size());
	}
	
	@Test
	public void testUpdate() {
		// modifique para inserir dados conforme o construtor
		Collect collect = new Collect(new Date(),Integer.valueOf(1),null,null);
		collectBC.insert(collect);
		
		List<Collect> listOfCollect = collectBC.findAll();
		Collect collect2 = (Collect)listOfCollect.get(0);
		assertNotNull(listOfCollect);

		// alterar para tratar uma propriedade existente na Entidade Collect
		// collect2.setUmaPropriedade("novo valor");
		collectBC.update(collect2);
		
		listOfCollect = collectBC.findAll();
		Collect collect3 = (Collect)listOfCollect.get(0);
		
		// alterar para tratar uma propriedade existente na Entidade Collect
		// assertEquals("novo valor", collect3.getUmaPropriedade());
	}

}