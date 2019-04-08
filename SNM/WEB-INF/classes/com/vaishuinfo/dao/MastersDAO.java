package com.vaishuinfo.dao;


import com.vaishuinfo.dto.CustomerDTO;
import com.vaishuinfo.dto.LoginDTO;
import com.vaishuinfo.setting.DBConnector;
import com.vaishuinfo.setting.DBQueryLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class MastersDAO extends AbstractDAO {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //  HH:mm:ss");

	public int saveCustomer(CustomerDTO cusDTO ) {

        DBConnector db2Connector = DBConnector.getInstance();
        boolean retry;
        int numOfretry = 0;
        Connection con = null;
        PreparedStatement prepStmnt = null;
        int status = 0;
        String selectStatement = "";
        synchronized (this) {
            do {
                retry = false;
                try {
                	logger.info("Serial number:" + cusDTO.getCustomerSerialNumber());
                	logger.info("GST File number:" + cusDTO.getCustomerGSTFileNumber());
                	logger.info("IT File number:" + cusDTO.getCustomerITFileNumber());
                	
                	if (cusDTO.getCustomerSerialNumber()==0 ){
                		con = db2Connector.getConnection(true);
                        selectStatement = DBQueryLoader.getInstance().getQueryStatement("INSERT_CUSTOMER");
                        prepStmnt = con.prepareStatement(selectStatement);
                        prepStmnt.setInt(1, cusDTO.getCustomerSerialNumber());
                        prepStmnt.setString(2, cusDTO.getCustomerITFileNumber());
                        prepStmnt.setString(3, cusDTO.getCustomerCompanyName());
                        prepStmnt.setString(4, cusDTO.getCustomerProprietorName());
                        prepStmnt.setString(5, cusDTO.getCustomerContactNumber());
                        prepStmnt.setString(6, cusDTO.getCustomerContactEmailId());
                        prepStmnt.setString(7, cusDTO.getCustomerPANNumber());
                        //prepStmnt.setString(8, "2017-01-01"); //cusDTO.getCustomerDOB());
                        
                        prepStmnt.setString(8, sdf.format(cusDTO.getCustomerDOB()));
                        prepStmnt.setString(9, cusDTO.getCustomerITLastFiledYear());
                        prepStmnt.setString(10, cusDTO.getCustomerITUserID());
                        prepStmnt.setString(11, cusDTO.getCustomerITPassword());
                        prepStmnt.setString(12, cusDTO.getCustomerITEmailId());
                        prepStmnt.setString(13, cusDTO.getCustomerITPassword());
                        prepStmnt.setString(14, cusDTO.getCustomerITPhoneNumber());
                        prepStmnt.setString(15, cusDTO.getCustomerITType());
                        
                        prepStmnt.setString(16, cusDTO.getCustomerGSTINNumber());
                        prepStmnt.setString(17, cusDTO.getCustomerGSTUserID());
                        prepStmnt.setString(18, cusDTO.getCustomerGSTPassword());
                        prepStmnt.setString(19, cusDTO.getCustomerGSTFileNumber());
                        prepStmnt.setString(20, cusDTO.getCustomerGSTEmailId());
                        prepStmnt.setString(21, cusDTO.getCustomerGSTEmailPassword());
                        prepStmnt.setString(22, cusDTO.getCustomerGSTPhoneNumber());
                        prepStmnt.setString(23, cusDTO.getCustomerAddress());
                        status = prepStmnt.executeUpdate();
                	}else{
                		con = db2Connector.getConnection(true);
                		selectStatement = DBQueryLoader.getInstance().getQueryStatement("UPDATE_CUSTOMER");
                        prepStmnt = con.prepareStatement(selectStatement);
                        prepStmnt.setString(1, cusDTO.getCustomerITFileNumber());
                        prepStmnt.setString(2, cusDTO.getCustomerCompanyName());
                        prepStmnt.setString(3, cusDTO.getCustomerProprietorName());
                        prepStmnt.setString(4, cusDTO.getCustomerContactNumber());
                        prepStmnt.setString(5, cusDTO.getCustomerContactEmailId());
                        prepStmnt.setString(6, cusDTO.getCustomerPANNumber());
                        //prepStmnt.setString(7, "2017-01-01");//cusDTO.getCustomerDOB());
                        prepStmnt.setString(7, sdf.format(cusDTO.getCustomerDOB()));
                        prepStmnt.setString(8, cusDTO.getCustomerITLastFiledYear());
                        prepStmnt.setString(9, cusDTO.getCustomerITUserID());
                        prepStmnt.setString(10, cusDTO.getCustomerITPassword());
                        prepStmnt.setString(11, cusDTO.getCustomerITEmailId());
                        prepStmnt.setString(12, cusDTO.getCustomerITPassword());
                        prepStmnt.setString(13, cusDTO.getCustomerITPhoneNumber());
                        prepStmnt.setString(14, cusDTO.getCustomerITType());
                        prepStmnt.setString(15, cusDTO.getCustomerITRemarks());
                        prepStmnt.setString(16, cusDTO.getCustomerGSTINNumber());
                        prepStmnt.setString(17, cusDTO.getCustomerGSTUserID());
                        prepStmnt.setString(18, cusDTO.getCustomerGSTPassword());
                        prepStmnt.setString(19, cusDTO.getCustomerGSTFileNumber());
                        prepStmnt.setString(20, cusDTO.getCustomerGSTEmailId());
                        prepStmnt.setString(21, cusDTO.getCustomerGSTEmailPassword());
                        prepStmnt.setString(22, cusDTO.getCustomerGSTPhoneNumber());
                        prepStmnt.setString(22, cusDTO.getCustomerGSTPhoneNumber());
                        prepStmnt.setString(23, cusDTO.getCustomerAddress());
                        
                        prepStmnt.setInt(24, cusDTO.getCustomerSerialNumber());
                                                
                        status = prepStmnt.executeUpdate();
                	}
                    
                
                } catch (SQLException scon) {
                    logger.info("SQLException.." + scon + "  " + numOfretry);
                    if (numOfretry < 2) {
                        numOfretry++;
                        con = db2Connector.getConnection(true);
                        retry = true;
                    } else {
                        retry = false;
                        logger.info("Exception :" + scon.getMessage());
                    }
                } finally {
                    close(prepStmnt);
                    close(con);
                }
            } while (retry);
            logger.info("Status:" + status);
        }
        return status;
    }

	
	public int getMasterCount(String keyword) {
        DBConnector db2Connector = DBConnector.getInstance();
        boolean retry;
        int numOfretry = 0;
        Connection con = null;
        PreparedStatement prepStmnt = null;
        ResultSet resInfo = null;
        int count=0;
        String selectStatement = "";
        do {
            retry = false;
            try {
                con = db2Connector.getConnection(false);
                if (keyword=="CUST") {
                	selectStatement = DBQueryLoader.getInstance().getQueryStatement("SELECT_CUSTOMER_COUNT");
                }
                
                
                
                prepStmnt = con.prepareStatement(selectStatement);
                resInfo = prepStmnt.executeQuery();
                if (resInfo.next()) {
                	count = resInfo.getInt(1);
                }
            } catch (SQLException scon) {
                logger.info("Count SQLException.." + scon + "  " + numOfretry);
                if (numOfretry < 2) {
                    numOfretry++;
                    con = db2Connector.getConnection(false);
                    retry = true;
                } else {
                    retry = false;
                    logger.info("Count Exception :" + scon.getMessage());
                }
            } finally {
                close(resInfo);
                close(prepStmnt);
                close(con);
            }
        } while (retry);
        return count;
    }
	
	public List<CustomerDTO> getCustomerList() {

        DBConnector db2Connector = DBConnector.getInstance();
        boolean retry;
        int numOfretry = 0;
        Connection con = null;
        PreparedStatement prepStmnt = null;
        ResultSet resInfo = null;
        List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
        do {
            retry = false;
            try {
                con = db2Connector.getConnection(false);
                String selectStatement =
                        DBQueryLoader.getInstance().getQueryStatement("SELECT_CUSTOMER");

                prepStmnt = con.prepareStatement(selectStatement);
                resInfo = prepStmnt.executeQuery();
                int i=1;
                while (resInfo.next()) {
                	CustomerDTO custDTO = new CustomerDTO();
                	custDTO.setCustomerSerialNumber(resInfo.getInt("serial_number"));
                	custDTO.setCustomerITFileNumber(resInfo.getString("file_number"));
                	custDTO.setCustomerCompanyName(resInfo.getString("company_name"));
                	custDTO.setCustomerProprietorName(resInfo.getString("proprietor_name"));
                	custDTO.setCustomerContactNumber(resInfo.getString("contact_number"));
                	custDTO.setCustomerContactEmailId(resInfo.getString("contact_email"));
                	custDTO.setCustomerPANNumber(resInfo.getString("pan"));
                	custDTO.setCustomerDOB(resInfo.getDate("dob"));
                	
                	custDTO.setCustomerITLastFiledYear(resInfo.getString("it_lastfiledyear"));
                	custDTO.setCustomerITUserID(resInfo.getString("it_user_id"));
                	custDTO.setCustomerITPassword(resInfo.getString("it_password"));
                	custDTO.setCustomerITEmailId(resInfo.getString("it_email_id"));
                	custDTO.setCustomerITEmailPassword(resInfo.getString("it_email_password"));
                	custDTO.setCustomerITPhoneNumber(resInfo.getString("it_phone_number"));
                	custDTO.setCustomerITType(resInfo.getString("it_type"));
                	custDTO.setCustomerITRemarks(resInfo.getString("it_remarks"));
                	
                	custDTO.setCustomerGSTINNumber(resInfo.getString("gstin_number"));
                	custDTO.setCustomerGSTUserID(resInfo.getString("gst_user_id"));
                	custDTO.setCustomerGSTPassword(resInfo.getString("gst_password"));
                	custDTO.setCustomerGSTFileNumber(resInfo.getString("gst_arn_number"));
                	custDTO.setCustomerGSTEmailId(resInfo.getString("gst_email_id"));
                	custDTO.setCustomerGSTEmailPassword(resInfo.getString("gst_email_password"));
                	custDTO.setCustomerGSTPhoneNumber(resInfo.getString("gst_phone_number"));
                	custDTO.setCustomerAddress(resInfo.getString("contact_address"));

                	custDTO.setNewCustmode(false);
                	custDTO.setEditCustmode(false);
                	custDTO.setEditCustITmode(false);
                	custDTO.setEditCustGSTmode(false);
                	custDTO.setLockCustmode(true);
                	custDTO.setLockCustITmode(true);
                	custDTO.setLockCustGSTmode(true);
                	customerDTOList.add(custDTO);
                }
            } catch (SQLException scon) {
                logger.info("Select SQLException.." + scon + "  " + numOfretry);
                if (numOfretry < 2) {
                    numOfretry++;
                    con = db2Connector.getConnection(false);
                    retry = true;
                } else {
                    retry = false;
                    logger.info("Select Exception :" + scon.getMessage());
                }
            } finally {
                close(resInfo);
                close(prepStmnt);
                close(con);
            }
        } while (retry);
        logger.info("cust list size"+customerDTOList.size());
        return customerDTOList;
    }
	

	public List<String> getMasterList(String type) {

        DBConnector db2Connector = DBConnector.getInstance();
        boolean retry;
        int numOfretry = 0;
        Connection con = null;
        PreparedStatement prepStmnt = null;
        ResultSet resInfo = null;
        List<String> mList = new ArrayList<String>();
        String selectStatement = "";
        do {
            retry = false;
            try {
                con = db2Connector.getConnection(false);
                logger.info("Select Type " + type );
                if (type=="CUSTOMER"){
                	selectStatement = DBQueryLoader.getInstance().getQueryStatement("SELECT_CUSTOMER1");
                }else if (type=="ITTYPE"){
                	selectStatement = DBQueryLoader.getInstance().getQueryStatement("SELECT_ITTYPE");
                }else if (type=="ASSESSMENTYEAR"){
                	selectStatement = DBQueryLoader.getInstance().getQueryStatement("SELECT_ASSESSMENTYEAR");
                } 
                prepStmnt = con.prepareStatement(selectStatement);
                resInfo = prepStmnt.executeQuery();
                int i=1;
                while (resInfo.next()) {
                	mList.add(resInfo.getString(1));
                }
            } catch (SQLException scon) {
                logger.info("Select SQLException.." + scon + "  " + numOfretry);
                if (numOfretry < 2) {
                    numOfretry++;
                    con = db2Connector.getConnection(false);
                    retry = true;
                } else {
                    retry = false;
                    logger.info("Select Exception :" + scon.getMessage());
                }
            } finally {
                close(resInfo);
                close(prepStmnt);
                close(con);
            }
        } while (retry);
        logger.info(type + " list size"+mList.size());
        return mList;
    }
	
	public boolean changePassword(String password, String userId) {
        DBConnector db2Connector = DBConnector.getInstance();
        boolean retry;
        int numOfretry = 0;
        Connection con = null;
        PreparedStatement prepStmnt = null;
        boolean status = false;
        synchronized (this) {
            do {
                retry = false;
                try {

                    con = db2Connector.getConnection(true);
                    String selectStatement =
                            DBQueryLoader.getInstance().getQueryStatement("UPDATE_PASSWORD");
                    prepStmnt = con.prepareStatement(selectStatement);
                    prepStmnt.setString(1, password);
                    prepStmnt.setString(2, userId);
                    int s = prepStmnt.executeUpdate();

                    if (s == 1) {
                        status = true;
                    }
                } catch (SQLException scon) {
                    logger.info("SQLException.." + scon + "  " + numOfretry);
                    if (numOfretry < 2) {
                        numOfretry++;
                        con = db2Connector.getConnection(true);
                        retry = true;
                    } else {
                        retry = false;
                        logger.info("Exception :" + scon.getMessage());
                    }
                } finally {
                    close(prepStmnt);
                    close(con);
                }
            } while (retry);
            logger.info("Update Status:" + status);

            return status;
        }
    }

    public LoginDTO checkLogin(String userId, String password) {
    	LoginDTO lDTO = new LoginDTO();
        DBConnector db2Connector = DBConnector.getInstance();
        boolean retry;
        int numOfretry = 0;
        Connection con = null;
        PreparedStatement prepStmnt = null;
        ResultSet resInfo = null;
        do {
            retry = false;
            try {

                con = db2Connector.getConnection(true);
                String selectStatement =
                        DBQueryLoader.getInstance().getQueryStatement("SELECT_LOGIN");
                //logger.info("password:" + password);
                prepStmnt = con.prepareStatement(selectStatement);
                prepStmnt.setString(1, userId);
                prepStmnt.setString(2, password);
                resInfo = prepStmnt.executeQuery();

                if (resInfo.next()) {
                    lDTO.setUserName(getString(resInfo, "lmusr_name", ""));
                    //logger.info("admin:" + resInfo.getBoolean("lmusr_admin"));
                    lDTO.setAdmin(resInfo.getBoolean("lmusr_admin"));
                    lDTO.setReadOnlyUser(resInfo.getBoolean("lmusr_readonly"));
                    //logger.info("admin:" + lDTO.isAdmin());
                    lDTO.setUserId(userId);
                    lDTO.setPassword(password);
                }
            } catch (SQLException scon) {
                logger.info("SQLException.." + scon + "  " + numOfretry);
                if (numOfretry < 2) {
                    numOfretry++;
                    con = db2Connector.getConnection(true);
                    retry = true;
                } else {
                    retry = false;
                    logger.info("Exception :" + scon.getMessage());
                }
            } finally {
                close(resInfo);
                close(prepStmnt);
                close(con);
            }
        } while (retry);
        
        return lDTO;
    }
    public int returnCutomerID(String custName){
    	int custid=0;
    	DBConnector db2Connector = DBConnector.getInstance();
        boolean retry;
        int numOfretry = 0;
        Connection con = null;
        PreparedStatement prepStmnt = null;
        ResultSet resInfo = null;
        do {
            retry = false;
            try {
                con = db2Connector.getConnection(false);
                String selectStatement =
                        DBQueryLoader.getInstance().getQueryStatement("SELECT_CUSTOMERID");

                prepStmnt = con.prepareStatement(selectStatement);
                prepStmnt.setString(1, custName);
                resInfo = prepStmnt.executeQuery();
                int i=1;
                while (resInfo.next()) {
                	custid = resInfo.getInt("lmcus_id");
                }
            } catch (SQLException scon) {
                logger.info("Select SQLException.." + scon + "  " + numOfretry);
                if (numOfretry < 2) {
                    numOfretry++;
                    con = db2Connector.getConnection(false);
                    retry = true;
                } else {
                    retry = false;
                    logger.info("Select Exception :" + scon.getMessage());
                }
            } finally {
                close(resInfo);
                close(prepStmnt);
                close(con);
            }
        } while (retry);
    	return custid;		
    }
    
    public String returnCutomerName(int custid){
    	String custName="";
    	DBConnector db2Connector = DBConnector.getInstance();
        boolean retry;
        int numOfretry = 0;
        Connection con = null;
        PreparedStatement prepStmnt = null;
        ResultSet resInfo = null;
        do {
            retry = false;
            try {
                con = db2Connector.getConnection(false);
                String selectStatement =
                        DBQueryLoader.getInstance().getQueryStatement("SELECT_CUSTOMERNAME");

                prepStmnt = con.prepareStatement(selectStatement);
                prepStmnt.setInt(1, custid);
                resInfo = prepStmnt.executeQuery();
                int i=1;
                while (resInfo.next()) {
                	custName = resInfo.getString("lmcus_name");
                }
            } catch (SQLException scon) {
                logger.info("Select SQLException.." + scon + "  " + numOfretry);
                if (numOfretry < 2) {
                    numOfretry++;
                    con = db2Connector.getConnection(false);
                    retry = true;
                } else {
                    retry = false;
                    logger.info("Select Exception :" + scon.getMessage());
                }
            } finally {
                close(resInfo);
                close(prepStmnt);
                close(con);
            }
        } while (retry);
    	return custName;		
    }

   

    public String deleteCustomer(int custId) {

        DBConnector db2Connector = DBConnector.getInstance();
        boolean retry;
        int numOfretry = 0;
        String retVal = "";
        Connection con = null;
        PreparedStatement prepStmnt = null;
        PreparedStatement prepStmnt1 = null;
        PreparedStatement prepStmnt2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        do {
            retry = false;
            try {
            	logger.info("Delete cust custId:" + custId);
                con = db2Connector.getConnection(true);
                //String selectStatement1 = DBQueryLoader.getInstance().getQueryStatement("CHECK_CUSTOMER1");
                //String selectStatement2 = DBQueryLoader.getInstance().getQueryStatement("CHECK_CUSTOMER2");
                //prepStmnt1 = con.prepareStatement(selectStatement1);
                //prepStmnt1.setInt(1, custId);
                //rs1 = prepStmnt1.executeQuery();
                //if (rs1.next()){
                //	retVal = "Transactions available. Customer is not allowed to delete.";
                //}else{
	            //    prepStmnt2 = con.prepareStatement(selectStatement2);
	            //    prepStmnt2.setInt(1, custId);
	            //    rs2 = prepStmnt2.executeQuery();
	            //    if (rs2.next()){
	            //    	retVal = "Transactions available. Customer is not allowed to delete.";
	            //    }else{
	                    String selectStatement = DBQueryLoader.getInstance().getQueryStatement("DELETE_CUSTOMER");
	                    prepStmnt = con.prepareStatement(selectStatement);
	                    prepStmnt.setInt(1, custId);
	                    prepStmnt.execute();
	                    logger.info("delete cust " + selectStatement);	                	
	            //    }
                //}    
            } catch (SQLException scon) {
                logger.info("Select SQLException.." + scon + "  " + numOfretry);
                if (numOfretry < 2) {
                    numOfretry++;
                    con = db2Connector.getConnection(true);
                    retry = true;
                } else {
                    retry = false;
                    logger.info("Select Exception :" + scon.getMessage());
                }
            } finally {
                close(rs1);
                close(rs2);
                close(prepStmnt);
                close(prepStmnt1);
                close(prepStmnt2);
                close(con);
            }
        } while (retry);
        return retVal;
    }
}
