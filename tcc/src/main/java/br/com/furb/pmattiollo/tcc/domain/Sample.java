package br.com.furb.pmattiollo.tcc.domain;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "sample")
public class Sample implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	@Column(name = "sample_id")
	private Long id;
	
	@Column(name = "number", nullable = false)
	private Integer number;
	
	@Column(name = "value", nullable = false)
	private Double value;
	
	@ManyToOne
	@JoinColumn(name = "unit", nullable = false)
	private Unit unit;
	
	public Sample() {
		super();
	}

	public Sample(Integer number, Double value, Unit unit) {
		this.number = number;
		this.value = value;
		this.unit = unit;
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

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}
