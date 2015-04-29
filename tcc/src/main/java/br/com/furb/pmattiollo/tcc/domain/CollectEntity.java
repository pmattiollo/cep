package br.com.furb.pmattiollo.tcc.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "collect")
@NamedQueries({
    @NamedQuery(name="CollectEntity.findById", query="SELECT obj FROM CollectEntity obj WHERE obj.id = :id"),
    @NamedQuery(name="CollectEntity.findAllBySoftwareAndItem", query="SELECT obj FROM CollectEntity obj WHERE obj.software = :software AND obj.item = :item ORDER BY obj.id ASC"),
    @NamedQuery(name="CollectEntity.findAllByItem", query="SELECT obj FROM CollectEntity obj WHERE obj.item = :item ORDER BY obj.id ASC"),
    @NamedQuery(name="CollectEntity.findAll", query="SELECT obj FROM CollectEntity obj ORDER BY obj.id"),
})
public class CollectEntity extends Collect {

	private static final long serialVersionUID = 1L;
	
}
