package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "process")
@NamedQueries({
    @NamedQuery(name="ProcessEntity.findById", query="SELECT obj FROM ProcessEntity obj WHERE obj.id = :id"),				
})
public class ProcessEntity extends Process {

	private static final long serialVersionUID = 1L;
	
}
