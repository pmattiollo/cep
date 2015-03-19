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
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class Software implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "software_id")
	private Long id;
	
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
	@Type(type = "true_false")
	@Column(name = "stable", nullable = false)
	private boolean stable;
	
	@Type(type = "true_false")
	@Column(name = "able", nullable = false)
	private boolean able;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "item")
	private List<ItemEntity> items;
	
	public Software() {
		super();
	}

	public Software(String description, boolean stable, boolean able, List<ItemEntity> items) {
		this.description = description;
		this.stable = stable;
		this.able = able;
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

	public boolean isStable() {
		return stable;
	}

	public void setStable(boolean stable) {
		this.stable = stable;
	}

	public boolean isAble() {
		return able;
	}

	public void setAble(boolean able) {
		this.able = able;
	}

	public List<ItemEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}
	
}
