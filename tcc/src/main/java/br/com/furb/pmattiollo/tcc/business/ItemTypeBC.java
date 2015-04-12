
package br.com.furb.pmattiollo.tcc.business;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.furb.pmattiollo.tcc.constant.ItemTypeEnum;
import br.com.furb.pmattiollo.tcc.domain.ItemTypeEntity;
import br.com.furb.pmattiollo.tcc.persistence.ItemTypeDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ItemTypeBC extends DelegateCrud<ItemTypeEntity, Long, ItemTypeDAO> {
	private static final long serialVersionUID = 1L;
	
	
	public List<SelectItem> getItemTypeEnum() {
		List<SelectItem> varItemTypeEnum = new ArrayList<SelectItem>();
		for (ItemTypeEnum eachItemTypeEnum : ItemTypeEnum.values()) {
			varItemTypeEnum.add(new SelectItem(eachItemTypeEnum));
		}
		return varItemTypeEnum;
	}
	
}
