
package br.com.furb.pmattiollo.tcc.view;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.CollectBC;
import br.com.furb.pmattiollo.tcc.business.ItemBC;
import br.com.furb.pmattiollo.tcc.domain.CollectEntity;
import br.com.furb.pmattiollo.tcc.domain.ItemEntity;
import br.com.furb.pmattiollo.tcc.domain.SampleEntity;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./collect_list.jsf")
public class CollectEditMB extends AbstractEditPageBean<CollectEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private CollectBC collectBC;
	
	@Inject
	private ItemBC itemEntityBC;
	
	private DataModel<SampleEntity> sampleEntityList;
	
	public List<ItemEntity> getItemEntityList(){
		return itemEntityBC.findAll();
	}			
	
	public void addSampleEntity() {
		this.getBean().getSamples().add(new SampleEntity());
	}
	
	public void deleteSampleEntity() {
	   this.getBean().getSamples().remove(getSampleEntityList().getRowData());
	}
	
	public DataModel<SampleEntity> getSampleEntityList() {
	   if (sampleEntityList == null) {
		   sampleEntityList = new ListDataModel<SampleEntity>(this.getBean().getSamples());
	   }
	   return sampleEntityList;
	} 
	
	@Override
	@Transactional
	public String delete() {
		this.collectBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.collectBC.insert(this.getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.collectBC.update(this.getBean());
		return getPreviousView();
	}
	
	@Override
	protected CollectEntity handleLoad(Long id) {
		return this.collectBC.load(id);
	}	
}