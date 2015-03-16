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
public abstract class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Long id;
	
	@Column(name = "revision", nullable = false)
	private Integer revision;
	
	@Column(name = "process", nullable = false, length = 100)
	private String process;
	
	@Column(name = "activity", nullable = false, length = 100)
	private String activity;
	
	@ManyToOne
	@JoinColumn(name = "item_type", nullable = false)
	private ItemTypeEntity itemType;
	
	public Item() {
		super();
	}
	
	public Item(Integer revision, String process, String activity, ItemTypeEntity itemType) {
		this.revision = revision;
		this.process = process;
		this.activity = activity;
		this.itemType = itemType;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRevision() {
		return revision;
	}

	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public ItemTypeEntity getItemType() {
		return itemType;
	}

	public void setItemType(ItemTypeEntity itemType) {
		this.itemType = itemType;
	}

}
