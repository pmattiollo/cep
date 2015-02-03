package br.com.furb.pmattiollo.tcc.domain;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "calculation")
public class Calculation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	@Column(name = "calculation_id")
	private Long id;
	
	@Column(name = "description", nullable = false)
	private String revision;
	
	@Column(name = "result", nullable = false)
	private Double result;
	
	@Column(name = "calculation_type", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private CalculationType type;
	
	@ManyToOne
	@JoinColumn(name = "representation", nullable = false)
	private Representation representation;
	
	@OneToMany
	@JoinColumn(name = "calculation")
	private List<Value> values;
	
	public Calculation() {
		super();
	}

	public Calculation(String revision, Double result, CalculationType type, Representation representation, List<Value> values) {
		this.revision = revision;
		this.result = result;
		this.type = type;
		this.representation = representation;
		this.values = values;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public CalculationType getType() {
		return type;
	}

	public void setType(CalculationType type) {
		this.type = type;
	}

	public Representation getRepresentation() {
		return representation;
	}

	public void setRepresentation(Representation representation) {
		this.representation = representation;
	}
	
	public List<Value> getValues() {
		return values;
	}
	
	public void setValues(List<Value> values) {
		this.values = values;
	}

}
