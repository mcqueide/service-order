package br.com.codeshare.builder;

import br.com.codeshare.enums.ServiceOrderType;
import br.com.codeshare.vo.ClientVO;
import br.com.codeshare.vo.PhoneVO;
import br.com.codeshare.vo.ServiceOrderVO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ServiceOrderBuild {

	private ServiceOrderVO so;

	public ServiceOrderBuild() {
		this.so = new ServiceOrderVO();
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
	public ServiceOrderBuild withClient(ClientVO client){
		so.setClient(client);
		return this;
	}
	public ServiceOrderBuild withPhone(PhoneVO phone){
		so.setPhone(phone);
		return this;
	}
	public ServiceOrderVO build(){
		return so;
	}
	
}
