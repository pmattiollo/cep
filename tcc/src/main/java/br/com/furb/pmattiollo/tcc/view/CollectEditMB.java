
package br.com.furb.pmattiollo.tcc.view;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import br.com.furb.pmattiollo.tcc.business.CollectBC;
import br.com.furb.pmattiollo.tcc.business.ItemBC;
import br.com.furb.pmattiollo.tcc.domain.Collect;
import br.com.furb.pmattiollo.tcc.domain.Item;
import br.com.furb.pmattiollo.tcc.domain.Sample;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./collect_list.jsf")
public class CollectEditMB extends AbstractEditPageBean<Collect, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private CollectBC collectBC;	

	@Inject
	private ItemBC itemBC;
	
	public List<Item> getItemList(){
		return itemBC.findAll();
	}
			
	private DataModel<Sample> sampleList;
	
	public void addSample() {
		this.getBean().getSamples().add(new Sample());
	}
	public void deleteSample() {
	   this.getBean().getSamples().remove(getSampleList().getRowData());
	}
	public DataModel<Sample> getSampleList() {
	   if (sampleList == null) {
		   sampleList = new ListDataModel<Sample>(this.getBean().getSamples());
	   }
	   return sampleList;
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
	protected Collect handleLoad(Long id) {
		return this.collectBC.load(id);
	}	
}