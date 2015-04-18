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
	
	@Column(name = "lsc", nullable = false)
	private BigDecimal lsc;
	
	@Column(name = "lc", nullable = false)
	private BigDecimal lc;
	
	@Column(name = "lic", nullable = false)
	private BigDecimal lic;
	
	@Column(name = "calculation_type", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private CalculationEnum type;
	
	@ManyToOne
	@JoinColumn(name = "item", nullable = false)
	private ItemEntity item;
	
	public Calculation() {
		super();
	}

	public Calculation(BigDecimal lsc, BigDecimal lc, BigDecimal lic, CalculationEnum type, ItemEntity item) {
		this.lsc = lsc;
		this.lc = lc;
		this.lic = lic;
		this.type = type;
		this.item = item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getLsc() {
		return lsc;
	}

	public void setLsc(BigDecimal lsc) {
		this.lsc = lsc;
	}

	public BigDecimal getLc() {
		return lc;
	}

	public void setLc(BigDecimal lc) {
		this.lc = lc;
	}

	public BigDecimal getLic() {
		return lic;
	}

	public void setLic(BigDecimal lic) {
		this.lic = lic;
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
