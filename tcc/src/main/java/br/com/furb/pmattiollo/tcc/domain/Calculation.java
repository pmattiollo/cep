package br.com.furb.pmattiollo.tcc.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;

@MappedSuperclass
public abstract class Calculation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "calculation_id")
	private Long id;
	
	@Column(name = "ucl", nullable = false)
	private BigDecimal ucl;
	
	@Column(name = "cl", nullable = false)
	private BigDecimal cl;
	
	@Column(name = "lcl", nullable = false)
	private BigDecimal lcl;
	
	@Column(name = "calculation_type", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private CalculationEnum type;
	
	@ManyToOne
	@JoinColumn(name = "item", nullable = false)
	private ItemEntity item;
	
	public Calculation() {
		super();
	}

	public Calculation(BigDecimal ucl, BigDecimal cl, BigDecimal lcl, CalculationEnum type, ItemEntity item) {
		this.ucl = ucl;
		this.cl = cl;
		this.lcl = lcl;
		this.type = type;
		this.item = item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getUcl() {
		return ucl;
	}

	public void setUcl(BigDecimal ucl) {
		this.ucl = ucl;
	}

	public BigDecimal getCl() {
		return cl;
	}

	public void setCl(BigDecimal cl) {
		this.cl = cl;
	}

	public BigDecimal getLcl() {
		return lcl;
	}

	public void setLcl(BigDecimal lcl) {
		this.lcl = lcl;
	}

	public CalculationEnum getType() {
		return type;
	}

	public void setType(CalculationEnum type) {
		this.type = type;
	}

	public ItemEntity getItem() {
		return item;
	}
	
	public void setItem(ItemEntity item) {
		this.item = item;
	}

}
