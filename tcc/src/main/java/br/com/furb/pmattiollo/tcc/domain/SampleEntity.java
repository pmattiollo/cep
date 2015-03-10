package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "sample")
@NamedQueries({
    @NamedQuery(name="SampleEntity.findById", query="SELECT obj FROM SampleEntity obj WHERE obj.id = :id"),				
})
public class SampleEntity extends Sample {

	private static final long serialVersionUID = 1L;
	
}
