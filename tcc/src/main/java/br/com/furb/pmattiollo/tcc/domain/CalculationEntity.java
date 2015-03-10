package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "calculation")
@NamedQueries({
    @NamedQuery(name="CalculationEntity.findById", query="SELECT obj FROM CalculationEntity obj WHERE obj.id = :id"),				
})
public class CalculationEntity extends Calculation {

	private static final long serialVersionUID = 1L;
	
}
