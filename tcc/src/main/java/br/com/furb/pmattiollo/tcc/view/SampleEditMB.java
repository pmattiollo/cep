
package br.com.furb.pmattiollo.tcc.view;

import java.util.List;

import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.SampleBC;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.SampleEntity;
import br.com.furb.pmattiollo.tcc.persistence.CollectDAO;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./collect_list.jsf")
public class SampleEditMB extends AbstractEditPageBean<SampleEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SampleBC sampleBC;
	
	@Inject
	private CollectDAO collectDao;
	
	public List<CollectEntity> getCollectEntityList(){
		return collectDao.findNotFinished();
	}
	
	@Override
	@Transactional
	public String delete() {
		this.sampleBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.sampleBC.insert(this.getBean());		
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.sampleBC.update(this.getBean());
		return getPreviousView();
	}
	
	@Override
	protected SampleEntity handleLoad(Long id) {
		return this.sampleBC.load(id);
	}	
}