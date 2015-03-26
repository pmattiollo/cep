package br.com.furb.pmattiollo.tcc.domain;

import java.io.Serializable;

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
	
	@Column(name = "description", nullable = false, length = 100)
	private String revision;
	
	@Column(name = "lsc", nullable = false)
	private Double lsc;
	
	@Column(name = "lc", nullable = false)
	private Double lc;
	
	@Column(name = "lic", nullable = false)
	private Double lic;
	
	@Column(name = "calculation_type", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private CalculationEnum type;
	
	@ManyToOne
	@JoinColumn(name = "representation", nullable = false)
	private RepresentationEntity representation;
	
	public Calculation() {
		super();
	}

	public Calculation(String revision, Double lsc, Double lc, Double lic, CalculationEnum type, RepresentationEntity representation) {
		this.revision = revision;
		this.lsc = lsc;
		this.lc = lc;
		this.lic = lic;
		this.type = type;
		this.representation = representation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public Double getLsc() {
		return lsc;
	}

	public void setLsc(Double lsc) {
		this.lsc = lsc;
	}

	public Double getLc() {
		return lc;
	}

	public void setLc(Double lc) {
		this.lc = lc;
	}

	public Double getLic() {
		return lic;
	}

	public void setLic(Double lic) {
		this.lic = lic;
	}

	public CalculationEnum getType() {
		return type;
	}

	public void setType(CalculationEnum type) {
		this.type = type;
	}

	public RepresentationEntity getRepresentation() {
		return representation;
	}

	public void setRepresentation(RepresentationEntity representation) {
		this.representation = representation;
	}

}
