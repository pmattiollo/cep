package br.com.furb.pmattiollo.tcc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.furb.pmattiollo.tcc.constant.ItemTypeEnum;

@MappedSuperclass
public abstract class ItemType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_type_id")
	private Long id;
	
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
	@Column(name = "type", nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private ItemTypeEnum type;
	
	public ItemType() {
		super();
	}
	
	public ItemType(String description, ItemTypeEnum type) {
		this.description = description;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public ItemTypeEnum getType() {
		return type;
	}
	
	public void setType(ItemTypeEnum type) {
		this.type = type;
	}

}
