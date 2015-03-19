package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "software")
@NamedQueries({
    @NamedQuery(name="SoftwareEntity.findById", query="SELECT obj FROM SoftwareEntity obj WHERE obj.id = :id"),				
})
public class SoftwareEntity extends Software {

	private static final long serialVersionUID = 1L;
	
}
