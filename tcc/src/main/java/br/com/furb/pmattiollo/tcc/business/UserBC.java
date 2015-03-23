
package br.com.furb.pmattiollo.tcc.business;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.furb.pmattiollo.tcc.constant.UserEnum;
import br.com.furb.pmattiollo.tcc.domain.UserEntity;
import br.com.furb.pmattiollo.tcc.persistence.UserDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class UserBC extends DelegateCrud<UserEntity, Long, UserDAO> {
	private static final long serialVersionUID = 1L;
	
	
	public List<SelectItem> getUserEnum() {
		List<SelectItem> varUserEnum = new ArrayList<SelectItem>();
		for (UserEnum eachUserEnum : UserEnum.values()) {
			varUserEnum.add(new SelectItem(eachUserEnum));
		}
		return varUserEnum;
	}
	
}
