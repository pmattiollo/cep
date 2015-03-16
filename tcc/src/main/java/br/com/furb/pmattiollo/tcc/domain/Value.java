package br.com.furb.pmattiollo.tcc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.furb.pmattiollo.tcc.constant.ValueEnum;

@MappedSuperclass
public abstract class Value implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "value_id")
	private Long id;
	
	@Column(name = "value_type", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private ValueEnum type;
	
	@Column(name = "value", nullable = false)
	private Double value;
	
	public Value() {
		super();
	}

	public Value(ValueEnum type, Double value) {
		this.type = type;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ValueEnum getType() {
		return type;
	}

	public void setType(ValueEnum type) {
		this.type = type;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
