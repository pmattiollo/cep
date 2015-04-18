package br.com.furb.pmattiollo.tcc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Long id;
	
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
	@Column(name = "process", nullable = false, length = 100)
	private String process;
	
	public Item() {
		super();
	}
	
	public Item(String description, String process) {
		this.description = description;
		this.process = process;
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

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

}
