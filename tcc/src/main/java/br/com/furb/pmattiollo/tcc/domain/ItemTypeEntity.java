package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "item_type")
@NamedQueries({
    @NamedQuery(name="ItemTypeEntity.findById", query="SELECT obj FROM ItemTypeEntity obj WHERE obj.id = :id"),				
})
public class ItemTypeEntity extends ItemType {

	private static final long serialVersionUID = 1L;
	
}