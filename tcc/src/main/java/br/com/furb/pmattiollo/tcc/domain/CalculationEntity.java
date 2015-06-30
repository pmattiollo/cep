package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "calculation")
@NamedQueries({
    @NamedQuery(name="CalculationEntity.findById", query="SELECT obj FROM CalculationEntity obj WHERE obj.id = :id"),			
    @NamedQuery(name="CalculationEntity.findAllBySoftwareAndItemAndType", query="SELECT obj FROM CalculationEntity obj WHERE obj.software = :software AND obj.item = :item AND obj.type = :type ORDER BY obj.id DESC"),
})
@NamedNativeQueries({
	@NamedNativeQuery(name="CalculationEntity.deleteByItem", query="DELETE FROM calculation WHERE item = :item", resultClass = CalculationEntity.class),
	@NamedNativeQuery(name="CalculationEntity.deleteBySoftware", query="DELETE FROM calculation WHERE software = :software", resultClass = CalculationEntity.class),
})
public class CalculationEntity extends Calculation {

	private static final long serialVersionUID = 1L;
	
}
