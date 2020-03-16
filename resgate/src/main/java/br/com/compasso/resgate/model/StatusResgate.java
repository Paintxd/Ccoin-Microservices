package br.com.compasso.resgate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "status_resgates")
public class StatusResgate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	private Resgate resgate;

	@ManyToOne(fetch = FetchType.EAGER)
	private TiposStatus status;

	private Date dataResgate;

	public StatusResgate() {
	}

	public StatusResgate(Resgate resgate, TiposStatus status) {
		this.resgate = resgate;
		this.status = status;
		this.dataResgate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Resgate getResgate() {
		return resgate;
	}

	public void setResgate(Resgate resgate) {
		this.resgate = resgate;
	}

	public TiposStatus getStatus() {
		return status;
	}

	public void setStatus(TiposStatus status) {
		this.status = status;
	}

	public Date getDataResgate() {
		return dataResgate;
	}

	public void setDataResgate(Date dataResgate) {
		this.dataResgate = dataResgate;
	}

}
