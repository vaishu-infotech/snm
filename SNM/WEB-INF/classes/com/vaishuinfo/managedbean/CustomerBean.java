package com.vaishuinfo.managedbean;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.lf5.util.DateFormatManager;
import org.slf4j.Logger;

import com.vaishuinfo.dao.MastersDAO;
import com.vaishuinfo.datamodel.CustomerDataModel;
import com.vaishuinfo.dto.CustomerDTO;
import com.vaishuinfo.setting.DBResourseLoader;

@ManagedBean
@ViewScoped
public class CustomerBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private CustomerDTO custDTO = new CustomerDTO();
    private CustomerDTO custDTO1 = new CustomerDTO();
    private CustomerDTO selectedCustomer;
    private List<CustomerDTO> customerList;
    private List<String> customers;
    private List<String> ITType;
    private List<String> AssessmentYearList;
    private CustomerDataModel customerModel;
    MastersDAO mdao = new MastersDAO();
    int customerCount = 0;

    private Logger logger = DBResourseLoader.getInstance().getLogger(getClass());
    
	public CustomerBean() {
		
		selectedCustomer = new CustomerDTO();
		customerCount = mdao.getMasterCount("CUST");
		customerList = mdao.getCustomerList();
		customerModel = new CustomerDataModel(customerList);
		customers = mdao.getMasterList("CUSTOMER");
		ITType = mdao.getMasterList("ITTYPE");
		AssessmentYearList = mdao.getMasterList("ASSESSMENTYEAR");
		this.custDTO = new CustomerDTO();
		this.custDTO1 = new CustomerDTO();

    }
	
	
	public CustomerDTO getCustDTO() {
		return custDTO;
	}

	public void setCustDTO(CustomerDTO custDTO) {
		this.custDTO = custDTO;
	}
	
	public CustomerDTO getCustDTO1() {
		return custDTO1;
	}

	public void setCustDTO1(CustomerDTO custDTO1) {
		this.custDTO1 = custDTO1;
	}


	public List<CustomerDTO> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerDTO> customerList) {
		this.customerList = customerList;
	}

	public CustomerDataModel getCustomerModel() {
		return customerModel;
	}

	public void setCustomerDataModel(CustomerDataModel customerModel) {
		this.customerModel = customerModel;
	}
	
	public List<String> getCustomers() {
		return customers;
	}

	public void setCustomers(List<String> customers) {
		this.customers = customers;
	}
	
	public List<String> getITType() {
		return ITType;
	}


	public void setITType(List<String> iTType) {
		ITType = iTType;
	}


	public List<String> getAssessmentYearList() {
		return AssessmentYearList;
	}


	public void setAssessmentYearList(List<String> assessmentYearList) {
		AssessmentYearList = assessmentYearList;
	}


	public CustomerDTO getSeletedCustomer() {
		return selectedCustomer;
	}
	public void setSelectedCustomer(CustomerDTO selectedCustomer) {  
        this.selectedCustomer = selectedCustomer;  
    }

	public void saveCustomer(){
    	int retVal =0;
    	//logger.info("Save Customer"+this.custDTO.getCustomerID());
        try {
	    	retVal=mdao.saveCustomer(this.custDTO);
	    	if (retVal==1) {
	    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Customer details saved successfully", ""));
	    		customerList = mdao.getCustomerList();
	    		this.custDTO = customerList.get(0);
	    		this.custDTO.setNewCustmode(false);
	    		this.custDTO.setEditCustmode(false);
	    		this.custDTO.setEditCustITmode(false);
	    		this.custDTO.setEditCustGSTmode(false);
	    		this.custDTO.setLockCustmode(true);
	    		this.custDTO.setLockCustITmode(true);
	    		this.custDTO.setLockCustGSTmode(true);
	    		
	    	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	public void saveCustomer1(){
    	int retVal =0;
    	//logger.info("Save Customer"+this.custDTO.getCustomerID());
        try {
	    	retVal=mdao.saveCustomer(this.custDTO1);
	    	if (retVal==1) {
	    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Customer details saved successfully", ""));
	    		customerList = mdao.getCustomerList();
	    		this.custDTO = customerList.get(0);
	    		this.custDTO.setNewCustmode(false);
	    		this.custDTO.setEditCustmode(false);
	    		this.custDTO.setEditCustITmode(false);
	    		this.custDTO.setEditCustGSTmode(false);
	    		this.custDTO.setLockCustmode(true);
	    		this.custDTO.setLockCustITmode(true);
	    		this.custDTO.setLockCustGSTmode(true);
	    		
	    	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	public void updateCustomer(){
    	int retVal =0;
    	//logger.info("Save Customer"+this.custDTO.getCustomerID());
    	retVal=mdao.saveCustomer(this.custDTO1);
    	if (retVal==1) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Customer details updated successfully", ""));  
    		this.custDTO1.setNewCustmode(false);
    		this.custDTO1.setEditCustmode(false);
    		this.custDTO1.setEditCustITmode(false);
    		this.custDTO1.setEditCustGSTmode(false);
    		this.custDTO1.setLockCustmode(true);
    		this.custDTO1.setLockCustITmode(true);
    		this.custDTO1.setLockCustGSTmode(true);

    	}
    	logger.info("Result"+retVal);
    }
	public void deleteCustomer(){
    	String retVal = "";
    	retVal=mdao.deleteCustomer(this.custDTO.getCustomerSerialNumber());
    	if (retVal == "" ) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Customer details deleted successfully", ""));
    		customerList = mdao.getCustomerList();
    		this.custDTO = customerList.get(0);
    	}else{
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,retVal, ""));
    	}
    	logger.info("Result"+retVal);
    }
	public void editCustomerInfo(){
		this.custDTO.setNewCustmode(false);
		this.custDTO.setEditCustmode(true);
		this.custDTO.setEditCustITmode(false);
		this.custDTO.setEditCustGSTmode(false);
		this.custDTO.setLockCustmode(false);
		this.custDTO.setLockCustITmode(true);
		this.custDTO.setLockCustGSTmode(true);
	}
	public void editCustomerITInfo(){
		this.custDTO.setNewCustmode(false);
		this.custDTO.setEditCustmode(false);
		this.custDTO.setEditCustITmode(true);
		this.custDTO.setEditCustGSTmode(false);
		this.custDTO.setLockCustmode(true);
		this.custDTO.setLockCustITmode(false);
		this.custDTO.setLockCustGSTmode(true);
	}
	public void editCustomerGSTInfo(){
		this.custDTO.setNewCustmode(false);
		this.custDTO.setEditCustmode(false);
		this.custDTO.setEditCustITmode(false);
		this.custDTO.setEditCustGSTmode(true);
		this.custDTO.setLockCustmode(true);
		this.custDTO.setLockCustITmode(true);
		this.custDTO.setLockCustGSTmode(false);
	}

	public void backup(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM-hhmmss");
		backupDB("snm_db", "root", "141210", "C:\\sn_db"+sdf.format(date)+".sql");
		logger.info("Backup Successful");
    }
	public boolean backupDB(String dbName, String dbUserName, String dbPassword, String path) {
		logger.info("path "+path); 
        String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump -u" + dbUserName + " -p" + dbPassword + " " + dbName + " -r " + path;
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Backup created successfully", ""));
                return true;
            } else {
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Could not create the backup. Contact adminstrator.", ""));
                System.out.println("Could not create the backup. Contact adminstrator.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return false;
    }
	
}
