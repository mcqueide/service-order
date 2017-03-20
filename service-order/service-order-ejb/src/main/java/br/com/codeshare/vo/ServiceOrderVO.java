package br.com.codeshare.vo;

import br.com.codeshare.enums.ServiceOrderState;
import br.com.codeshare.enums.ServiceOrderType;
import br.com.codeshare.util.LocalDateDeserializer;
import br.com.codeshare.util.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by mcqueide on 28/02/17.
 */
public class ServiceOrderVO {

    private Long id;
    private String reportedProblem;
    private String problemFound;
    private String executedService;
    private ServiceOrderType serviceOrderType;
    private ServiceOrderState soState;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateSo;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate approvedDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate datePhoneWithdrawl;
    private BigDecimal value;
    private ClientVO client;
    private PhoneVO phone;

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

    public ServiceOrderState getSoState() {
        return soState;
    }

    public void setSoState(ServiceOrderState soState) {
        this.soState = soState;
    }

    public LocalDate getDateSo() {
        return dateSo;
    }

    public void setDateSo(LocalDate dateSo) {
        this.dateSo = dateSo;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceOrderVO that = (ServiceOrderVO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (reportedProblem != null ? !reportedProblem.equals(that.reportedProblem) : that.reportedProblem != null)
            return false;
        if (problemFound != null ? !problemFound.equals(that.problemFound) : that.problemFound != null) return false;
        if (executedService != null ? !executedService.equals(that.executedService) : that.executedService != null)
            return false;
        if (serviceOrderType != that.serviceOrderType) return false;
        if (soState != that.soState) return false;
        if (dateSo != null ? !dateSo.equals(that.dateSo) : that.dateSo != null) return false;
        if (approvedDate != null ? !approvedDate.equals(that.approvedDate) : that.approvedDate != null) return false;
        if (datePhoneWithdrawl != null ? !datePhoneWithdrawl.equals(that.datePhoneWithdrawl) : that.datePhoneWithdrawl != null)
            return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        return phone != null ? phone.equals(that.phone) : that.phone == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reportedProblem != null ? reportedProblem.hashCode() : 0);
        result = 31 * result + (problemFound != null ? problemFound.hashCode() : 0);
        result = 31 * result + (executedService != null ? executedService.hashCode() : 0);
        result = 31 * result + (serviceOrderType != null ? serviceOrderType.hashCode() : 0);
        result = 31 * result + (soState != null ? soState.hashCode() : 0);
        result = 31 * result + (dateSo != null ? dateSo.hashCode() : 0);
        result = 31 * result + (approvedDate != null ? approvedDate.hashCode() : 0);
        result = 31 * result + (datePhoneWithdrawl != null ? datePhoneWithdrawl.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
