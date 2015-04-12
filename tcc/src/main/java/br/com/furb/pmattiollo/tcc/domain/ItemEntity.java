package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "item")
@NamedQueries({
    @NamedQuery(name="ItemEntity.findById", query="SELECT obj FROM ItemEntity obj WHERE obj.id = :id"),
})
public class ItemEntity extends Item {

	private static final long serialVersionUID = 1L;
	
}
