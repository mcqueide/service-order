package br.com.codeshare.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.codeshare.enums.ServiceOrderState;
import br.com.codeshare.enums.ServiceOrderType;

@Entity
public class ServiceOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "SEQ_OS", sequenceName = "SEQ_OS", initialValue = 1, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OS")
	private Long numberOS;
	
	private String reportedProblem;
	
	private String problemFound;
	
	private String executedService;
	
	private ServiceOrderType orderServiceType;
	
	@Enumerated(EnumType.ORDINAL)
	private ServiceOrderState estadoOrdemServico;
	
	@Column(name = "dateSo")
	@Temporal(TemporalType.DATE)
	private Date dataSo;
	
	@Temporal(TemporalType.DATE)
	private Date approvedDate;
	
	@Temporal(TemporalType.DATE)
	private Date datePhoneWithdrawl;
	
	@ManyToOne
	private Client client;
	
	@ManyToOne
	private Phone phone;

	public ServiceOrder() {
	}
	
	public Long getNumberOS() {
		return numberOS;
	}

	public void setNumberOS(Long numberOS) {
		this.numberOS = numberOS;
	}

	public String getReportedProblem() {
		return reportedProblem;
	}

	public void setReportedProblem(String reportedProblem) {
		this.reportedProblem = reportedProblem;
	}

	public String getProblemFound() {
		return problemFound;
	}

	public void setProblemFound(String problemFound) {
		this.problemFound = problemFound;
	}

	public String getExecutedService() {
		return executedService;
	}

	public void setExecutedService(String executedService) {
		this.executedService = executedService;
	}

	public ServiceOrderType getOrderServiceType() {
		return orderServiceType;
	}

	public void setOrderServiceType(ServiceOrderType orderServiceType) {
		this.orderServiceType = orderServiceType;
	}

	public ServiceOrderState getEstadoOrdemServico() {
		return estadoOrdemServico;
	}

	public void setEstadoOrdemServico(ServiceOrderState estadoOrdemServico) {
		this.estadoOrdemServico = estadoOrdemServico;
	}

	public Date getDataSo() {
		return dataSo;
	}

	public void setDataSo(Date dataSo) {
		this.dataSo = dataSo;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Date getDatePhoneWithdrawl() {
		return datePhoneWithdrawl;
	}

	public void setDatePhoneWithdrawl(Date datePhoneWithdrawl) {
		this.datePhoneWithdrawl = datePhoneWithdrawl;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}
}
