package br.com.furb.pmattiollo.tcc.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

@MappedSuperclass
@Entity
public abstract class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Long id;
	
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
	@Column(name = "process", nullable = false, length = 100)
	private String process;
	
	@Column(name = "usl", nullable = false)
	private BigDecimal usl;
	
	@Column(name = "lsl", nullable = false)
	private BigDecimal lsl;
	
	@Type(type="true_false")
	@Column(name = "stable", nullable = false)
	private boolean stable;
	
	@Type(type="true_false")
	@Column(name = "able", nullable = false)
	private boolean able;
	
	public Item() {
		super();
	}

	public Item(String description, String process, BigDecimal usl, BigDecimal lsl, boolean stable, boolean able) {
		this.description = description;
		this.process = process;
		this.usl = usl;
		this.lsl = lsl;
		this.stable = stable;
		this.able = able;
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

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	
	public BigDecimal getUsl() {
		return usl;
	}
	
	public void setUsl(BigDecimal usl) {
		this.usl = usl;
	}
	
	public BigDecimal getLsl() {
		return lsl;
	}
	
	public void setLsl(BigDecimal lsl) {
		this.lsl = lsl;
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

}
