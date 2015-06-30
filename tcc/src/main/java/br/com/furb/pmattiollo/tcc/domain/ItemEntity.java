package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "item")
@NamedQueries({
    @NamedQuery(name="ItemEntity.findById", query="SELECT obj FROM ItemEntity obj WHERE obj.id = :id"),
    @NamedQuery(name="ItemEntity.findByDescription", query="SELECT obj FROM ItemEntity obj WHERE obj.description = :description"),
    @NamedQuery(name="ItemEntity.deleteById", query="DELETE FROM ItemEntity obj WHERE obj.id = :id"),
})
@NamedNativeQueries({
	@NamedNativeQuery(name="ItemEntity.findByProcess", query="SELECT * FROM item WHERE process = :process", resultClass = ItemEntity.class),
	@NamedNativeQuery(name="ItemEntity.deleteSoftwareItemByItem", query="DELETE FROM software_item WHERE item_id = :item", resultClass = ItemEntity.class),
})
public class ItemEntity extends Item {

	private static final long serialVersionUID = 1L;
	
}
