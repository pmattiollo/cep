package br.com.furb.pmattiollo.tcc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Collect implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "collect_id")
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false)
	private Date start_date;
	
	@Column(name = "amount", nullable = false)
	private Integer amount;
	
	@ManyToOne
	@JoinColumn(name = "item", nullable = false)
	private ItemEntity item;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "sample")
	private List<SampleEntity> samples;
	
	public Collect() {
		super();
	}

	public Collect(Date start_date, Integer amount, ItemEntity item, List<SampleEntity> samples) {
		this.start_date = start_date;
		this.amount = amount;
		this.item = item;
		this.samples = samples;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public ItemEntity getItem() {
		return item;
	}
	
	public void setItem(ItemEntity item) {
		this.item = item;
	}

	public List<SampleEntity> getSamples() {
		return samples;
	}

	public void setSamples(List<SampleEntity> samples) {
		this.samples = samples;
	}

}
