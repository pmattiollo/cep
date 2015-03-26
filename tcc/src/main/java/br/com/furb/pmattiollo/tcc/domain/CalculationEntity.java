package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "calculation")
@NamedQueries({
    @NamedQuery(name="CalculationEntity.findById", query="SELECT obj FROM CalculationEntity obj WHERE obj.id = :id"),			
    @NamedQuery(name="CalculationEntity.findLastByType", query="SELECT obj FROM CalculationEntity obj WHERE obj.type = :type ORDER BY obj.id DESC"),
})
public class CalculationEntity extends Calculation {

	private static final long serialVersionUID = 1L;
	
}
