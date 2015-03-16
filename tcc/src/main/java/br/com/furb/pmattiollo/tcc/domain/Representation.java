package br.com.furb.pmattiollo.tcc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Representation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "representation_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "item", nullable = false)
	private ItemEntity item;
	
	public Representation() {
		super();
	}
	
	public Representation(ItemEntity item) {
		this.item = item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemEntity getItem() {
		return item;
	}
	
	public void setItem(ItemEntity item) {
		this.item = item;
	}

}
