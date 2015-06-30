package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "software")
@NamedQueries({
    @NamedQuery(name="SoftwareEntity.findById", query="SELECT obj FROM SoftwareEntity obj WHERE obj.id = :id"),
    @NamedQuery(name="SoftwareEntity.findAll", query="SELECT obj FROM SoftwareEntity obj"),
    @NamedQuery(name="SoftwareEntity.deleteById", query="DELETE FROM SoftwareEntity obj WHERE obj.id = :id"),
    @NamedQuery(name="SoftwareEntity.findByDescription", query="SELECT obj FROM SoftwareEntity obj WHERE obj.description = :description")
})
@NamedNativeQueries({
	@NamedNativeQuery(name="SoftwareEntity.deleteSoftwareItemBySoftware", query="DELETE FROM software_item WHERE software_id = :software", resultClass = ItemEntity.class),
})
public class SoftwareEntity extends Software {

	private static final long serialVersionUID = 1L;
	
}
