package br.com.furb.pmattiollo.tcc.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Long id;
	
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
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
	
	@ManyToOne
	@JoinColumn(name = "process", nullable = false)
	private ProcessEntity process;
	
	public Item() {
		super();
	}

	public Item(String description, BigDecimal usl, BigDecimal lsl, boolean stable, boolean able, ProcessEntity process) {
		this.description = description;
		this.usl = usl;
		this.lsl = lsl;
		this.stable = stable;
		this.able = able;
		this.process = process;
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
	
	public ProcessEntity getProcess() {
		return process;
	}
	
	public void setProcess(ProcessEntity process) {
		this.process = process;
	}

}
