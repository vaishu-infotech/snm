package com.vaishuinfo.managedbean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;

import com.vaishuinfo.dao.MastersDAO;
import com.vaishuinfo.dao.ReportsDAO;
import com.vaishuinfo.setting.DBResourseLoader;

@ManagedBean
@SessionScoped
public class ReportBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private String customerName = "";
    private String customerName1 = "";
    private String customerName2 = "";
    private String Particulars = "";
    private String OrderNumber = "";
    private String BankName = "";
    private String CustomerType = "";
    private Date FromDate;
    private Date ToDate;
    private Date FromDate1;
    private Date ToDate1;
    private Date FromDate2;
    private Date ToDate2;
    private Date FromDate3;
    private Date ToDate3;
    private Date AsOnDate = new Date();
    ReportsDAO rDAO = new ReportsDAO();
    MastersDAO mDAO = new MastersDAO();
    private Logger logger = DBResourseLoader.getInstance().getLogger(getClass());
    
    private String OpeningBalance="";
    private String TotalDebit = "";
    private String TotalCredit = "";
    private String ClosingBalance = "";
    private String tdsStatus = "";
    private String tdsPercent="ALL";
    private List<String>tdsList;
    
	public ReportBean(){
		MastersDAO mdao = new MastersDAO();
		
		tdsList = mdao.getMasterList("TDS");
    }
    
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerType() {
		return CustomerType;
	}

	public void setCustomerType(String customerType) {
		CustomerType = customerType;
	}

	public Date getFromDate() {
		return FromDate;
	}
	public void setFromDate(Date fromDate) {
		FromDate = fromDate;
	}
	public Date getToDate() {
		return ToDate;
	}
	public void setToDate(Date toDate) {
		ToDate = toDate;
	}
	public Date getAsOnDate() {
		return AsOnDate;
	}
	public void setAsOnDate(Date asOnDate) {
		AsOnDate = asOnDate;
	}
	public String getCustomerName1() {
		return customerName1;
	}
	public void setCustomerName1(String customerName1) {
		this.customerName1 = customerName1;
	}
	public Date getFromDate1() {
		return FromDate1;
	}
	public void setFromDate1(Date fromDate1) {
		FromDate1 = fromDate1;
	}
	public Date getToDate1() {
		return ToDate1;
	}
	public void setToDate1(Date toDate1) {
		ToDate1 = toDate1;
	}
	public String getParticulars() {
		return Particulars;
	}
	public void setParticulars(String particulars) {
		Particulars = particulars;
	}
	public String getOrderNumber() {
		return OrderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public Date getFromDate2() {
		return FromDate2;
	}
	public void setFromDate2(Date fromDate2) {
		FromDate2 = fromDate2;
	}
	public Date getToDate2() {
		return ToDate2;
	}
	public void setToDate2(Date toDate2) {
		ToDate2 = toDate2;
	}
	public String getCustomerName2() {
		return customerName2;
	}
	public void setCustomerName2(String customerName2) {
		this.customerName2 = customerName2;
	}
	public Date getFromDate3() {
		return FromDate3;
	}
	public void setFromDate3(Date fromDate3) {
		FromDate3 = fromDate3;
	}
	public Date getToDate3() {
		return ToDate3;
	}
	public void setToDate3(Date toDate3) {
		ToDate3 = toDate3;
	}
	
	public String getOpeningBalance() {
		return OpeningBalance;
	}

	public void setOpeningBalance(String openingBalance) {
		OpeningBalance = openingBalance;
	}

	public String getTotalDebit() {
		return TotalDebit;
	}

	public void setTotalDebit(String totalDebit) {
		TotalDebit = totalDebit;
	}

	public String getTotalCredit() {
		return TotalCredit;
	}

	public void setTotalCredit(String totalCredit) {
		TotalCredit = totalCredit;
	}

	public String getTdsPercent() {
		return tdsPercent;
	}

	public void setTdsPercent(String tdsPercent) {
		this.tdsPercent = tdsPercent;
	}

	public List<String> getTdsList() {
		return tdsList;
	}

	public void setTdsList(List<String> tdsList) {
		this.tdsList = tdsList;
	}

	public String getClosingBalance() {
		return ClosingBalance;
	}

	public void setClosingBalance(String closingBalance) {
		ClosingBalance = closingBalance;
	}

	
	public String getTdsStatus() {
		return tdsStatus;
	}

	public void setTdsStatus(String tdsStatus) {
		this.tdsStatus = tdsStatus;
	}

	public void generateLedgerReport(){
		logger.info("generateLedgerReport"+this.customerName);
		
		ReportBean repbean = rDAO.CustomerLedgerReport(this.customerName,this.FromDate,this.ToDate);
		this.OpeningBalance = repbean.getOpeningBalance();
		this.TotalCredit = repbean.getTotalCredit();
		this.TotalDebit = repbean.getTotalDebit();
		this.ClosingBalance = repbean.getClosingBalance();
//		this.transactionlist = repbean.getTransactionlist();
//		this.compDTO = mDAO.returnCompanyDetails();	
//		logger.info("Total records "+ this.transactionlist.size());
	}
}
