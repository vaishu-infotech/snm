package com.vaishuinfo.dao;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.record.formula.functions.Abs;
import org.apache.poi.hssf.record.formula.functions.Isnumber;
import org.slf4j.Logger;

import com.vaishuinfo.managedbean.ReportBean;
import com.vaishuinfo.setting.DBConnector;
import com.vaishuinfo.setting.DBQueryLoader;
import com.vaishuinfo.setting.DBResourseLoader;


public class ReportsDAO extends AbstractDAO {

 private Logger logger = DBResourseLoader.getInstance().getLogger(getClass());
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
 SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
 SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy");
 DecimalFormat df = new DecimalFormat("#.00");
 DecimalFormat df1 = new DecimalFormat("#.000");
 
 public ReportBean CustomerLedgerReport(String custName, Date FromDate, Date ToDate) {
	 	
	 	ReportBean repBean = new ReportBean();
        DBConnector db2Connector = DBConnector.getInstance();
        MastersDAO mdao = new MastersDAO();
        boolean retry;
        int numOfretry = 0;
        int custNum = 0;
        Connection con = null;
        PreparedStatement prepStmnt1 = null;
        PreparedStatement prepStmnt2 = null;
        PreparedStatement prepStmnt3 = null;
        PreparedStatement prepStmnt4 = null;
        PreparedStatement prepStmnt5 = null;
        PreparedStatement prepStmnt6 = null;
        PreparedStatement prepStmntCb = null;
        PreparedStatement prepStmntDb = null;
        PreparedStatement prepStmntTds = null;
        PreparedStatement prepStmntPay = null;
        PreparedStatement prepStmntInv = null;
        PreparedStatement prepStmnt3a = null;
        PreparedStatement prepStmnt4a = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs6 = null;
        ResultSet rsCb = null;
        ResultSet rsDb = null;
        ResultSet rsTds = null;
        ResultSet rsPay = null;
        ResultSet rsInv = null;
        ResultSet rs3a = null;
        ResultSet rs4a = null;
        Double TotalCredit=0.0;
        Double TotalDebit=0.0;
        Double OpeningBalance = 0.0;
/*        ArrayList<TransactionDTO> trnDTOList = new ArrayList<TransactionDTO>();
        
        do {
            retry = false;
            try {
            	custNum = mdao.returnCutomerID(custName);
            	logger.info("custNum1 "+custNum);
            	logger.info("From date "+FromDate);
            	logger.info("To date "+ToDate);
            	
            	OpeningBalance = mdao.getCustomerOpeningBalance(custNum);
            	
                con = db2Connector.getConnection(false);
                String selectStatement1 = "SELECT * FROM lm_debitheader INNER JOIN lm_debitdetail ON lmdh_entrynumber=lmdd_entrynumber where lmdh_status=2 and lmdh_customerid=?";
                String selectStatement2 = "SELECT * FROM lm_creditheader INNER JOIN lm_creditdetail ON lmch_entrynumber=lmcd_entrynumber where lmch_status=2 and lmch_customerid=?";
                String selectStatement3a = "SELECT * FROM lm_paymentheader INNER JOIN lm_paymentdetail ON lmph_entrynumber=lmpd_entrynumber where lmph_status=2 and lmph_customerid=?";
                String selectStatement4a = "SELECT * FROM lm_invoiceheader INNER JOIN lm_invoicedetail ON lmih_entrynumber=lmid_entrynumber where lmih_status=2 and lmih_customerid=?";
                
                
                if (FromDate != null){
                    String selectStatementCb = "SELECT SUM(lmcd_amount)as TotCd FROM lm_creditdetail INNER JOIN lm_creditheader ON lmch_entrynumber=lmcd_entrynumber WHERE lmch_status=2 and lmch_customerid=?";
                    String selectStatementDb = "SELECT SUM(lmdd_amount)as TotDb FROM lm_debitdetail INNER JOIN lm_debitheader ON lmdh_entrynumber=lmdd_entrynumber WHERE lmdh_status=2 and lmdh_customerid=?";
                    String selectStatementTds = "SELECT SUM(lmtds_amount)as TotTds FROM lm_tdsdetail INNER JOIN lm_creditheader ON lmch_entrynumber=lmtds_entrynumber WHERE lmch_status=2 and lmch_customerid=?";
                    String selectStatementInv = "SELECT SUM(lmid_amount)as TotInv FROM lm_invoicedetail INNER JOIN lm_invoiceheader ON lmih_entrynumber=lmid_entrynumber WHERE lmih_status=2 and lmih_customerid=?";
                    String selectStatementPay = "SELECT SUM(lmpd_amount)as TotPay FROM lm_paymentdetail INNER JOIN lm_paymentheader ON lmph_entrynumber=lmpd_entrynumber WHERE lmph_status=2 and lmph_customerid=?";
                    
                	
                	selectStatement1 = selectStatement1 + " and lmdh_entrydate>='"+sdf2.format(FromDate)+"'";
                	selectStatement2 = selectStatement2 + " and lmch_entrydate>='"+sdf2.format(FromDate)+"'";
                	selectStatementCb = selectStatementCb + " and lmch_entrydate<'"+sdf2.format(FromDate)+"'";
                	selectStatementDb = selectStatementDb + " and lmdh_entrydate<'"+sdf2.format(FromDate)+"'";
                	selectStatementTds = selectStatementTds + " and lmch_entrydate<'"+sdf2.format(FromDate)+"'";
                	selectStatementInv = selectStatementInv + " and lmih_entrydate<'"+sdf2.format(FromDate)+"'";
                	selectStatementPay = selectStatementPay + " and lmph_entrydate<'"+sdf2.format(FromDate)+"'";
                	selectStatement3a = selectStatement3a + " and lmph_entrydate>='"+sdf2.format(FromDate)+"'";
                	selectStatement4a = selectStatement4a + " and lmih_entrydate>='"+sdf2.format(FromDate)+"'";
                	
                	
                	prepStmntCb = con.prepareStatement(selectStatementCb);
	                prepStmntDb = con.prepareStatement(selectStatementDb);
	                prepStmntTds = con.prepareStatement(selectStatementTds);
	                prepStmntPay = con.prepareStatement(selectStatementPay);
	                prepStmntInv = con.prepareStatement(selectStatementInv);
	                prepStmntCb.setInt(1, custNum);
	                rsCb = prepStmntCb.executeQuery();
	                if (rsCb.next()){
	                	Double TotCd = rsCb.getDouble("TotCd"); 
	                	if (TotCd != null){
	                		OpeningBalance = OpeningBalance + TotCd;
	                	}
	                }
	                prepStmntTds.setInt(1, custNum);
	                rsTds = prepStmntTds.executeQuery();
	                if (rsTds.next()){
	                	Double TotTds = rsTds.getDouble("TotTds"); 
	                	if (TotTds != null){ 
	                		OpeningBalance = OpeningBalance - TotTds;
	                	}
	                }
	                prepStmntDb.setInt(1, custNum);
	                rsDb = prepStmntDb.executeQuery();
	                if (rsDb.next()){
	                	Double TotDb = rsDb.getDouble("TotDb") ;
	                	if (TotDb != null){
	                		OpeningBalance = OpeningBalance - TotDb;
	                	}
	                }
	                prepStmntPay.setInt(1, custNum);
	                rsPay = prepStmntPay.executeQuery();
	                if (rsPay.next()){
	                	Double TotPay = rsPay.getDouble("TotPay") ;
	                	if (TotPay != null){
	                		OpeningBalance = OpeningBalance + TotPay;
	                	}
	                }
	                prepStmntInv.setInt(1, custNum);
	                rsInv = prepStmntInv.executeQuery();
	                if (rsInv.next()){
	                	Double TotInv = rsInv.getDouble("TotInv") ;
	                	if (TotInv != null){
	                		OpeningBalance = OpeningBalance - TotInv;
	                	}
	                }
                	
                }
                if (ToDate != null){
                	selectStatement1 = selectStatement1 + " and lmdh_entrydate<='"+sdf2.format(ToDate)+"'";
                	selectStatement2 = selectStatement2 + " and lmch_entrydate<='"+sdf2.format(ToDate)+"'";
                	selectStatement3a = selectStatement3a + " and lmph_entrydate<='"+sdf2.format(ToDate)+"'";
                	selectStatement4a = selectStatement4a + " and lmih_entrydate<='"+sdf2.format(ToDate)+"'";
                }
                logger.info("selectStatement1 "+selectStatement1);
                String selectStatement3 = DBQueryLoader.getInstance().getQueryStatement("INSERT_TMPLEDGER");
                String selectStatement4 = DBQueryLoader.getInstance().getQueryStatement("SELECT_TMPLEDGER");
                String selectStatement5 = DBQueryLoader.getInstance().getQueryStatement("SELECT_TOTALVAL");
                
                
                
                prepStmnt1 = con.prepareStatement(selectStatement1);
                prepStmnt1.setInt(1, custNum);
                rs1 = prepStmnt1.executeQuery();
                prepStmnt2 = con.prepareStatement(selectStatement2);
                prepStmnt2.setInt(1, custNum);
                rs2 = prepStmnt2.executeQuery();
                prepStmnt3a = con.prepareStatement(selectStatement3a);
                prepStmnt3a.setInt(1, custNum);
                rs3a = prepStmnt3a.executeQuery();
                prepStmnt4a = con.prepareStatement(selectStatement4a);
                prepStmnt4a.setInt(1, custNum);
                rs4a = prepStmnt4a.executeQuery();
               // ClearTemporaryTable("LEDGERREPORT");
                int tmpEntNo = 0;
                while (rs2.next()) {
                	if (tmpEntNo != rs2.getInt("lmch_entrynumber")){
                		String selectStatement6 = "SELECT * FROM lm_creditheader INNER JOIN lm_tdsdetail ON lmch_entrynumber=lmtds_entrynumber where lmch_status=2 and lmch_entrynumber=?";
                		prepStmnt6 = con.prepareStatement(selectStatement6);
                		prepStmnt6.setString(1, rs2.getString("lmch_entrynumber"));
                		rs6 = prepStmnt6.executeQuery();
                		while (rs6.next()) {
        	                prepStmnt3 = con.prepareStatement(selectStatement3);
        	                prepStmnt3.setString(1, rs6.getString("lmtds_description"));
        	                prepStmnt3.setString(2, sdf2.format(rs2.getDate("lmch_billdate")));
        	                prepStmnt3.setString(3, rs2.getString("lmch_billno"));
        	                prepStmnt3.setString(4, rs6.getString("lmtds_remarks"));
        	                prepStmnt3.setDouble(5, 0);
        	                prepStmnt3.setDouble(6, rs6.getDouble("lmtds_amount"));
        	                prepStmnt3.setString(7, sdf2.format(rs6.getDate("lmch_entrydate")));
        	                prepStmnt3.executeUpdate();
                		}
                	}
                	tmpEntNo=rs2.getInt("lmch_entrynumber");
                	
	                prepStmnt3 = con.prepareStatement(selectStatement3);
	                prepStmnt3.setString(1, rs2.getString("lmcd_description"));
	                prepStmnt3.setString(2, sdf2.format(rs2.getDate("lmch_billdate")));
	                prepStmnt3.setString(3, rs2.getString("lmch_billno"));
	                prepStmnt3.setString(4, rs2.getString("lmcd_particulars"));
	                prepStmnt3.setDouble(5, rs2.getDouble("lmcd_amount"));
	                prepStmnt3.setDouble(6, 0);
	                prepStmnt3.setString(7, sdf2.format(rs2.getDate("lmch_entrydate")));
	                prepStmnt3.executeUpdate();
                }
                while (rs1.next()) {
	                prepStmnt3 = con.prepareStatement(selectStatement3);
	                prepStmnt3.setString(1, rs1.getString("lmdd_description"));
	                prepStmnt3.setString(2, sdf2.format(rs1.getDate("lmdh_billdate")));
	                prepStmnt3.setString(3, rs1.getString("lmdh_billno"));
	                prepStmnt3.setString(4, rs1.getString("lmdd_type")+"("+rs1.getString("lmdd_remarks")+")");
	            
	                prepStmnt3.setDouble(5, 0);
	                prepStmnt3.setDouble(6, rs1.getDouble("lmdd_amount"));
	                prepStmnt3.setString(7, sdf2.format(rs1.getDate("lmdh_entrydate")));
	                prepStmnt3.executeUpdate();
                }
                
                while (rs3a.next()) {
	                prepStmnt3 = con.prepareStatement(selectStatement3);
	                prepStmnt3.setString(1, rs3a.getString("lmpd_description"));
	                prepStmnt3.setString(2, sdf2.format(rs3a.getDate("lmph_billdate")));
	                prepStmnt3.setString(3, rs3a.getString("lmph_billno"));
	                prepStmnt3.setString(4, rs3a.getString("lmpd_type")+"("+rs3a.getString("lmpd_remarks")+")(P)");
	            
	                prepStmnt3.setDouble(5, rs3a.getDouble("lmpd_amount"));
	                prepStmnt3.setDouble(6, 0);
	                prepStmnt3.setString(7, sdf2.format(rs3a.getDate("lmph_entrydate")));
	                prepStmnt3.executeUpdate();
                }
                
                while (rs4a.next()) {
	                prepStmnt3 = con.prepareStatement(selectStatement3);
	                prepStmnt3.setString(1, rs4a.getString("lmid_description"));
	                prepStmnt3.setString(2, sdf2.format(rs4a.getDate("lmih_billdate")));
	                prepStmnt3.setString(3, rs4a.getString("lmih_billno"));
	                prepStmnt3.setString(4, rs4a.getString("lmid_particulars")+"(I)");
	            
	                prepStmnt3.setDouble(5, 0);
	                prepStmnt3.setDouble(6, rs4a.getDouble("lmid_amount"));
	                prepStmnt3.setString(7, sdf2.format(rs4a.getDate("lmih_entrydate")));
	                prepStmnt3.executeUpdate();
                }
                
                prepStmnt5 = con.prepareStatement(selectStatement5);
                rs4 = prepStmnt5.executeQuery();
                if (rs4.next()){
                	TotalCredit = rs4.getDouble("TotCredit");
                	TotalDebit = rs4.getDouble("TotDebit");
                }
                
                if (OpeningBalance<0){
                	TotalDebit = TotalDebit + Math.abs(OpeningBalance);
                }else if (OpeningBalance>0){
                	TotalCredit = TotalCredit + Math.abs(OpeningBalance);
                }
                
                repBean.setTotalCredit(df.format(TotalCredit));
                repBean.setTotalDebit(df.format(TotalDebit));
                repBean.setOpeningBalance(df.format(OpeningBalance));
                repBean.setClosingBalance(df.format(TotalCredit-TotalDebit));
                
                prepStmnt4 = con.prepareStatement(selectStatement4);
                rs3 = prepStmnt4.executeQuery();
                while (rs3.next()) {
                	TransactionDTO tDTO = new TransactionDTO();
	                tDTO.setRepTrnDescription(rs3.getString("repTrnDescription"));
	                tDTO.setRepTrnBillDate(sdf1.format(rs3.getDate("repTrnBillDate")));
	                tDTO.setRepTrnBillNumber(rs3.getString("repTrnBillNumber"));
	                tDTO.setRepTrnParticulars(rs3.getString("repTrnParticulars"));
	                tDTO.setRepTrnEntryDate(sdf1.format(rs3.getDate("repTrnEntryDate")));
	                DecimalFormat df = new DecimalFormat("#.00");
	                if (rs3.getDouble("repCrAmount") > 0){
	                	tDTO.setRepCrAmount(df.format(rs3.getDouble("repCrAmount")));
	                }else{
	                	tDTO.setRepCrAmount("");
	                }
	                if (rs3.getDouble("repDbAmount")>0){
	                	tDTO.setRepDbAmount(df.format(rs3.getDouble("repDbAmount")));
	                }else{
	                	tDTO.setRepDbAmount("");
	                }
	                
	                tDTO.setRepTotalCrAmount(df.format(TotalCredit));
	                tDTO.setRepTotalDbAmount(df.format(TotalDebit));
	                tDTO.setRepOpeningBalance(df.format(OpeningBalance));
	                
	                trnDTOList.add(tDTO);
                }
               // ClearTemporaryTable("LEDGERREPORT");
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
                close(rs1);
                close(rs2);
                close(rs3);
                close(rs4);
                close(prepStmnt1);
                close(prepStmnt2);
                close(prepStmnt3);
                close(prepStmnt4);
                close(prepStmnt5);
                close(con);
            }
        } while (retry);
        logger.info("Total tmp records - "+trnDTOList.size());
        repBean.setTransactionlist(trnDTOList);
  */
        return repBean;
        
    }
 
 
 private void ClearTemporaryTable(String tblName) {
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
                    con = db2Connector.getConnection(true);
                    if (tblName == "LEDGERREPORT"){
                    	selectStatement = "delete from lm_ledgerreport";	
                    }
                    prepStmnt = con.prepareStatement(selectStatement);
                    status = prepStmnt.executeUpdate();

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
    }

}
