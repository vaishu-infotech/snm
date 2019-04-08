package com.vaishuinfo.dto;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //  HH:mm:ss");
    private int customerSerialNumber=0;
    private String customerGSTFileNumber="";
    private String customerCompanyName="";
    private String customerProprietorName="";
    private String customerContactNumber="";
    private String customerContactEmailId="";
    private String customerPANNumber="";
    private Date customerDOB;
    private String customerITLastFiledYear="";
    private String customerITUserID="";
    private String customerITPassword="";
    private String customerITEmailId="";
    private String customerITEmailPassword="";
    private String customerITPhoneNumber="";
    private String customerITType="";
    private String customerITRemarks="";
    private String customerAddress="";
    
    private String customerGSTINNumber="";
    private String customerGSTUserID="";
    private String customerGSTPassword="";
    private String customerITFileNumber="";
    private String customerGSTEmailId="";
    private String customerGSTEmailPassword="";
    private String customerGSTPhoneNumber="";
    
    private boolean newCustmode = true;
	private boolean editCustmode = false;
	private boolean editCustITmode = false;
	private boolean editCustGSTmode = false;
	private boolean lockCustmode = false;
	private boolean lockCustITmode = false;
	private boolean lockCustGSTmode = false;
	
	public CustomerDTO() {
		this.customerSerialNumber = 0;
		this.customerDOB = new Date();
    }

	public int getCustomerSerialNumber() {
		return customerSerialNumber;
	}

	public void setCustomerSerialNumber(int customerSerialNumber) {
		this.customerSerialNumber = customerSerialNumber;
	}

	public String getCustomerCompanyName() {
		return customerCompanyName;
	}

	public void setCustomerCompanyName(String customerCompanyName) {
		this.customerCompanyName = customerCompanyName;
	}

	public String getCustomerProprietorName() {
		return customerProprietorName;
	}

	public void setCustomerProprietorName(String customerProprietorName) {
		this.customerProprietorName = customerProprietorName;
	}

	public String getCustomerContactNumber() {
		return customerContactNumber;
	}

	public void setCustomerContactNumber(String customerContactNumber) {
		this.customerContactNumber = customerContactNumber;
	}

	public String getCustomerContactEmailId() {
		return customerContactEmailId;
	}

	public void setCustomerContactEmailId(String customerContactEmailId) {
		this.customerContactEmailId = customerContactEmailId;
	}

	public String getCustomerPANNumber() {
		return customerPANNumber;
	}

	public void setCustomerPANNumber(String customerPANNumber) {
		this.customerPANNumber = customerPANNumber;
	}

	public String getCustomerITLastFiledYear() {
		return customerITLastFiledYear;
	}

	public void setCustomerITLastFiledYear(String customerITLastFiledYear) {
		this.customerITLastFiledYear = customerITLastFiledYear;
	}

	public String getCustomerITUserID() {
		return customerITUserID;
	}

	public void setCustomerITUserID(String customerITUserID) {
		this.customerITUserID = customerITUserID;
	}

	public String getCustomerITPassword() {
		return customerITPassword;
	}

	public void setCustomerITPassword(String customerITPassword) {
		this.customerITPassword = customerITPassword;
	}

	public String getCustomerITEmailId() {
		return customerITEmailId;
	}

	public void setCustomerITEmailId(String customerITEmailId) {
		this.customerITEmailId = customerITEmailId;
	}

	public String getCustomerITEmailPassword() {
		return customerITEmailPassword;
	}

	public void setCustomerITEmailPassword(String customerITEmailPassword) {
		this.customerITEmailPassword = customerITEmailPassword;
	}

	public String getCustomerITPhoneNumber() {
		return customerITPhoneNumber;
	}

	public void setCustomerITPhoneNumber(String customerITPhoneNumber) {
		this.customerITPhoneNumber = customerITPhoneNumber;
	}

	public String getCustomerGSTINNumber() {
		return customerGSTINNumber;
	}

	public void setCustomerGSTINNumber(String customerGSTINNumber) {
		this.customerGSTINNumber = customerGSTINNumber;
	}

	public String getCustomerGSTUserID() {
		return customerGSTUserID;
	}

	public void setCustomerGSTUserID(String customerGSTUserID) {
		this.customerGSTUserID = customerGSTUserID;
	}

	public String getCustomerGSTPassword() {
		return customerGSTPassword;
	}

	public void setCustomerGSTPassword(String customerGSTPassword) {
		this.customerGSTPassword = customerGSTPassword;
	}

	public String getCustomerGSTEmailId() {
		return customerGSTEmailId;
	}

	public void setCustomerGSTEmailId(String customerGSTEmailId) {
		this.customerGSTEmailId = customerGSTEmailId;
	}

	public String getCustomerGSTEmailPassword() {
		return customerGSTEmailPassword;
	}

	public void setCustomerGSTEmailPassword(String customerGSTEmailPassword) {
		this.customerGSTEmailPassword = customerGSTEmailPassword;
	}

	public String getCustomerGSTPhoneNumber() {
		return customerGSTPhoneNumber;
	}

	public void setCustomerGSTPhoneNumber(String customerGSTPhoneNumber) {
		this.customerGSTPhoneNumber = customerGSTPhoneNumber;
	}

	public boolean isEditCustmode() {
		return editCustmode;
	}

	public void setEditCustmode(boolean editCustmode) {
		this.editCustmode = editCustmode;
	}

	public boolean isEditCustITmode() {
		return editCustITmode;
	}

	public void setEditCustITmode(boolean editCustITmode) {
		this.editCustITmode = editCustITmode;
	}

	public boolean isEditCustGSTmode() {
		return editCustGSTmode;
	}

	public void setEditCustGSTmode(boolean editCustGSTmode) {
		this.editCustGSTmode = editCustGSTmode;
	}

	public boolean isLockCustmode() {
		return lockCustmode;
	}

	public void setLockCustmode(boolean lockCustmode) {
		this.lockCustmode = lockCustmode;
	}

	public boolean isLockCustITmode() {
		return lockCustITmode;
	}

	public void setLockCustITmode(boolean lockCustITmode) {
		this.lockCustITmode = lockCustITmode;
	}

	public boolean isLockCustGSTmode() {
		return lockCustGSTmode;
	}

	public void setLockCustGSTmode(boolean lockCustGSTmode) {
		this.lockCustGSTmode = lockCustGSTmode;
	}

	public boolean isNewCustmode() {
		return newCustmode;
	}

	public void setNewCustmode(boolean newCustmode) {
		this.newCustmode = newCustmode;
	}

	public Date getCustomerDOB() {
		return customerDOB;
	}

	public void setCustomerDOB(Date customerDOB) {
		this.customerDOB = customerDOB;
	}

	public String getCustomerGSTFileNumber() {
		return customerGSTFileNumber;
	}

	public void setCustomerGSTFileNumber(String customerGSTFileNumber) {
		this.customerGSTFileNumber = customerGSTFileNumber;
	}

	public String getCustomerITFileNumber() {
		return customerITFileNumber;
	}

	public void setCustomerITFileNumber(String customerITFileNumber) {
		this.customerITFileNumber = customerITFileNumber;
	}

	public String getCustomerITType() {
		return customerITType;
	}

	public void setCustomerITType(String customerITType) {
		this.customerITType = customerITType;
	}

	public String getCustomerITRemarks() {
		return customerITRemarks;
	}

	public void setCustomerITRemarks(String customerITRemarks) {
		this.customerITRemarks = customerITRemarks;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	

	
}
