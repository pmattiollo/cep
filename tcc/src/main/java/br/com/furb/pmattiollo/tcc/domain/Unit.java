package br.com.furb.pmattiollo.tcc.domain;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "unit")
public class Unit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	@Column(name = "unit_id")
	private Long id;
	
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
	public Unit() {
		super();
	}
	
	public Unit(String description) {
		this.description = description;
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

}
