package br.com.furb.pmattiollo.tcc.domain;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import br.com.furb.pmattiollo.tcc.constant.CalculationEnum;

@MappedSuperclass
public abstract class Calculation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = SEQUENCE)
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "calculation")
	private List<Value> values;
	
	public Calculation() {
		super();
	}

	public Calculation(String revision, Double lsc, Double lc, Double lic, CalculationEnum type, RepresentationEntity representation, List<Value> values) {
		this.revision = revision;
		this.lsc = lsc;
		this.lc = lc;
		this.lic = lic;
		this.type = type;
		this.representation = representation;
		this.values = values;
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

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}

}
