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
public abstract class Sample implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sample_id")
	private Long id;
	
	@Column(name = "number", nullable = false)
	private Integer number;
	
	@Column(name = "value", nullable = false)
	private Double value;
	
	@ManyToOne
	@JoinColumn(name = "unit", nullable = false)
	private UnitEntity unit;
	
	@ManyToOne
	@JoinColumn(name = "collect", nullable = false)
	private CollectEntity collect;
	
	public Sample() {
		super();
	}

	public Sample(Integer number, Double value, UnitEntity unit, CollectEntity collect) {
		this.number = number;
		this.value = value;
		this.unit = unit;
		this.collect = collect;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(UnitEntity unit) {
		this.unit = unit;
	}
	
	public CollectEntity getCollect() {
		return collect;
	}
	
	public void setCollect(CollectEntity collect) {
		this.collect = collect;
	}

}
