package br.com.compasso.resgate.controller.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.compasso.resgate.model.StatusResgate;

public class StatusResgateDto {

	private Long id;

	private Long idUsuario;

	private List<ResgateItemDto> resgates = new ArrayList<ResgateItemDto>();

	private String status;

	private Date dataResgate;

	public StatusResgateDto(StatusResgate statusResgate) {
		this.id = statusResgate.getId();
		statusResgate.getResgate().getItensComprados().forEach(e -> {
			resgates.add(new ResgateItemDto(e));
		});
		this.idUsuario = statusResgate.getResgate().getIdUsuario();
		this.status = statusResgate.getStatus().getDescricao();
		this.dataResgate = statusResgate.getDataResgate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataResgate() {
		return dataResgate;
	}

	public void setDataResgate(Date dataResgate) {
		this.dataResgate = dataResgate;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ResgateItemDto> getResgates() {
		return resgates;
	}

	public void setResgates(List<ResgateItemDto> resgates) {
		this.resgates = resgates;
	}

}
