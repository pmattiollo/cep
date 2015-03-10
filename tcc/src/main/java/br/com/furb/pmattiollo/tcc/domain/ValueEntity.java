package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "value")
@NamedQueries({
    @NamedQuery(name="ValueEntity.findById", query="SELECT obj FROM ValueEntity obj WHERE obj.id = :id"),				
})
public class ValueEntity extends Value {

	private static final long serialVersionUID = 1L;
	
}
