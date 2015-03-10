package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "representation")
@NamedQueries({
    @NamedQuery(name="RepresentationEntity.findById", query="SELECT obj FROM RepresentationEntity obj WHERE obj.id = :id"),				
})
public class RepresentationEntity extends Representation {

	private static final long serialVersionUID = 1L;
	
}
