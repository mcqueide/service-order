package br.com.codeshare.model;

import br.com.codeshare.enums.ServiceOrderState;
import br.com.codeshare.enums.ServiceOrderType;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "so")
@NamedQuery(name="ServiceOrder.findSoByPhone", query="SELECT so FROM ServiceOrder so JOIN so.soPhonePhoneState sop where sop.phone.id = :phoneid")
public class ServiceOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_SO_BY_PHONE = "ServiceOrder.findSoByPhone";

	@SequenceGenerator(name = "seq_os", sequenceName = "seq_os", initialValue = 1, allocationSize = 1)
	@Id
	@Column(name = "so_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_os")
	private Long id;
	
	@NotNull(message="{reportedProblem.notempty}")
	@NotEmpty(message="{reportedProblem.notempty}")
	@Column(name = "reported_problem")
	private String reportedProblem;

	@Column(name = "problem_found")
	private String problemFound;

	@Column(name = "executed_service")
	private String executedService;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "service_order_type")
	private ServiceOrderType serviceOrderType;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "so_state")
	private ServiceOrderState soState;
	
	@Column(name = "date_so")
	private LocalDate dateSo;

	@Column(name = "approved_date")
	private LocalDate approvedDate;

	@Column(name = "date_phone_withdrawl")
	private LocalDate datePhoneWithdrawl;
	
	@NotNull(message="{value.notempty}")
	private BigDecimal value;
	
	@NotNull(message="{client.notempty}")
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;

	@OneToMany(mappedBy = "serviceOrder")
	private List<ServiceOrderPhone> soPhonePhoneState;
	
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

	public List<ServiceOrderPhone> getSoPhonePhoneState() {
		return soPhonePhoneState;
	}

	public void setSoPhonePhoneState(List<ServiceOrderPhone> state) {
		this.soPhonePhoneState = state;
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
