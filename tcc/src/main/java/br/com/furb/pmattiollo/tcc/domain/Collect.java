package br.com.furb.pmattiollo.tcc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
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
	
	@ManyToOne
	@JoinColumn(name = "software", nullable = false)
	private SoftwareEntity software;
	
	@ManyToOne
	@JoinColumn(name = "item", nullable = false)
	private ItemEntity item;
	
	@Column(name = "value", nullable = false)
	private BigDecimal value;
	
	public Collect() {
		super();
	}

	public Collect(Date start_date, Integer amount, ItemEntity item, SoftwareEntity software, BigDecimal value) {
		this.start_date = start_date;
		this.item = item;
		this.software = software;
		this.value = value;
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
	
	public ItemEntity getItem() {
		return item;
	}
	
	public void setItem(ItemEntity item) {
		this.item = item;
	}
	
	public SoftwareEntity getSoftware() {
		return software;
	}
	
	public void setSoftware(SoftwareEntity software) {
		this.software = software;
	}

	public BigDecimal getValue() {
		return value;
	}
	
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		Collect other = (Collect) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		
		return true;
	}

}
