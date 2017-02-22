package br.com.codeshare.builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.codeshare.enums.ServiceOrderState;
import br.com.codeshare.enums.ServiceOrderType;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Phone;
import br.com.codeshare.model.ServiceOrder;

public class ServiceOrderBuild {

	private ServiceOrder so;

	public ServiceOrderBuild() {
		this.so = new ServiceOrder();
	}
	
	public ServiceOrderBuild withId(Long id){
		so.setId(id);
		return this;
	}
	public ServiceOrderBuild withReportedProblem (String reportedProblem){
		so.setReportedProblem(reportedProblem);
		return this;
	}
	public ServiceOrderBuild withProblemFound(String problemFound){
		so.setProblemFound(problemFound);
		return this;
	}
	public ServiceOrderBuild withExecutedService(String executedService){
		so.setExecutedService(executedService);
		return this;
	}
	public ServiceOrderBuild withServiceOrderType (ServiceOrderType serviceOrderType){
		so.setServiceOrderType(serviceOrderType);
		return this;
	}
	public ServiceOrderBuild withServiceOrderState (ServiceOrderState soState){
		so.setSOState(soState);
		return this;
	}
	public ServiceOrderBuild withDate(LocalDate dateSo){
		so.setDateSo(dateSo);
		return this;
	}
	public ServiceOrderBuild withApprovedDate(LocalDate approvedDate){
		so.setApprovedDate(approvedDate);
		return this;
	}
	public ServiceOrderBuild withDatePhoneWithdrawl(LocalDate datePhoneWithdrawl){
		so.setDatePhoneWithdrawl(datePhoneWithdrawl);
		return this;
	}
	public ServiceOrderBuild withValue(BigDecimal value){
		so.setValue(value);
		return this;
	}
	public ServiceOrderBuild withClient(Client client){
		so.setClient(client);
		return this;
	}
	public ServiceOrderBuild withPhone(Phone phone){
		so.setPhone(phone);
		return this;
	}
	public ServiceOrder build(){
		return so;
	}
	
}
