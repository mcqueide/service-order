package br.com.codeshare.vo;

import br.com.codeshare.enums.ServiceOrderState;
import br.com.codeshare.enums.ServiceOrderType;
import br.com.codeshare.model.ServiceOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ServiceOrderVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String reportedProblem;
	
	private String problemFound;
	
	private String executedService;
	
	private ServiceOrderType serviceOrderType;
	
	private ServiceOrderState soState;
	
	private Date dateSo;
	
	private Date approvedDate;
	
	private Date datePhoneWithdrawl;
	
	private BigDecimal value;
	
	private ClientVO client;

	private PhoneVO phone;

	public ServiceOrderVO() {
	}

	public ServiceOrderVO(ServiceOrder entity) {
		this.id = entity.getId();
		this.reportedProblem = entity.getReportedProblem();
		this.problemFound = entity.getProblemFound();
		this.executedService = entity.getExecutedService();
		this.serviceOrderType = entity.getServiceOrderType();
		this.soState = entity.getSoState();
		this.dateSo = entity.getDateSo();
		this.approvedDate = entity.getApprovedDate();
		this.datePhoneWithdrawl = entity.getDatePhoneWithdrawl();
		this.value = entity.getValue();
		if(entity.getClient() != null){
            this.client = new ClientVO(entity.getClient());
        }
        if(entity.getPhone() != null){
            this.phone = new PhoneVO(entity.getPhone());
        }
	}

	public ServiceOrder fromDTO(){
		ServiceOrder entity = new ServiceOrder();
		entity.setId(this.id);
		entity.setReportedProblem(this.reportedProblem);
		entity.setProblemFound(this.problemFound);
		entity.setExecutedService(this.executedService);
		entity.setServiceOrderType(this.serviceOrderType);
		entity.setSOState(this.soState);
		entity.setDateSo(this.dateSo);
		entity.setApprovedDate(this.approvedDate);
		entity.setDatePhoneWithdrawl(this.datePhoneWithdrawl);
		entity.setValue(this.value);
		if(this.client != null){
            entity.setClient(this.client.fromDTO());
        }
        if(this.phone != null){
            entity.setPhone(this.phone.fromDTO());
        }

		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ServiceOrderType getServiceOrderType() {
		return serviceOrderType;
	}

	public void setServiceOrderType(ServiceOrderType serviceOrderType) {
		this.serviceOrderType = serviceOrderType;
	}

	public ServiceOrderState getSOState() {
		return soState;
	}

	public void setSOState(ServiceOrderState soState) {
		this.soState = soState;
	}

	public Date getDateSo() {
		return dateSo;
	}

	public void setDateSo(Date dataSo) {
		this.dateSo = dataSo;
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

	public ClientVO getClient() {
		return client;
	}

	public void setClient(ClientVO client) {
		this.client = client;
	}

	public PhoneVO getPhone() {
		return phone;
	}

	public void setPhone(PhoneVO phone) {
		this.phone = phone;
	}
	
	public ServiceOrderState getSoState() {
		return soState;
	}

	public void setSoState(ServiceOrderState soState) {
		this.soState = soState;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceOrderVO other = (ServiceOrderVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
