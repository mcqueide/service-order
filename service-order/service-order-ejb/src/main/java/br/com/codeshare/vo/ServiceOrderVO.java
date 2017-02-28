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
}
