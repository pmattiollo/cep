package br.com.furb.pmattiollo.tcc.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Software implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "software_id")
	private Long id;
	
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "software_item", 
			   joinColumns = {@JoinColumn(name = "software_id", nullable = false, updatable = false)}, 
			   inverseJoinColumns = {@JoinColumn(name = "item_id", nullable = false, updatable = false)})
	private List<ItemEntity> items;
	
	public Software() {
		super();
	}

	public Software(String description, List<ItemEntity> items) {
		this.description = description;
		this.items = items;
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

	public List<ItemEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}
	
}
