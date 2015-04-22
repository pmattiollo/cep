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

@MappedSuperclass
public abstract class Sample implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sample_id")
	private Long id;
	
	@Column(name = "value", nullable = false)
	private BigDecimal value;
	
	@ManyToOne
	@JoinColumn(name = "collect", nullable = false)
	private CollectEntity collect;
	
	public Sample() {
		super();
	}

	public Sample(BigDecimal value, CollectEntity collect) {
		this.value = value;
		this.collect = collect;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public CollectEntity getCollect() {
		return collect;
	}
	
	public void setCollect(CollectEntity collect) {
		this.collect = collect;
	}

}
