package br.com.codeshare.model;

import br.com.codeshare.enums.ServiceOrderState;
import br.com.codeshare.enums.ServiceOrderType;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NamedQuery(name="ServiceOrder.findSoByPhone", query="select so from ServiceOrder so where so.phone.id = :phoneid")
public class ServiceOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_SO_BY_PHONE = "ServiceOrder.findSoByPhone";

	@SequenceGenerator(name = "SEQ_OS", sequenceName = "SEQ_OS", initialValue = 1, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OS")
	private Long id;
	
	@NotNull(message="{reportedProblem.notempty}")
	@NotEmpty(message="{reportedProblem.notempty}")
	private String reportedProblem;
	
	private String problemFound;
	
	private String executedService;
	
	private ServiceOrderType serviceOrderType;
	
	@Enumerated(EnumType.ORDINAL)
	private ServiceOrderState soState;
	
	@Column(name = "dateSo")
	private LocalDate dateSo;
	
	private LocalDate approvedDate;
	
	private LocalDate datePhoneWithdrawl;
	
	@NotNull(message="{value.notempty}")
	private BigDecimal value;
	
	@NotNull(message="{client.notempty}")
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@NotNull(message="{phone.notempty}")
	@ManyToOne
	@JoinColumn(name="phone_id")
	private Phone phone;

	public ServiceOrder() {
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

	public LocalDate getDateSo() {
		return dateSo;
	}

	public void setDateSo(LocalDate dataSo) {
		this.dateSo = dataSo;
	}

	public LocalDate getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(LocalDate approvedDate) {
		this.approvedDate = approvedDate;
	}

	public LocalDate getDatePhoneWithdrawl() {
		return datePhoneWithdrawl;
	}

	public void setDatePhoneWithdrawl(LocalDate datePhoneWithdrawl) {
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
		ServiceOrder other = (ServiceOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
